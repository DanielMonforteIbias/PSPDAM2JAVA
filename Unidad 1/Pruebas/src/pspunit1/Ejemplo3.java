package pspunit1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejemplo3 {
	public static void main(String[] args) throws IOException {
		//Obtener el directorio donde nos encontramos
		String w=System.getProperty("user.dir");
		System.out.println("Mi ruta es: "+w);
		
		File directorio=new File(".\\bin");
		
		ProcessBuilder pb=new ProcessBuilder("java","pspunit1.Ejemplo2");
		pb.directory(directorio);
		System.out.println("El directorio de trabajo es: "+pb.directory());
		Process p=pb.start();
		
		InputStream is=p.getInputStream();
		int c;
		while((c=is.read())!=-1) {
			System.out.print((char)c);
		}
		is.close();
	}
}
