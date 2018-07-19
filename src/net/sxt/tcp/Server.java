package net.sxt.tcp;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 必须要先创建服务器，再创建客户端，这一点是与UDP协议的区别！
 * @author 东东
 *
 */
public class Server {
	public static void main(String[] args) throws IOException {
		//1.创建服务器，指定端口
		ServerSocket server=new ServerSocket(8888);
		//2.接受客户端连接  阻塞式?-->就是指线程不运行状态，因为是监听此端口，没来请求时必然是阻塞挂起状态！
		Socket socket=server.accept();
		System.out.println("一个客户端建立连接");
		//3.发送数据+接受数据
		String msg="欢迎使用！";
		/*
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		bw.write(msg);
		bw.newLine();//这里必须要newLine
		bw.flush();
		*/
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
	}
}
