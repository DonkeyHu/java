package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class StateMachineServer {
        public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1",9595));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer readBuffer = ByteBuffer.allocate(256);
        ByteBuffer writeBuffer = ByteBuffer.allocate(256);
        writeBuffer.put("receivedDate".getBytes());
        writeBuffer.flip();

        while (true){
            int nReady = selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()){
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    selectionKey.attach(new StateMachineTask(socketChannel,selectionKey));
                }else if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();

                    StateMachineTask task = (StateMachineTask) key.attachment();
                    task.onRead(getInt(readBuffer));

                    key.interestOps(SelectionKey.OP_WRITE);
                }else if (key.isWritable()){
                    StateMachineTask task = (StateMachineTask) key.attachment();
                    key.interestOps(SelectionKey.OP_READ);
                    task.onWrite();
                }

            }
        }

    }

    public static int getInt(ByteBuffer buf) {
        int r = 0;
        while (buf.hasRemaining()) {
            r *= 10;
            r += buf.get() - '0';
        }
        return r;
    }
}