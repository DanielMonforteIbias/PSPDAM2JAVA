package clases;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.*;

public class ClienteFTP1 {
	public static void main(String[] args) throws SocketException, IOException {
		FTPClient cliente=new FTPClient();
		String servFTP="ftp.rediris.es";
		System.out.println("Nos conectamos a "+servFTP);
		cliente.connect(servFTP);
		System.out.println(cliente.getReplyString());
		int respuesta=cliente.getReplyCode();
		if(!FTPReply.isPositiveCompletion(respuesta)) {
			cliente.disconnect();
			System.out.println("Conexion rechazada: "+respuesta);
			System.exit(0);
		}
		cliente.disconnect();
		System.out.println("Conexion finalizada");
	}
}
