package pspunit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploLectura {
	public static void main(String[] args) {
		InputStreamReader in=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(in);
		try {
			System.out.println("Introduce una cadena: ");
			String texto=br.readLine();
			System.out.println("La cadena escrita es: "+texto);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
