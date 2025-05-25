package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 8080));

        // 发送测试消息
        String message = "Hello from client!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        client.write(buffer);

        // 读取服务器响应
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int bytesRead = client.read(readBuffer);
        if (bytesRead > 0) {
            readBuffer.flip();
            String response = StandardCharsets.UTF_8.decode(readBuffer).toString();
            System.out.println("服务器响应: " + response);
        }

        client.close();
    }
}
