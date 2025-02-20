package ejercicio2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Ejercicio2 {
	private static final String SERVFTP="localhost";
    private static final String USUARIO = "usuario1";
    private static final String CLAVE = "usu1";
    private static final String DIRECTORIOFTP="danielMonforte";
    private static final String DIRECTORIOLOCAL="filesRespuesta";
    
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        try {
            cliente.connect(SERVFTP);
            boolean login=cliente.login(USUARIO, CLAVE);
            if(login) System.out.println("Login correcto...");
			else {
				System.out.println("Login incorrecto");
				cliente.disconnect();
				System.exit(1);
			}
            if (!cliente.changeWorkingDirectory(DIRECTORIOFTP)) {
                if (cliente.makeDirectory(DIRECTORIOFTP)) {
                    System.out.println("Directorio '" + DIRECTORIOFTP + "' creado en el servidor FTP.");
                } else {
                    System.out.println("No se pudo crear el directorio en el servidor.");
                    return;
                }
            }
            File carpetaLocal = new File(DIRECTORIOLOCAL);
            if (carpetaLocal.exists() && carpetaLocal.isDirectory()) {
                File[] archivos = carpetaLocal.listFiles();
                if (archivos != null) {
                    cliente.changeWorkingDirectory(DIRECTORIOFTP);
                    for (File archivo : archivos) {
                        if (archivo.isFile()) {
                            FileInputStream fis = new FileInputStream(archivo);
                            boolean subido = cliente.storeFile(archivo.getName(), fis);
                            fis.close();
                            if (subido) {
                                System.out.println("Subido: " + archivo.getName());
                            } else {
                                System.out.println("Error al subir: " + archivo.getName());
                            }
                        }
                    }
                }
            } else {
                System.out.println("El directorio local no existe o no es válido.");
            }
            
            
			boolean logout=cliente.logout();
			if(logout) System.out.println("Logout del servidor FTP");
			else System.out.println("Error al hacer logout");
			cliente.disconnect();
			System.out.println("Desconectado...");
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    private static void listarArchivos(FTPClient cliente, String directorio, int nivel) throws IOException {
        FTPFile[] files = cliente.listFiles(directorio); //Obtenemos la lista del contenido del directorio recibido
        String[] tipos= {"Fichero","Directorio","Enlace simb."};
        for (FTPFile file : files) {
            String tabulado = "\t".repeat(nivel); //El tabulado sera mayor segun el nivel, para mostrar visualmente los archivos contenidos en directorios
            System.out.println(tabulado +"["+tipos[file.getType()] +"] => "+file.getName()); //Mostramos el tipo y nombre del archivo
            if (file.isDirectory()) listarArchivos(cliente, directorio+"/"+file.getName(), nivel+1); //Si es un directorio, llamamos al metodo de forma recursiva, sumando un nivel y siendo el directorio actual la raiz de donde listar
        }
    }
}
