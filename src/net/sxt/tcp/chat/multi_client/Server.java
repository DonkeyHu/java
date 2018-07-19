package net.sxt.tcp.chat.multi_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9999);
		while (true) {
			Socket client = server.accept();
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			while (true) {
				String meg = dis.readUTF();
				dos.writeUTF("服务器--->" + meg);
				dos.flush();
			}
		}
	}
}
