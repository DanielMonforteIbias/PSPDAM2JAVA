package pspunit1;

import java.io.*;

public class Ejemplo2 {
	public static void main(String[] args) throws IOException{
		//C ejecuta el CMD y luego finaliza
		Process p=new ProcessBuilder("CMD","/C","DIR").start();
		InputStream is=p.getInputStream();
		int c;
		while((c=is.read())!=-1) {
			System.out.print((char)c);
		}
		is.close();
		try {
			int exitVal=p.waitFor();
			System.out.println("Valor de salida: "+exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
