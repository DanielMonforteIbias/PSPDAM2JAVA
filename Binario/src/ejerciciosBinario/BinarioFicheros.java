package ejerciciosBinario;

import java.io.File;
import java.io.IOException;

public class BinarioFicheros {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","ejerciciosBinario.Binario");
		pb.directory(directorio);
		Process p=pb.start();
		
		File fEntrada=new File("numero.txt");
		File fSalida=new File("salida.txt");
		File fError=new File("error.txt");
		pb.redirectInput(fEntrada);
		pb.redirectOutput(fSalida);
		pb.redirectError(fError);
		pb.start();
	}
}
