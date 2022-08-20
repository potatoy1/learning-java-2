package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 데이터가 저장될 공간으로 byte배열을 생성함.
	
	public UdpClient() {
		msg = new byte[100];
		
		try {
			// 포트번호를 지정하지 않으면 이용가능한 임의의 포트번호 할당됨.(파일전송하면 포트번호 알아낼수 있으므로 포트번호 지정안함. 해도는됨)
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
			InetAddress serverAddr = InetAddress.getByName("192.168.142.3");
			dp = new DatagramPacket(msg, 1, serverAddr, 8888);
			ds.send(dp); // 패킷 전송
			
			dp= new DatagramPacket(msg, msg.length);
			ds.receive(dp); // 패킷 수신
			
			System.out.println("현재 서버 시간=> " + new String(dp.getData()));
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			ds.close(); // 소켓 종료.
		}
	}
	public static void main(String[] args) {
		new UdpClient().start();
	}
}
