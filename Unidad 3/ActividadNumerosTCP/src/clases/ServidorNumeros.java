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
			Numeros numero = new Numeros();
			do {
				ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
				numero = (Numeros) inObjeto.readObject();
				System.out.println("Recibido: "+numero.getNumero());
				numero = calcularAtributosNumero(numero);
			} while (numero.getNumero() > 0);
			System.out.println("0 detectado, terminando servidor");
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
