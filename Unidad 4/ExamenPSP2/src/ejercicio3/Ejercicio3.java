package ejercicio3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class Ejercicio3 {
	private final static String SERVFTP="localhost";
	private final static String USUARIO="usuario1";
	private final static String CLAVE="usu1";
	private final static String DIRECTORIOFTP="examenDanielMonforteIbias";
	private final static String DIRECTORIOLOCAL="files";
	
	public static void main(String[] args) {
		FTPClient cliente=new FTPClient();
		try {
			cliente.connect(SERVFTP);
			boolean login=cliente.login(USUARIO,CLAVE);
			if(login) System.out.println("Inicio de sesion exitoso");
			else {
				System.out.println("Inicio de sesion incorrecto");
				cliente.disconnect();
				System.exit(1);
			}
			if(!cliente.changeWorkingDirectory(DIRECTORIOFTP)) {
				if(cliente.makeDirectory(DIRECTORIOFTP))System.out.println("Directorio "+DIRECTORIOFTP+" creado");
				else {
					System.out.println("No se pudo crear el directorio "+DIRECTORIOFTP);
					return;
				}
			}
			File carpetaLocal=new File(DIRECTORIOLOCAL);
			if(carpetaLocal.exists() && carpetaLocal.isDirectory()) {
				File[] archivos=carpetaLocal.listFiles();
				if(archivos!=null) {
					cliente.changeWorkingDirectory(DIRECTORIOFTP);
					for(File archivo:archivos) {
						if(archivo.isFile() && archivo.getName().equals("correos.txt")) {
							FileInputStream fis=new FileInputStream(archivo);
							boolean subido=cliente.storeFile(archivo.getName(), fis);
							if(subido) System.out.println("Se ha subido el archivo "+archivo.getName());
							else System.out.println("Error subiendo el archivo");
						}
					}
				}
			}
			else System.out.println("No existe o no es un directorio: "+DIRECTORIOLOCAL);
			
			boolean logout=cliente.logout();
			if(logout) System.out.println("Logout exitoso");
			else System.out.println("Error en el logout");
			cliente.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
