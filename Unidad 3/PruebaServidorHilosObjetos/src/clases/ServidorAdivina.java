package clases;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAdivina {
	public static void main(String[] args) throws IOException {
		ServerSocket servidor=new ServerSocket(6001);
		System.out.println("Servidor iniciado");
		
		int numero=(int)(Math.random()*25+1);
		System.out.println("NUMERO SECRETO=> "+numero);
		
		ObjetoCompartido objeto=new ObjetoCompartido(numero);
		int id=0;
		while(true) {
			Socket cliente=new Socket();
			cliente=servidor.accept();
			id++;
			HiloServidorAdivina hilo=new HiloServidorAdivina(cliente,objeto,id);
			hilo.start();
		}
	}
}