package net.sxt.url;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo01 {
	/**
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		/*
		URL url=new URL("http://www.baidu.com:80/index.html#aa?name=bjsxt");
		System.out.println("锚点："+url.getRef());
		System.out.println("参数："+url.getQuery());
		*/
		URL url=new URL("http://www.baidu.com");
		try {
			InputStream is=url.openStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu1.html"),"utf-8"));
			
			String s=null;
			while(null!=(s=br.readLine())){
				//System.out.println(s);
				bw.append(s);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
