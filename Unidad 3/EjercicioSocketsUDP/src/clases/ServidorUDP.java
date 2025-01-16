package clases;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
	public static void main(String[] args) throws IOException {
		byte bufer[] = new byte[1024];
		DatagramSocket socket = new DatagramSocket(12345);
		System.out.println("Esperando Datagrama .......... ");
		DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
		socket.receive(recibo);// recibo datagrama
		String texto = new String(recibo.getData());// obtengo String
		System.out.println("Contenido del Paquete : " + texto.trim());
		int contador=0;
		for(int i=0;i<texto.length();i++)if(texto.charAt(i)=='a')contador++;
		byte respuesta[]=(contador+"").getBytes();
		DatagramPacket paqueteRespuesta = new DatagramPacket(respuesta, respuesta.length, recibo.getAddress(), recibo.getPort());
		socket.send(paqueteRespuesta);
		socket.close(); // cierro el socket
	}
}