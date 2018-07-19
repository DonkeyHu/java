package net.sxt.tcp.chat.multi_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{
	private BufferedReader br=null;
	private DataOutputStream dos=null;
	private boolean isRunning=true;
	
	public Send() {
		br=new BufferedReader(new InputStreamReader(System.in));
	}
	public Send(Socket socket) {
		//这里调用了无参构造函数，很关键！！
		this();
		try {
			dos=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			CloseUtil.closeAll(dos,br);
			isRunning=false;
		}
	}
	
	private String getMessageFromCosole() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	public void send() {
		String meg=getMessageFromCosole();
		//字符串不为null且字符串不为空字符串--->主要针对控制台打印
		if(meg!=null && !meg.equals("")) {
			try {
				dos.writeUTF(meg);
			} catch (IOException e) {
				//e.printStackTrace();
				CloseUtil.closeAll(dos);
				isRunning=false;
			}
		}
	}
	public void run() {
		while(isRunning) {
			send();
		}
	}
	
}
