package pspunit1;

import java.io.File;
import java.io.IOException;

public class Ejemplo7 {
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb=new ProcessBuilder("CMD","/C","DIR");
		File fOut=new File("Files/salida.txt");
		File fErr=new File("Files/error.txt");
		pb.redirectOutput(fOut);
		pb.redirectError(fErr);
		pb.start();
	}
}
