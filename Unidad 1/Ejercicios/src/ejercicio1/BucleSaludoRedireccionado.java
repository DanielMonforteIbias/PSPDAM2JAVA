package ejercicio1;

import java.io.File;
import java.io.IOException;

public class BucleSaludoRedireccionado {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","ejercicio1.BucleSaludo","Saludoooo");
		pb.directory(directorio);
		Process p=null;
		
		File salida=new File("FicheroSaludo.txt");
		
		try {
			pb.redirectOutput(salida);
			p=pb.start();
		}
		catch(IOException ioe) {
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
