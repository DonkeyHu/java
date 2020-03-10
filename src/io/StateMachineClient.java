package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class StateMachineClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9595));

        ByteBuffer writeBuffer  = ByteBuffer.allocate(256);
        ByteBuffer readBuffer = ByteBuffer.allocate(256);

        Random r = new Random();
        int d = 0;

        d = r.nextInt(100);
        System.out.println(d);
        writeBuffer.put(String.valueOf(d).getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        socketChannel.read(readBuffer);
        readBuffer.flip();
        System.out.println(new String(readBuffer.array()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        d = r.nextInt(100);
        System.out.println(d);
        writeBuffer.clear();
        writeBuffer.put(String.valueOf(d).getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        readBuffer.clear();
        socketChannel.read(readBuffer);
        readBuffer.flip();
        System.out.println(new String(readBuffer.array()));

        socketChannel.close();
    }
}
