package clases;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDPObjeto {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DatagramSocket clientSocket=new DatagramSocket();
		byte recibidos[]=new byte[1024];
		InetAddress ipServidor=InetAddress.getLocalHost();
		int puerto=9876;
		Persona p=new Persona("Maria",22);
		
		//De objeto a bytes
		ByteArrayOutputStream bs=new ByteArrayOutputStream();
		ObjectOutputStream os=new ObjectOutputStream(bs);
		os.writeObject(p);
		os.close();
		
		byte bytes[]=bs.toByteArray();
		
		//Enviando al servidor
		System.out.println("Enviando "+bytes.length+" bytes al servidor");
		DatagramPacket envio=new DatagramPacket(bytes,bytes.length,ipServidor,puerto);
		clientSocket.send(envio);
		
		//Recibiendo del servidor
		DatagramPacket recibo=new DatagramPacket(recibidos,recibidos.length);
		System.out.println("Esperando datagrama...");
		clientSocket.receive(recibo);
		
		//Convertimos bytes a objeto
		ByteArrayInputStream is=new ByteArrayInputStream(recibidos);
		ObjectInputStream ois=new ObjectInputStream(is);
		Persona persona=(Persona)ois.readObject();
		is.close();
		
		//Visualizar datos
		InetAddress ipOrigen=recibo.getAddress();
		int puertoOrigen=recibo.getPort();
		System.out.println("Procedente de: "+ipOrigen+": "+puertoOrigen);
		System.out.println("Datos: "+persona.getNombre()+", "+persona.getEdad());
		clientSocket.close();
	}
}