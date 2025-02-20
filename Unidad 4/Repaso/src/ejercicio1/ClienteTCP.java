package ejercicio1;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String host="localhost";
		int puerto=6000;
		System.out.println("PROGRAMA CLIENTE INICIADO...");
		try {
			Socket cliente=new Socket(host,puerto);
			DataOutputStream flujoSalida=new DataOutputStream(cliente.getOutputStream());
			DataInputStream flujoEntrada=new DataInputStream(cliente.getInputStream());
			String consulta="", resultado="";
			do {
				System.out.println("Introduce una consulta: ");
				consulta=s.next();
				flujoSalida.writeUTF(consulta);
				resultado=flujoEntrada.readUTF();
				System.out.println(resultado);
				if(!resultado.equals("(No existe el archivo)")) {
					String archivo = "filesRespuesta/respuesta"+consulta+".txt";
			        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
			            bw.write(resultado);
			            System.out.println("Archivo "+archivo+"guardado correctamente.");
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				}
			}while(!consulta.equalsIgnoreCase("EXIT"));
			System.out.println("Desconectando del servidor");
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();	
		} catch (java.net.ConnectException e) {
			System.out.println("No se pudo conectar al servidor");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}