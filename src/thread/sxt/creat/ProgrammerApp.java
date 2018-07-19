package thread.sxt.creat;

public class ProgrammerApp {

	public static void main(String[] args) {
           Programmer p=new Programmer();
           Thread t=new Thread(p);
           t.start();
           for(int i=0;i<1000;i++){
        	   System.out.println("打游戏——————");
           }
	}
}
