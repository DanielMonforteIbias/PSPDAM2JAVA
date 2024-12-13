package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteBinario {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String host="localhost";
		int puerto=6000;
		System.out.println("PROGRAMA CLIENTE INICIADO...");
		try {
			Socket cliente=new Socket(host,puerto);
			DataOutputStream flujoSalida=new DataOutputStream(cliente.getOutputStream());
			DataInputStream flujoEntrada=new DataInputStream(cliente.getInputStream());
			String numeroBinario="", resultado="";
			do {
				System.out.println("Introduce un numero decimal: ");
				numeroBinario=s.next();
				flujoSalida.writeUTF(numeroBinario);
				resultado=flujoEntrada.readUTF();
				System.out.println("Recibiendo resultado binario del servidor: "+resultado);
			}while(!resultado.equals("0")); //Se compara con el resultado y no con numeroBinario porque se si pusiesen inputs como 00 o 000 tambien se deberia cerrar
			System.out.println("Desconectando del servidor");
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();	
		} catch (java.net.ConnectException e) {
			System.out.println("No se pudo conectar al servidor");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
