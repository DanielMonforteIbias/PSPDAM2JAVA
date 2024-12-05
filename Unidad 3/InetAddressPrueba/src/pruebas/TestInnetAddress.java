package pruebas;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInnetAddress {
	public static void main(String[] args) {
		InetAddress dir=null;
		System.out.println("====================================");
		System.out.println("SALIDA PARA LOCALHOST");
		try {
			//dir tendra la ip del host
			dir=InetAddress.getByName("localhost");
			pruebaMetodos(dir);
			
			System.out.println("====================================");
			System.out.println("SALIDA PARA UNA URL");
			dir=InetAddress.getByName("www.google.es");
			pruebaMetodos(dir);
			System.out.println("Direcciones IP para "+dir.getHostName());
			InetAddress direcciones[]=InetAddress.getAllByName(dir.getHostName());
			for(int i=0;i<direcciones.length;i++) {
				System.out.println("\t"+direcciones[i].toString());
			}
			System.out.println("====================================");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
	}
	private static void pruebaMetodos(InetAddress dir) {
		System.out.println("Metodo getByName(): "+dir);
		try {
			InetAddress dir2=InetAddress.getLocalHost();
			System.out.println("Metodo getLocalHost(): "+dir2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("Metodo getHostName(): "+dir.getHostName());
		System.out.println("Metodo getHostAddress(): "+dir.getHostAddress());
		System.out.println("Metodo toString(): "+dir.toString());
		System.out.println("Metodo getCanonicalHostName(): "+dir.getCanonicalHostName());
	}
}