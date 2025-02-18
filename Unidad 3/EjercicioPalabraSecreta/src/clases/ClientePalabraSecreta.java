package clases;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientePalabraSecreta {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String host="localhost";
		int puerto=6000;
		System.out.println("PROGRAMA CLIENTE INICIADO...");
		try {
			Socket cliente=new Socket(host,puerto);
			DataOutputStream flujoSalida=new DataOutputStream(cliente.getOutputStream());
			ObjectInputStream flujoEntrada=new ObjectInputStream(cliente.getInputStream());
			String palabraCliente="";
			Resultado resultado=new Resultado();
			int intentos=4;
			boolean seguir=true;
			System.out.println("Trata de adivinar la palabra secreta, tienes 4 intentos");
			do {
				System.out.println("Introduce una palabra: ");
				palabraCliente=s.next();
				flujoSalida.writeUTF(palabraCliente);
				try {
					resultado=(Resultado) flujoEntrada.readObject();
					System.out.println(resultado.getMensaje());
					if(resultado.isCorrecto()) {
						seguir=false;
					}
					else intentos--;
				} catch (ClassNotFoundException e) {
					System.out.println("Error al obtener el resultado");
					e.printStackTrace();
				}
				
				if(intentos==0) seguir=false;
			}while(seguir); //Se compara con el resultado y no con numeroBinario porque se si pusiesen inputs como 00 o 000 tambien se deberia cerrar
			if(intentos==0) System.out.println("El cliente ha perdido");
			else System.out.println("El cliente ha ganado");
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
		s.close();
	}
}
