package classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String host="localhost";
		int puerto=6000;
		int intentos=20;
		int contadorAciertos=0;
		System.out.println("PROGRAMA CLIENTE INICIADO...");
		try {
			Socket cliente=new Socket(host,puerto);
			DataOutputStream flujoSalida=new DataOutputStream(cliente.getOutputStream());
			DataInputStream flujoEntrada=new DataInputStream(cliente.getInputStream());
			String numeroBinario="", resultado="";
			int i=1;
			do {
				System.out.println("Intento "+i+". Introduce una posicion: ");
				numeroBinario=s.next();
				flujoSalida.writeUTF(numeroBinario);
				resultado=flujoEntrada.readUTF();
				System.out.println("Recibiendo resultado del servidor: "+resultado);
				if(resultado.equals("Hundido"))contadorAciertos++;
				intentos--;
				i++;
			}while(intentos>0 && contadorAciertos<10);
			if(contadorAciertos==10)System.out.println("El cliente ha encontrado todos los barcos. Desconectando del servidor");
			else if(intentos==0) System.out.println("El cliente se ha quedado sin intentos. Desconectando del servidor");
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
