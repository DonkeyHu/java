package net.sxt.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetDemo01 {
	public static void main(String[] args) {
		try {
			InetAddress addr=InetAddress.getLocalHost();
			System.out.println(addr.getHostAddress());
			System.out.println(addr.getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
