package thread.sxt.status;

public class StopDemo01 {
    public static void main(String[] args) {
	Study s=new Study();
	new Thread(s).start();
	for(int i=0;i<100;i++){
		if(i==50){
			s.stop();
		}
		System.out.println("main is..."+i);
	}
}
}
class Study implements Runnable{
    private boolean flag=true;
	@Override
	public void run() {
		while(flag){
			System.out.println("Thread.....");
		}
	}
	public void stop(){
		this.flag=false;
	}
}
