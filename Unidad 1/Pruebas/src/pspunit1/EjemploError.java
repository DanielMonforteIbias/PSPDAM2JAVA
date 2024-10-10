package pspunit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjemploError {
	public static void main(String[] args) throws IOException {
		Process p=new ProcessBuilder("CMD","/C","DIRE").start(); //DIR está escrito mal para visualizar un error
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
		
		InputStream er=p.getErrorStream();
		BufferedReader brer=new BufferedReader(new InputStreamReader(er));
		String liner="";
		while((liner=brer.readLine())!=null) {
			System.out.println("ERROR> "+liner);
		}
	}
}
