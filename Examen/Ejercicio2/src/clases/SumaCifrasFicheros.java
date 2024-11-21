package clases;

import java.io.File;
import java.io.IOException;

public class SumaCifrasFicheros {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","clases.SumaCifras");
		pb.directory(directorio);
		
		File fEntrada=new File("numero.txt");
		File fSalida=new File("salida.txt");
		File fError=new File("error.txt");
		pb.redirectInput(fEntrada);
		pb.redirectOutput(fSalida);
		pb.redirectError(fError);
		pb.start();
		System.out.println("Compruebe los ficheros para ver el resultado");
	}
}
