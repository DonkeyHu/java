package net.sxt.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MyServer {
	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket ds=new DatagramSocket(8888);
		byte[] b=new byte[1024];
		DatagramPacket dp=new DatagramPacket(b,b.length);
		ds.receive(dp);
		byte[] data=dp.getData();
		int len=dp.getLength();
		String s=new String(data,0,len);
		System.out.println(s);
		ds.close();
	}
}
