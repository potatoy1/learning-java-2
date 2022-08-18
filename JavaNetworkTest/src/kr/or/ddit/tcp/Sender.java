package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
	 
	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	
	
	public Sender(Socket socket) {
		
		name = "[" + socket.getInetAddress() + " : " + socket.getLocalPort() +"]";
		scan = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());	// writeUTF메서드 호출
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(dos != null) {
			try {
				dos.writeUTF(name + ">>> "+ scan.nextLine()); 	// 입력받을 때 까지 block상태
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
