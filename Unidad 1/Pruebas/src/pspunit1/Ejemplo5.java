package pspunit1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Ejemplo5 {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","pspunit1.EjemploLectura");
		pb.directory(directorio);
		String w=System.getProperty("user.dir");
		System.out.println("Mi ruta es: "+w);
		Process p=pb.start();
		//Escritura
		OutputStream os=p.getOutputStream();
		os.write("Hola Maria\n".getBytes());
		os.flush();
		
		//Leer errores
		try {
			InputStream er=p.getErrorStream();
			BufferedReader brer=new BufferedReader(new InputStreamReader(er));
			String liner="";
			while((liner=brer.readLine())!=null) {
				System.out.println("ERROR> "+liner);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		//Leer stream de entrada
		try {
			InputStream is=p.getInputStream();
			BufferedReader brer=new BufferedReader(new InputStreamReader(is));
			String liner="";
			while((liner=brer.readLine())!=null) {
				System.out.println("BIEN>"+liner);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
