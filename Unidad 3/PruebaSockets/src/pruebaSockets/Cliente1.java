package pruebaSockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente1 {
	public static void main(String[] args) {
		String host="localhost";
		int puerto=6000;
		System.out.println("PROGRAMA CLIENTE INICIADO...");
		try {
			Socket cliente=new Socket(host,puerto);
			DataOutputStream flujoSalida=new DataOutputStream(cliente.getOutputStream());
			DataInputStream flujoEntrada=new DataInputStream(cliente.getInputStream());
			String cadena=flujoEntrada.readUTF();
			System.out.println("Recibiendo del servidor:\n\t"+cadena);
			flujoSalida.writeUTF(cadena);
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
