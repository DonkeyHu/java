package thread.sxt.creat;
/**
 * 为何能调用前面的重写方法？懂了，地址赋值过去了！
 * @author 东东
 *
 */
public class StaticProxy {

	public static void main(String[] args) {
       You you=new You();
       WeddingCompany wc=new WeddingCompany(you);
       wc.marry();
	}

}
interface Marry{
	public abstract void marry();
}
class You implements Marry{
	@Override
	public void marry() {
		System.out.println("我和D.C结婚了！");
	}
	
}
class WeddingCompany implements Marry{
	private Marry you;
	public WeddingCompany(){
		
	}
	public WeddingCompany(Marry you){
		this.you=you;
	}
	public void before(){
		System.out.println("婚前准备");
	}
	public void after(){
		System.out.println("婚后xx");
	}
	@Override
	public void marry() {
        before();
        you.marry();
        after();
	}
}