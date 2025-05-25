package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于Java NIO Selector的高性能服务器
 * 底层在Linux系统上使用epoll实现
 */
public class NIOSelectorServer {

    private final int port;
    private final String host;
    private Selector selector;
    private ServerSocketChannel serverChannel;

    // 统计信息
    private final AtomicInteger connectionCount = new AtomicInteger(0);
    private final ConcurrentHashMap<SocketChannel, ClientInfo> clientMap = new ConcurrentHashMap<>();

    // 缓冲区大小
    private static final int BUFFER_SIZE = 1024;

    public NIOSelectorServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 启动服务器
     */
    public void start() throws IOException {
        // 1. 创建Selector (底层使用epoll_create)
        selector = Selector.open();

        // 2. 创建ServerSocketChannel
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false); // 设置为非阻塞模式

        // 3. 绑定端口
        serverChannel.bind(new InetSocketAddress(host, port));

        // 4. 注册到Selector，监听ACCEPT事件 (相当于epoll_ctl)
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("NIO服务器启动，监听地址: " + host + ":" + port);
        System.out.println("等待客户端连接...");

        // 5. 开始事件循环
        eventLoop();
    }

    /**
     * 主事件循环 - 相当于不断调用epoll_wait
     */
    private void eventLoop() throws IOException {
        while (true) {
            // 阻塞等待事件发生 (相当于epoll_wait)
            // 返回值是就绪的channel数量
            int readyChannels = selector.select();

            if (readyChannels == 0) {
                continue;
            }

            // 获取就绪的SelectionKey集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            // 处理每个就绪的事件
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove(); // 必须手动移除，否则会重复处理

                try {
                    if (!key.isValid()) {
                        continue;
                    }

                    // 处理不同类型的事件
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    } else if (key.isWritable()) {
                        handleWrite(key);
                    }
                } catch (Exception e) {
                    System.err.println("处理客户端事件时出错: " + e.getMessage());
                    closeClient(key);
                }
            }

            // 定期打印统计信息
            printStats();
        }
    }

    /**
     * 处理客户端连接请求 (ACCEPT事件)
     */
    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

        // 接受新连接
        SocketChannel clientChannel = serverChannel.accept();
        if (clientChannel != null) {
            // 设置为非阻塞模式
            clientChannel.configureBlocking(false);

            // 注册到Selector，监听READ事件
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);

            // 为每个客户端创建信息对象
            ClientInfo clientInfo = new ClientInfo(clientChannel);
            clientKey.attach(clientInfo);
            clientMap.put(clientChannel, clientInfo);

            int count = connectionCount.incrementAndGet();
            System.out.println("新客户端连接: " + clientChannel.getRemoteAddress() +
                    " (总连接数: " + count + ")");

            // 发送欢迎消息
            String welcome = "欢迎连接到NIO服务器! 你是第" + count + "个连接\n";
            clientInfo.addToWriteBuffer(welcome);
            clientKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    /**
     * 处理客户端数据读取 (READ事件)
     */
    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ClientInfo clientInfo = (ClientInfo) key.attachment();

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int bytesRead = clientChannel.read(buffer);

        if (bytesRead > 0) {
            // 有数据读取
            buffer.flip();
            String message = StandardCharsets.UTF_8.decode(buffer).toString();

            System.out.println("收到来自 " + clientChannel.getRemoteAddress() + " 的消息: " + message.trim());

            // 回显消息
            String response = "服务器回复: " + message;
            clientInfo.addToWriteBuffer(response);

            // 注册写事件
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        } else if (bytesRead == 0) {
            // 没有数据，继续监听
        } else {
            // 客户端关闭连接 (bytesRead == -1)
            System.out.println("客户端 " + clientChannel.getRemoteAddress() + " 断开连接");
            closeClient(key);
        }
    }

    /**
     * 处理数据写入 (WRITE事件)
     */
    private void handleWrite(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ClientInfo clientInfo = (ClientInfo) key.attachment();

        if (clientInfo.hasDataToWrite()) {
            ByteBuffer writeBuffer = clientInfo.getWriteBuffer();
            int bytesWritten = clientChannel.write(writeBuffer);

            if (bytesWritten > 0) {
                System.out.println("向 " + clientChannel.getRemoteAddress() + " 发送了 " + bytesWritten + " 字节");
            }

            // 如果数据写完了，取消写事件监听
//            if (!clientInfo.hasDataToWrite()) {
//                key.interestOps(SelectionKey.OP_READ);
//            }
            key.interestOps(SelectionKey.OP_READ);
        } else {
            // 没有数据要写，取消写事件监听
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    /**
     * 关闭客户端连接
     */
    private void closeClient(SelectionKey key) {
        try {
            SocketChannel clientChannel = (SocketChannel) key.channel();
            clientMap.remove(clientChannel);
            connectionCount.decrementAndGet();

            key.cancel();
            clientChannel.close();
        } catch (IOException e) {
            System.err.println("关闭客户端连接时出错: " + e.getMessage());
        }
    }

    /**
     * 打印服务器统计信息
     */
    private void printStats() {
        // 每1000次循环打印一次统计信息
        if (System.currentTimeMillis() % 10000 < 100) {
            System.out.println("=== 服务器状态 ===");
            System.out.println("当前连接数: " + connectionCount.get());
            System.out.println("注册的Channel数: " + selector.keys().size());
        }
    }

    /**
     * 关闭服务器
     */
    public void shutdown() throws IOException {
        if (selector != null) {
            selector.close();
        }
        if (serverChannel != null) {
            serverChannel.close();
        }
        System.out.println("服务器已关闭");
    }

    /**
     * 客户端信息类
     */
    private static class ClientInfo {
        private final SocketChannel channel;
        private final ByteBuffer writeBuffer;
        private final long connectTime;

        public ClientInfo(SocketChannel channel) {
            this.channel = channel;
            this.writeBuffer = ByteBuffer.allocate(BUFFER_SIZE * 4);
            this.connectTime = System.currentTimeMillis();
        }

        public void addToWriteBuffer(String message) {
            synchronized (writeBuffer) {
                byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
                if (writeBuffer.remaining() >= bytes.length) {
                    writeBuffer.put(bytes);
                }
            }
        }

        public ByteBuffer getWriteBuffer() {
            synchronized (writeBuffer) {
                writeBuffer.flip();
                return writeBuffer;
            }
        }

        public boolean hasDataToWrite() {
            synchronized (writeBuffer) {
                return writeBuffer.position() > 0 || writeBuffer.hasRemaining();
            }
        }

        public long getConnectTime() {
            return connectTime;
        }
    }

    /**
     * 主函数 - 启动服务器
     */
    public static void main(String[] args) {
        NIOSelectorServer server = new NIOSelectorServer("localhost", 8080);

        // 添加关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.shutdown();
            } catch (IOException e) {
                System.err.println("关闭服务器时出错: " + e.getMessage());
            }
        }));

        try {
            server.start();
        } catch (IOException e) {
            System.err.println("启动服务器失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
