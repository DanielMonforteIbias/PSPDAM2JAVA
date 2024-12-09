package pruebaSockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {
	public static void main(String[] args) {
		int numeroPuerto=6000;
		ServerSocket servidor=null;
		try {
			servidor=new ServerSocket(numeroPuerto);
			Socket clienteConectado=null;
			System.out.println("Esperando al cliente");
			clienteConectado=servidor.accept();
			OutputStream salida=null;
			salida=clienteConectado.getOutputStream();
			DataOutputStream flujoSalida=new DataOutputStream(salida);
			flujoSalida.writeUTF("Saludos al cliente del servidor");
			InputStream entrada=null;
			entrada=clienteConectado.getInputStream();
			DataInputStream flujoEntrada=new DataInputStream(entrada);
			System.out.println("Recibiendo del cliente: \n\t"+flujoEntrada.readUTF());
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
