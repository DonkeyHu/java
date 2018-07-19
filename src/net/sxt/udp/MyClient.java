package net.sxt.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class MyClient {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds=new DatagramSocket(6666);
		String str="UDP编程~";
		byte[] b=str.getBytes();
		DatagramPacket dp=new DatagramPacket(b,b.length,new InetSocketAddress("localhost",8888));
		ds.send(dp);
		ds.close();
	}
}
