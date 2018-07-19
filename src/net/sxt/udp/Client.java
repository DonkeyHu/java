package net.sxt.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds=new DatagramSocket(6666);
		byte[] b=convert(82.30);
		DatagramPacket dp=new DatagramPacket(b,b.length,new InetSocketAddress("localhost",8888));
		ds.send(dp);
		ds.close();
	}
	/**
	 * 数据是基本类型
	 * @throws IOException 
	 */
	public static byte[] convert(double b) throws IOException{
		byte[] data=null;
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		DataOutputStream dos=new DataOutputStream(bos);
		dos.writeDouble(b);
		dos.flush();
		//这里才是把流输出到指定位置
		data=bos.toByteArray();
		return data;
	}
}
