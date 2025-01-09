package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) throws IOException {
		String host="localhost";
		int puerto=6000;
		Socket cliente=new Socket(host,puerto);
		PrintWriter fSalida=new PrintWriter(cliente.getOutputStream(),true);
		BufferedReader fEntrada=new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String cadena="", eco="";
		
		System.out.println("INTRODUCE CADENA: ");
		cadena=in.readLine();
		while(cadena!=null) {
			fSalida.println(cadena);
			eco=fEntrada.readLine();
			System.out.println("=>ECO: "+eco);
			System.out.println("Introduce cadena: ");
			cadena=in.readLine();
		}
		fSalida.close();
		fEntrada.close();
		System.out.println("Fin del envio...");
		in.close();
		cliente.close();
	}
}
