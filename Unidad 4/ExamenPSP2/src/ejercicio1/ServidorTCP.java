package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
	public static void main(String[] args) {
		int puerto=6000;
		ServerSocket servidor=null;
		try {
			System.out.println("Esperando cliente...");
			servidor=new ServerSocket(puerto);
			Socket cliente=servidor.accept();
			System.out.println("Cliente "+cliente.getInetAddress().getLocalHost()+" conectado");
			OutputStream salida=cliente.getOutputStream();
			DataOutputStream flujoSalida=new DataOutputStream(salida);
			InputStream entrada=null;
			DataInputStream flujoEntrada;
			String resultado="",correo="";
			do {
				entrada=cliente.getInputStream();
				flujoEntrada=new DataInputStream(entrada);
				correo=flujoEntrada.readUTF();
				System.out.println("Correo recibido: "+correo);
				if(correoValido(correo)) {
					guardarCorreo(correo);
					resultado="El correo se ha almacenado";
				}
				else {
					resultado="El correo no es valido, no se almacena";
				}
				System.out.println("Resultado: "+resultado);
				flujoSalida.writeUTF(resultado);
			}while(!correo.trim().equalsIgnoreCase("FIN"));
			System.out.println("Desconectando servidor...");
			entrada.close();
			salida.close();
			cliente.close();
			flujoEntrada.close();
			flujoSalida.close();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	private static boolean correoValido(String correo) {
		return correo.matches("[A-Za-z0-9.]+@{1}[A-Za-z0-9]+.{1}[A-Za-z]+");
	}
	
	private static void guardarCorreo(String correo) {
		String archivo="files/correos.txt";
		String contenido=leerArchivo(archivo);
		contenido+=correo;
		try (BufferedWriter bw=new BufferedWriter(new FileWriter(archivo))){
			bw.write(contenido);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String leerArchivo(String archivo) {
		String contenido="";
		try (BufferedReader br=new BufferedReader(new FileReader(archivo))){
			String linea="";
			while((linea=br.readLine())!=null) {
				contenido+=linea+"\n";
			}
		} catch (IOException e) {
			System.out.println("Creado el fichero files/correos.txt");
		}
		return contenido;
	}
}