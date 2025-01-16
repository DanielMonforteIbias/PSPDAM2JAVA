package clases;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorNumeros {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DatagramSocket serverSocket=new DatagramSocket(9876);
		byte recibidos[]=new byte[1024];
		DatagramPacket recibo=new DatagramPacket(recibidos,recibidos.length);
		Numeros numero=new Numeros();
		do {
			System.out.println("Esperando datagrama...");
			serverSocket.receive(recibo);
			
			//Convertir bytes a objeto
			ByteArrayInputStream is=new ByteArrayInputStream(recibidos);
			ObjectInputStream ois=new ObjectInputStream(is);
			numero=(Numeros)ois.readObject();
			is.close();
			
			//Direccion origen
			InetAddress ipOrigen=recibo.getAddress();
			int puertoOrigen=recibo.getPort();
			System.out.println("Procedente de: "+ipOrigen+": "+puertoOrigen);
			System.out.println("Numero recibido: "+numero.getNumero());
			
			//Cambiar datos
			numero.setCuadrado((long)(Math.pow(numero.getNumero(), 2)));
			numero.setCubo((long)(Math.pow(numero.getNumero(), 3)));
			
			//De objeto a bytes
			ByteArrayOutputStream bs=new ByteArrayOutputStream();
			ObjectOutputStream os=new ObjectOutputStream(bs);
			os.writeObject(numero);
			os.close();
			byte bytes[]=bs.toByteArray();
			
			//Enviar al cliente
			System.out.println("Enviando "+bytes.length+" bytes al servidor");
			DatagramPacket envio=new DatagramPacket(bytes,bytes.length,ipOrigen,puertoOrigen);
			serverSocket.send(envio);
		}while(numero.getNumero()>0);
		serverSocket.close();
		System.out.println("Socket servidor cerrado");
	}
}
