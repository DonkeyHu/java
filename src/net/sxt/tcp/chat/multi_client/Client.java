package net.sxt.tcp.chat.multi_client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 多人聊天室
 * 目的：要创建多个客户端与服务器相连，且每个客户端独立不相互影响
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client=new Socket("localhost",9999);
		//发送数据+接受数据
		new Thread(new Send(client)).start();
		new Thread(new Recieve(client)).start();
	}
}
