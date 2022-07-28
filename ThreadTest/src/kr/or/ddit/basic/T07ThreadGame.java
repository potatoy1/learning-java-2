package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class T07ThreadGame {
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		CountDown1 count = new CountDown1();
		count.start();

		String user = JOptionPane.showInputDialog("가위바위보 중에 입력하세요.");
		T07ThreadGame.inputCheck = true;
		
		String[] computerArr = {"가위", "바위", "보"};
		Random random = new Random();
		int ranNum = random.nextInt(3);
		String computer = computerArr[ranNum];

		
		String result = "";
		if (user.equals(computer)) {
			result = "비겼습니다.";
		} else if (user.equals("가위") && computer.equals("보") || user.equals("바위") && computer.equals("가위")
				|| user.equals("보") && computer.equals("바위")) {
			result = "당신이 이겼습니다.";
		} else {
			result = "컴퓨터가 이겼습니다.";
		}
		System.out.println("========결과========");
		System.out.println("컴퓨터 : "+ computer);
		System.out.println("사용자 : "+ user);
		System.out.println(result);
	}
}

class CountDown1 extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			
			if(T07ThreadGame.inputCheck) {
				return;	
			}
			
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("5초가 지났습니다. 패배하였습니다.");
		System.exit(0);
	}
}
