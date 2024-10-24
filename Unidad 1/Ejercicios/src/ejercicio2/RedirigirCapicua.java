package ejercicio2;

import java.io.File;
import java.io.IOException;

public class RedirigirCapicua {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","ejercicio2.ComprobarCapicua");
		pb.directory(directorio);
		Process p=pb.start();
		
		File fEntrada=new File("capicua.txt");
		File fSalida=new File("salidaCapicua.txt");
		File fError=new File("errorCapicua.txt");
		pb.redirectInput(fEntrada);
		pb.redirectOutput(fSalida);
		pb.redirectError(fError);
		pb.start();
	}
}
