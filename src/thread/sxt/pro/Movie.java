package thread.sxt.pro;
/**
 * ����������������
 * �źŵƷ�
 * @author ����
 *
 */
public class Movie {
    private String pic;
	private boolean flag=true;
	//---->T �����������������ߵȴ���������֪ͨ������
	//---->F ���������ѣ������ߵȴ���������֪ͨ������
	public synchronized void  play(String pic){
		if(!flag){ //�����ߵȴ�
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ��ʼ����
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�����ˣ�"+pic);
    	this.pic=pic;
    	//֪ͨ����
    	this.notify();
    	//ֹͣ����
    	this.flag=false;
    }
	public synchronized void watch(){
		//�����ߵȴ�
		if(flag){  
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//����������
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������:"+pic);
		//֪ͨ����
		this.notifyAll();
		//����ͣ��
		this.flag=true;
	}
}
