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
		Numeros numero=new Numeros(), resultado=new Numeros();
		int n=0;
		boolean seguir=true;
		do {
			System.out.println("Introduce un numero: ");
			if(s.hasNextInt()) {
				n=s.nextInt();
				numero.setNumero(n);
				salida.writeObject(numero);
			}
			else {
				salida.writeObject(null);
				s.next(); //Saltamos al siguiente del escaner para no entrar en un bucle infinito
			}
			salida.flush();
			salida.reset();
			resultado=(Numeros)entrada.readObject();
			if(resultado!=null) {
				System.out.println("Cuadrado de "+resultado.getNumero()+": "+resultado.getCuadrado()+", Cubo de "+resultado.getNumero()+": "+resultado.getCubo());
				if(numero.getNumero()<=0) seguir=false;
			}
			else System.out.println("El dato enviado al servidor no fue valido");
		}while(seguir);
		System.out.println("Terminando cliente");
		entrada.close();
		salida.close();
		cliente.close();
		s.close();
	}
}