package net.sxt.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(9999);
		Socket client=server.accept();
		
		DataInputStream dis=new DataInputStream(client.getInputStream());
		String meg=dis.readUTF();
		
		DataOutputStream dos=new DataOutputStream(client.getOutputStream());
		dos.writeUTF("服务器--->"+meg);
		dos.flush();
	}
}
