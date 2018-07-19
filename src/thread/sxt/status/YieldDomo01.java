package thread.sxt.status;

public class YieldDomo01 extends Thread{
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
			System.out.println("yield__"+i);
		}
	}
	 public static void main(String[] args) throws InterruptedException {
			JoinDemo01 j=new JoinDemo01();
			j.start();
			for(int i=0;i<1000;i++){
				if(i%50==0){
					Thread.yield();//main方法阻塞
				}
				System.out.println("main__"+i);
			}
		}
}
