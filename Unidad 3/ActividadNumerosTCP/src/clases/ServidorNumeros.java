package clases;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorNumeros {
	public static void main(String[] args) {
		int numeroPuerto = 9876;// Puerto
		ServerSocket servidor;
		try {
			servidor = new ServerSocket(numeroPuerto);
			System.out.println("Esperando al cliente.....");
			Socket cliente = servidor.accept();
			ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
			ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
			Numeros numero = new Numeros();
			boolean seguir=true;
			do {
				numero = (Numeros) inObjeto.readObject();
				if(numero!=null) {
					System.out.println("Recibido: "+numero.getNumero());
					numero = calcularAtributosNumero(numero);
					outObjeto.writeObject(numero);
					if(numero.getNumero() <=0) seguir=false;
				}
				else {
					System.out.println("Recibido dato no valido");
					outObjeto.writeObject(null);
				}
			} while (seguir);
			System.out.println("Terminando servidor");
			outObjeto.close();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Numeros calcularAtributosNumero(Numeros numero) {
		numero.setCuadrado((long) (Math.pow(numero.getNumero(), 2)));
		numero.setCubo((long) (Math.pow(numero.getNumero(), 3)));
		return numero;
	}
}
