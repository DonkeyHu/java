package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class WebServer2 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1",9595));
        ssc.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);

        while (true){
            SocketChannel socketChannel = ssc.accept();
            System.out.println("AAA");

            if (socketChannel == null){
                System.out.println("BBB");
            }else{
                socketChannel.read(byteBuffer);
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.println((char) byteBuffer.get());
                }
            }
        }
    }
}
