package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver extends Thread{
	
	private DataInputStream dis;
	
	public Receiver(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dis != null) {
			
			try {
				System.out.println(dis.readUTF());	// 상대방이 writeUTF 다 쓸때까지 block되고 읽고 다시 block -> 반복
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
