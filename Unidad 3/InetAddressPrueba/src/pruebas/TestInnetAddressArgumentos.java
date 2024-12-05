package pruebas;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInnetAddressArgumentos {
	public static void main(String[] args) {
		if(args.length==1) {
			InetAddress dir=null;
			System.out.println("====================================");
			try {
				String url=args[0];
				dir=InetAddress.getByName(url);
				System.out.println("SALIDA PARA "+dir);
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
		else System.out.println("Numero de parametros incorrecto");
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