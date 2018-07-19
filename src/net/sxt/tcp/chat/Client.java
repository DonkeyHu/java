package net.sxt.tcp.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 多人聊天室
 * 目的：要创建多个客户端与服务器相连，且每个客户端独立不相互影响(目前不符合要求....)
 *
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client=new Socket("localhost",9999);
		//发送数据+接受数据
		
		//表示接受控制台的输入信息
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		
		DataOutputStream dos=new DataOutputStream(client.getOutputStream());
		dos.writeUTF(s);
		dos.flush();
		
		DataInputStream dis=new DataInputStream(client.getInputStream());
		String meg=dis.readUTF();
		System.out.println(meg);
	}
}
