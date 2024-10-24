package ejerciciosBinario;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LlamaBinarioArgs {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","ejerciciosBinario.BinarioArgs","5");
		pb.directory(directorio);
		Process p=pb.start();
		
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
				System.out.println(liner);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
