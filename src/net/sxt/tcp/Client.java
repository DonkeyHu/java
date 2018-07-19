package net.sxt.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//1.创建客户端  指定服务器+端口
		Socket client=new Socket("localhost",8888);
		//2.接受数据+发送数据
		/*
		BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
		String s=br.readLine();//阻塞式方法
		System.out.println(s);
		*/
		DataInputStream dis=new DataInputStream(client.getInputStream());
		String s=dis.readUTF();
		System.out.println(s);
	}
}
