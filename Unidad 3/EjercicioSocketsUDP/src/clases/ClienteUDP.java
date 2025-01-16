package clases;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
	public static void main(String[] args) throws IOException {
		Scanner s=new Scanner(System.in);
		InetAddress destino = InetAddress.getLocalHost();
		int port = 12345; // puerto al que envio
		byte[] mensaje = new byte[1024];
		System.out.println("Introduce el texto: ");
		String texto = s.next();
		mensaje = texto.getBytes();
		// construyo el datagrama que se va a enviar
		DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
		DatagramSocket socket = new DatagramSocket(34567);
		System.out.println("Enviando datagrama con el contenido: "+new String(envio.getData()));
		socket.send(envio);
		
		byte bufer[] = new byte[1024];
		DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
		socket.receive(recibo);
		String textoRespuesta = new String(recibo.getData());// obtengo String
		System.out.println("Veces que aparece la a: "+textoRespuesta);
		socket.close();
	}
}