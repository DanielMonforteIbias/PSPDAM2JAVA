package ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String host="localhost";
		int puerto=6000;
		try {
			Socket cliente=new Socket(host,puerto);
			System.out.println("Cliente conectado a "+host);
			DataOutputStream flujoSalida=new DataOutputStream(cliente.getOutputStream());
			DataInputStream flujoEntrada=new DataInputStream(cliente.getInputStream());
			String correo="", resultado="";
			do {
				System.out.println("Escribe un correo (FIN PARA TERMINAR): ");
				correo=s.next();
				flujoSalida.writeUTF(correo);
				resultado=flujoEntrada.readUTF();
				if(!correo.equalsIgnoreCase("FIN"))System.out.println(resultado);
			}while(!correo.trim().equalsIgnoreCase("FIN"));
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			System.out.println("Desconectando del servidor...");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
