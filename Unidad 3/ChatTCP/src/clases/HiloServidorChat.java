package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidorChat extends Thread {
	DataInputStream fEntrada;
	Socket socket = null;
	ComunHilos comun;

	public HiloServidorChat(Socket socket, ComunHilos comun) {
		this.socket = socket;
		this.comun = comun;
		try {
			fEntrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("NUMERO DE CONEXIONES ACTUALES: "+comun.getActuales());
		String texto=comun.getMensajes();
		EnviarMensajesATodos(texto);
		while(true) {
			String cadena="";
		try {
				cadena=fEntrada.readUTF();
				if(cadena.trim().equals("*")) {
					comun.setActuales(comun.getActuales()-1);
					System.out.println("NUMERO DE CONEXIONES ACTUALES: "+comun.getActuales());
				}
				comun.setMensajes(comun.getMensajes()+cadena+"\n");
				EnviarMensajesATodos(comun.getMensajes());
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}
		//Se cierra el socket del cliente
		try {
			socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void EnviarMensajesATodos(String texto) {
		for(int i=0;i<comun.getConexiones();i++) {
			Socket s1=comun.getElementoTabla(i);
			if(!s1.isClosed()) {
				try {
					DataOutputStream fSalida=new DataOutputStream(s1.getOutputStream());
					fSalida.writeUTF(texto);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}