package pspunit1;

import java.io.IOException;

public class Ejemplo9 {
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb=new ProcessBuilder("CMD","/C","DIR");
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		Process p=pb.start();
	}
}
