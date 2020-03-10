package io;

import net.sxt.tcp.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class WebServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1",9595));
        SocketChannel socketChannel = ssc.accept();
        System.out.println("AAA");
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        socketChannel.read(byteBuffer);

        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println((char) byteBuffer.get());
        }
        socketChannel.close();
        ssc.close();
    }
}
