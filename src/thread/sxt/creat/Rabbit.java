package thread.sxt.creat;
/**
 * run(){里面才是线程体}
 * @author 东东
 *
 */
public class Rabbit extends Thread{
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
		System.out.println("兔子走了"+i+"步");
	}
	}
	
}
 class Tortoise extends Thread{
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
		System.out.println("乌龟走了"+i+"步");
	}
	}
}
