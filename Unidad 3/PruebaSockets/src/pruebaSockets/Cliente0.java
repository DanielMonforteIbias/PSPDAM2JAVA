package pruebaSockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente0 {
	public static void main(String[] args) {
		String host="localhost";
		int puerto=6000;
		Socket cliente;
		try {
			cliente=new Socket(host,puerto);
			InetAddress ip=cliente.getInetAddress();
			System.out.println("Puerto local: "+cliente.getLocalPort());
			System.out.println("Puerto remoto: "+cliente.getPort());
			System.out.println("Host remoto: "+ip.getHostName());
			System.out.println("IP Host Remoto: "+ip.getHostAddress());
			cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}