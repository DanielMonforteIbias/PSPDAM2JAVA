package clases;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMulticast {
	public static void main(String[] args) throws IOException {
		int port=12345;
		MulticastSocket socket=new MulticastSocket(port);
		InetAddress grupo=InetAddress.getByName("225.0.0.1");
		socket.joinGroup(grupo);
		String mensaje="";
		while(!mensaje.trim().equals("*")) {
			byte buffer[]=new byte[1000];
			DatagramPacket paquete=new DatagramPacket(buffer, buffer.length);
			socket.receive(paquete);
			mensaje=new String(paquete.getData());
			System.out.println("Recibo: "+mensaje);
		}
		socket.leaveGroup(grupo);
		socket.close();
		System.out.println("Socket cerrado");
	}
}
