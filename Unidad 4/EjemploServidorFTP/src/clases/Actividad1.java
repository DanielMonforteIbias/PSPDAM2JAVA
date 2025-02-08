package clases;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Actividad1 {
	public static void main(String[] args) {
		FTPClient cliente=new FTPClient();
		String servFTP="localhost";
		System.out.println("Nos conectamos a "+servFTP);
		String usuario="usuario1";
		String clave="usu1";
		try {
			cliente.connect(servFTP);
			boolean login=cliente.login(usuario, clave);
			if(login) System.out.println("Login correcto...");
			else {
				System.out.println("Login incorrecto");
				cliente.disconnect();
				System.exit(1);
			}
			System.out.println("Directorio actual: "+cliente.printWorkingDirectory());
			//listDirectories() //Obtiene una lista de los directorios contenidos en el directorio de trabajo actual
			FTPFile[] files=cliente.listDirectories();
			System.out.println("Directorios en el directorio actual: "+files.length);
			System.out.println("===========================");
			for(int i=0;i<files.length;i++) {
				System.out.println("Directorio: "+files[i].getName());
				FTPFile[]files2=cliente.listFiles(files[i].getName());
				String[] tipos= {"Fichero","Directorio","Enlace simb."};
				for(int j=0;j<files2.length;j++) {
					System.out.println("\t"+files2[j].getName()+" => "+tipos[files2[j].getType()]);
				}
			}
			boolean logout=cliente.logout();
			if(logout) System.out.println("Logout del servidor FTP");
			else System.out.println("Error al hacer logout");
			
			cliente.disconnect();
			System.out.println("Desconectado...");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
