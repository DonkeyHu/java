package net.sxt.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
	
	public static String getURL(String urlStr) {
		StringBuilder sb=new StringBuilder();
		try {
			URL url=new URL(urlStr);
			BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream(),Charset.forName("utf-8")));
			String temp="";
			while((temp=br.readLine())!=null) {
				sb.append(temp);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	public static List<String> getSpider(String regex,String destStr){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(destStr);
		List<String> list=new ArrayList();
		while(m.find()) {
			list.add(m.group(1));
		}
		return list;
	}
	
	public static void main(String[] args) {
		String url="http://news.baidu.com";
		String str=getURL(url);
		List<String> result=getSpider("href=\"(.+?)\"", str);
		for (String string : result) {
			System.out.println(string);
		}
		
	}
}
