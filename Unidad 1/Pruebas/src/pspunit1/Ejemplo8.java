package pspunit1;

import java.io.File;
import java.io.IOException;

public class Ejemplo8 {
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb=new ProcessBuilder("CMD");
		File fBat=new File("Files/fichero.bat");
		File fOut=new File("Files/salida.txt");
		File fErr=new File("Files/error.txt");
		pb.redirectInput(fBat);
		pb.redirectError(fOut);
		pb.redirectError(fErr);
		pb.start();
	}
}
