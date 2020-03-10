package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9595));

        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        byteBuffer.put("Hello World".getBytes());

        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }
}
