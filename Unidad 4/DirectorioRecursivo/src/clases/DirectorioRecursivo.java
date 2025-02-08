package clases;

import java.io.IOException;

import org.apache.commons.net.ftp.*;

public class DirectorioRecursivo {
	private static final String SERVFTP="localhost";
    private static final String USUARIO = "usuario1";
    private static final String CLAVE = "usu1";

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
            System.out.println("Lista de archivos y directorios de "+USUARIO+":");
            listarArchivos(cliente, "", 0); //Empezamos a listar en el directorio raiz, por eso las comillas vacias en el directorio; y en el nivel 0
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