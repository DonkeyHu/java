package net.sxt.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
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
		double d=convert(data);
		System.out.println(d);
		ds.close();
	}
	/**
	 * 
	 * @param b
	 * @return
	 * @throws IOException
	 */
	public static double convert(byte[] b) throws IOException{
		DataInputStream dis=new DataInputStream(new ByteArrayInputStream(b));
		double d=dis.readDouble();
		return d;
	}
}
