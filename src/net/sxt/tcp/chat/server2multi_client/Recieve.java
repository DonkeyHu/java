package net.sxt.tcp.chat.server2multi_client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Recieve implements Runnable{
	private DataInputStream dis=null;
	private boolean isRunning=true;
	
	public Recieve() {
		
	}
	public Recieve(Socket s) {
		try {
			dis=new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			CloseUtil.closeAll(dis);
			isRunning=false;
		}
	}
	
	public String recieve() {
		String meg="";
		try {
			meg=dis.readUTF();
		} catch (IOException e) {
			//e.printStackTrace();
			CloseUtil.closeAll(dis);
			isRunning=false;
		}
		return meg;
	}
	@Override
	public void run() {
		while(isRunning) {
			System.out.println(recieve());
		}
	}
	
	
	
}
