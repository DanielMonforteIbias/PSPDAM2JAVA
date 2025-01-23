package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.naming.ReferralException;

public class ServidorPalabraSecreta {
	static String palabraSecreta="gato";
	static int intentos=4;
	public static void main(String[] args) {
		int numeroPuerto=6000;
		ServerSocket servidor=null;
		try {
			servidor=new ServerSocket(numeroPuerto);
			Socket clienteConectado=null;
			System.out.println("Esperando al cliente");
			clienteConectado=servidor.accept();
			System.out.println("Cliente "+clienteConectado.getInetAddress().getHostName()+" aceptado");
			OutputStream salida=null;
			salida=clienteConectado.getOutputStream();
			ObjectOutputStream flujoSalida=new ObjectOutputStream(salida);
			InputStream entrada=null;
			DataInputStream flujoEntrada;
			String mensaje="";
			boolean seguir=true, correcto=false;
			do {
				entrada=clienteConectado.getInputStream();
				flujoEntrada=new DataInputStream(entrada);
				String palabraCliente=flujoEntrada.readUTF();
				System.out.println("Palabra recibida del cliente: "+palabraCliente);
				if(palabraCliente.equalsIgnoreCase(palabraSecreta)){
					mensaje="Has ganado! La palabra secreta era "+palabraCliente;
					correcto=true;
					seguir=false;
				}
				else {
					intentos--;
					mensaje="Incorrecto! Intentos restantes: "+intentos;
					correcto=false;
					seguir=true;
				}
				Resultado resultado=new Resultado(palabraCliente,mensaje,correcto);
				if(intentos<=0) seguir=false;
				flujoSalida.writeObject(resultado);
			}while(seguir);
			if(intentos==0) System.out.println("El servidor gana, no se ha adivinado la palabra");
			else System.out.println("El cliene gana, se ha adivinado la palabra");
			System.out.println("Terminando servidor");
			entrada.close();
			flujoEntrada.close();
			salida.close();
			flujoSalida.close();
			clienteConectado.close();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
