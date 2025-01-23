package clases;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat {
	static final int MAXIMO=5;
	public static void main(String[] args) throws IOException {
		int puerto=4444;
		ServerSocket servidor=new ServerSocket(puerto);
		System.out.println("Servidor iniciado...");
		Socket tabla[]=new Socket[MAXIMO];
		ComunHilos comun=new ComunHilos(MAXIMO,0,0,tabla);
		while(comun.getConexiones()<MAXIMO) {
			Socket socket=new Socket();
			socket=servidor.accept();
			comun.addTabla(socket,comun.getConexiones());
			comun.setActuales(comun.getActuales()+1);
			comun.setConexiones(comun.getConexiones()+1);
			HiloServidorChat hilo=new HiloServidorChat(socket,comun);
			hilo.start();
		}
		servidor.close();
	}
}
