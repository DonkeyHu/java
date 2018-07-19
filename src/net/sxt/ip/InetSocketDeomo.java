package net.sxt.ip;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetSocketDeomo {
	public static void main(String[] args) {
		InetSocketAddress isa=new InetSocketAddress("127.0.0.1",9999);
		System.out.println(isa.getHostName());
		System.out.println(isa.getPort());
		
		InetAddress add=isa.getAddress();
		System.out.println(add.getHostName());
	}
}
