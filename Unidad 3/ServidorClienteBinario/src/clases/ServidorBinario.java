package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBinario {
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
			String resultado="";
			do {
				entrada=clienteConectado.getInputStream();
				flujoEntrada=new DataInputStream(entrada);
				String decimal=flujoEntrada.readUTF();
				System.out.println("Numero recibido del cliente: "+decimal);
				resultado=convertirABinario(decimal);
				System.out.println("Resultado de "+decimal+" en binario: "+resultado);
				flujoSalida.writeUTF(resultado);
			}while(!resultado.equals("0"));
			System.out.println("0 detectado, terminando servidor");
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
	private static String convertirABinario(String numeroDecimal) {
		String resultado="";
		try {
			int decimal=Integer.parseInt(numeroDecimal);
			if(decimal<0) return resultado="El numero debe ser positivo"; //Solo funciona para numeros positivos
			else while(decimal>=2) {
				resultado=(decimal%2)+resultado;
				decimal=decimal/2;
			}
			resultado=decimal+resultado;
		}catch(NumberFormatException e) {
			resultado="Numero no valido";
		}
		return resultado;
	}
}