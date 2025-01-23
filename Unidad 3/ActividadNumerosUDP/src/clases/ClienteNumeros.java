package clases;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteNumeros {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner s=new Scanner(System.in);
		DatagramSocket clientSocket=new DatagramSocket();
		byte recibidos[]=new byte[1024];
		InetAddress ipServidor=InetAddress.getLocalHost();
		int puerto=9876;
		int numero=0;
		do {
			System.out.println("Introduce un numero: ");
			if(s.hasNextInt()) numero=s.nextInt();
			else {
				s.next(); //Evitamos bucle infinito
				numero=0; //Hacemos que numero sea 0 para terminar la ejecucion
			}
			Numeros nObjeto=new Numeros();
			nObjeto.setNumero(numero);
			//De objeto a bytes
			ByteArrayOutputStream bs=new ByteArrayOutputStream();
			ObjectOutputStream os=new ObjectOutputStream(bs);
			os.writeObject(nObjeto);
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
			Numeros numeroRespuesta=(Numeros)ois.readObject();
			is.close();
			
			//Visualizar datos
			InetAddress ipOrigen=recibo.getAddress();
			int puertoOrigen=recibo.getPort();
			System.out.println("Procedente de: "+ipOrigen+": "+puertoOrigen);
			System.out.println("Cuadrado de "+numeroRespuesta.getNumero()+": "+numeroRespuesta.getCuadrado()+", Cubo de "+numeroRespuesta.getNumero()+": "+numeroRespuesta.getCubo());
		}while(numero>0);
		
		clientSocket.close();
		s.close();
		System.out.println("Socket cliente cerrado");
	}
}
