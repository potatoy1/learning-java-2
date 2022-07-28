package kr.or.ddit.basic;

public class T13ThreadStopTest {
	public static void main(String[] args) {
		ThreadStopEx1 th = new ThreadStopEx1();
		th.start();
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		//th.stop();	// 다른 방법 이용해주세요---
		th.setStop(true);
	}
}


class ThreadStopEx1 extends Thread{
	
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리 중...");
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}
