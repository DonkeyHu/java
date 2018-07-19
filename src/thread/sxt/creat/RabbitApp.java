package thread.sxt.creat;

public class RabbitApp {

	public static void main(String[] args) {
        Rabbit r=new Rabbit();
        r.start();
        Tortoise t=new Tortoise();
        t.start();
        for(int i=0;i<1000;i++){
        	System.out.println("main线程走了"+i+"步！");
        }
	}

}
