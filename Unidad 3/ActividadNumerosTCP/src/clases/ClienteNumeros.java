package clases;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteNumeros {
	public static void main(String[] arg) throws IOException, ClassNotFoundException {
		Scanner s=new Scanner(System.in);
		String Host = "localhost";
		int Puerto = 9876;// puerto remoto
		System.out.println("PROGRAMA CLIENTE INICIADO....");
		Socket cliente = new Socket(Host, Puerto);
		//Flujo de entrada para objetos
		ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
		ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
		Numeros numero=new Numeros();
		do {
			System.out.println("Introduce un numero: ");
			int n=s.nextInt();
			numero.setNumero(n);
			salida.writeObject(numero);
		}while(numero.getNumero()>0);
		entrada.close();
		salida.close();
		cliente.close();
	}
}