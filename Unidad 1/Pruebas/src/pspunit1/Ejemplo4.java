package pspunit1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Ejemplo4 {
	public static void main(String[] args) throws IOException {
		Process p=new ProcessBuilder("CMD","/C","DATE").start();
		
		//Enviar la fecha a date
		OutputStream os=p.getOutputStream();
		os.write("06/05/2018".getBytes());
		os.flush();
		
		//Lectura: Obtener la salida
		InputStream is=p.getInputStream();
		int c;
		while((c=is.read())!=-1) {
			System.out.print((char)c);
		}
		try {
			int exitVal=p.waitFor();
			System.out.println("Valor de salida: "+exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
