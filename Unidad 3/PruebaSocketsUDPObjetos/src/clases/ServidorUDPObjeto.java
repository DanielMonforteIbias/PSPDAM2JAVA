package clases;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDPObjeto {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DatagramSocket serverSocket=new DatagramSocket(9876);
		System.out.println("Esperando datagrama...");
		byte recibidos[]=new byte[1024];
		DatagramPacket recibo=new DatagramPacket(recibidos,recibidos.length);
		serverSocket.receive(recibo);
		
		//Convertir bytes a objeto
		ByteArrayInputStream is=new ByteArrayInputStream(recibidos);
		ObjectInputStream ois=new ObjectInputStream(is);
		Persona persona=(Persona)ois.readObject();
		is.close();
		
		//Direccion origen
		InetAddress ipOrigen=recibo.getAddress();
		int puertoOrigen=recibo.getPort();
		System.out.println("Procedente de: "+ipOrigen+": "+puertoOrigen);
		System.out.println("Datos: "+persona.getNombre()+", "+persona.getEdad());
		
		//Cambiar datos
		persona.setNombre("Nombre nuevo");
		persona.setEdad(20);
		
		//De objeto a bytes
		ByteArrayOutputStream bs=new ByteArrayOutputStream();
		ObjectOutputStream os=new ObjectOutputStream(bs);
		os.writeObject(persona);
		os.close();
		byte bytes[]=bs.toByteArray();
		
		//Enviar al cliente
		System.out.println("Enviando "+bytes.length+" bytes al servidor");
		DatagramPacket envio=new DatagramPacket(bytes,bytes.length,ipOrigen,puertoOrigen);
		serverSocket.send(envio);
		
		serverSocket.close();
		System.out.println("Socket cerrado");
	}
}
