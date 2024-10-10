package pspunit1;

import java.io.IOException;

public class EjemploNotepad {
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb=new ProcessBuilder("NOTEPAD");
		pb.start();
		/**
		 * También se puede hacer en 1 línea de la siguiente forma:
		 * ProcessBuilder pb=new ProcessBuilder("NOTEPAD").start();
		 */
	}
}
