package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class PollClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9595));

        ByteBuffer readBuffer = ByteBuffer.allocate(256);
        ByteBuffer writeBuffer  = ByteBuffer.allocate(256);
        writeBuffer.put("Hello World".getBytes());
        writeBuffer.flip();

        while (true){
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);
            readBuffer.clear();
            socketChannel.read(readBuffer);
            System.out.println("Client:"+new String(readBuffer.array()));
        }
    }
}
