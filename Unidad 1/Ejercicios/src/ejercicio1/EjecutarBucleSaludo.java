package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjecutarBucleSaludo {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","ejercicio1.BucleSaludo","Saludoooo");
		pb.directory(directorio);
		Process p=pb.start();
		
		
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
		
		//COMPROBACION DE ERROR. 0 bien 1 mal
		try {
			int exitVal=p.waitFor();
			System.out.println("Valor de salida: "+exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
