package clases;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
	public static void main(String[] args) throws IOException {
		InetAddress destino = InetAddress.getLocalHost();
		int port = 12345; // puerto al que envio
		byte[] mensaje = new byte[1024];
		String saludo = "Enviando Saludos !!";
		mensaje = saludo.getBytes();
		// construyo el datagrama que se va a enviar
		DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
		DatagramSocket socket = new DatagramSocket(34567);

		System.out.println("Enviando Datagrama de longitud: " + mensaje.length);
		System.out.println("Host destino: " + destino.getHostName());
		System.out.println("IP destino: " + destino.getHostAddress());
		System.out.println("Puerto local del socket: " + socket.getLocalPort());
		System.out.println("Puerto al que envio: " + envio.getPort());

		socket.send(envio);
		socket.close();
	}
}
