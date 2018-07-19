package net.sxt.tcp.chat.server2multi_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	List<Channel> list=new ArrayList<Channel>();
	
	public static void main(String[] args) throws IOException {
		new Server().start();
	}

	public void start() throws IOException {
		ServerSocket server = new ServerSocket(9999);
		while (true) {
			Socket client = server.accept();
			Channel channel = new Channel(client);
			list.add(channel);
			new Thread(channel).start();
		}
	}

	class Channel implements Runnable {
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean flag = true;

		public Channel() {

		}

		public Channel(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				// e.printStackTrace();
				CloseUtil.closeAll(dos, dis);
				flag = false;
			}
		}

		private String recieve() {
			String meg = "";
			try {
				meg = dis.readUTF();
			} catch (IOException e) {
				// e.printStackTrace();
				CloseUtil.closeAll(dis);
				flag = false;
				list.remove(this);
			}
			return meg;
		}

		public void send(String meg) {
			if (meg != null && !meg.equals("")) {
				try {
					dos.writeUTF("服务器" + meg);
				} catch (IOException e) {
					// e.printStackTrace();
					CloseUtil.closeAll(dos);
					flag = false;
					list.remove(this);
				}
			}
		}
		public void sendOther() {
			String meg=this.recieve();
			for(Channel client:list) {
				if(client==this) {
					continue;
				}
				client.send(meg);
			}
		}
		@Override
		public void run() {
			while (flag) {
				sendOther();
			}
		}

	}
}
