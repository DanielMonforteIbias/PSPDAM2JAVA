package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread{
		BufferedReader fEntrada;
		PrintWriter fSalida;
		Socket socket=null;
		public HiloServidor(Socket s) {
			socket=s;
			try {
				fSalida=new PrintWriter(socket.getOutputStream(),true);
				fEntrada=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			}catch(IOException e) {
				System.out.println("ERROR DE E/S");
			}
		}
		public void run() {
			String cadena="";
			while(!cadena.trim().equals("*")){
				System.out.println("Comunico con "+socket.toString());
				try {
					cadena=fEntrada.readLine();
				}catch(IOException e) {
					e.printStackTrace();
				}
				fSalida.println(cadena.trim().toUpperCase());
			}
			System.out.println("FIN CON "+socket.toString());
			fSalida.close();
			try {
				fEntrada.close();
				socket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
}