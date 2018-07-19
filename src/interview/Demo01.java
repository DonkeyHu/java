package interview;

import java.util.ArrayList;

public class Demo01 {
	public static void main(String[] args) {
		ArrayList<String> list=getSubset("abcde", 3);
		for (String s : list) {
			System.out.println(s);
		}
	}
	
	public static ArrayList<String> getSubset(String s,int num){
		ArrayList<String> result=new ArrayList<>();
		if(num==1) {
			
		}
		int len=s.length();
		char lastc=' ';
		for(int i=0;i<(len-num+1);i++) {
			char c=s.charAt(i);
			if(lastc==c) {
				continue;
			}
			lastc=c;
			ArrayList<String> al=getSubset(s.substring(i+1),num-1);
			for (String ss : al) {
				result.add(ss+c);
			}
		}
		return result;
	}
}
