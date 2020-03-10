package io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class StateMachineTask {
    private SocketChannel socketChannel;
    private SelectionKey key;
    private ByteBuffer byteBuffer;
    private int state;
    //被除数
    private int dividend;
    //除数
    private int divisor;
    private int result;

    public StateMachineTask(SocketChannel socketChannel, SelectionKey key) {
        this.socketChannel = socketChannel;
        this.key = key;
        byteBuffer = ByteBuffer.allocate(256);
    }

    public void onRead(int data){
        if (state == 0){
            dividend = data;
            System.out.println("dividend:"+dividend);
            state = 1;
        }else if (state ==2){
            divisor = data;
            System.out.println("divisor:"+divisor);
            if (divisor == 0){
                result = Integer.MAX_VALUE;
            }else {
                result = dividend/divisor;
            }
            state = 3;
        }else {
            throw new RuntimeException("wrong state " + state);
        }

    }

    public void onWrite(){
        try {
            if (state == 1){
                byteBuffer.clear();
                byteBuffer.put("divident".getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                state = 2;
            }else if (state == 3){
                byteBuffer.clear();
                byteBuffer.put(String.valueOf(result).getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                state = 4;
                socketChannel.close();
                key.cancel();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
