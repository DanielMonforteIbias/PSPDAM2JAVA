package ejercicio1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
	public static void main(String[] args) {
		int numeroPuerto=6000;
		ServerSocket servidor=null;
		try {
			servidor=new ServerSocket(numeroPuerto);
			Socket clienteConectado=null;
			System.out.println("Esperando al cliente");
			clienteConectado=servidor.accept();
			System.out.println("Cliente "+clienteConectado.getInetAddress().getHostName()+" aceptado");
			OutputStream salida=null;
			salida=clienteConectado.getOutputStream();
			DataOutputStream flujoSalida=new DataOutputStream(salida);
			InputStream entrada=null;
			DataInputStream flujoEntrada;
			String resultado="", fichero="";
			do {
				entrada=clienteConectado.getInputStream();
				flujoEntrada=new DataInputStream(entrada);
				fichero=flujoEntrada.readUTF();
				System.out.println("Fichero recibido del cliente: "+fichero);
				resultado=leerFichero(fichero);
				flujoSalida.writeUTF(resultado);
			}while(!fichero.equalsIgnoreCase("EXIT"));
			System.out.println("EXIT detectado, terminando servidor");
			entrada.close();
			flujoEntrada.close();
			salida.close();
			flujoSalida.close();
			clienteConectado.close();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String leerFichero(String nombre) {
		String contenido="";
		File file=new File("files/"+nombre+".csv");
		if (!file.exists())contenido="(No existe el archivo)";
		else {
			String linea;
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        	br.readLine();
	            while ((linea = br.readLine()) != null) {
	                String[] datos = linea.split(";");
	                for (String dato : datos) {
	                    contenido+=(dato + " | ");
	                }
	                contenido+="\n";
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		return contenido;
	}
}