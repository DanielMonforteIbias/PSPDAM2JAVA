package clases;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(6000);
		System.out.println("Servidor iniciado...");
		while(true) {
			Socket cliente=new Socket();
			cliente=server.accept();
			HiloServidor hilo=new HiloServidor(cliente);
			hilo.start();
		}
	}
}