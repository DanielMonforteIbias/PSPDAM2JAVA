package classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private Tablero tablero;
	public static void main(String[] args) {
		int numeroPuerto=6000;
		ServerSocket servidor=null;
		Servidor s=new Servidor();
		s.tablero=new Tablero();
		int intentos=20;
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
				String posicion=flujoEntrada.readUTF();
				System.out.println("Posicion recibida del cliente: "+posicion);
				resultado=s.comprobarPosicion(posicion);
				System.out.println("Resultado de "+posicion+" en el tablero: "+resultado);
				flujoSalida.writeUTF(resultado);
				intentos--;
			}while(s.tablero.getNumBarcos()>0 && intentos>0);
			if(s.tablero.getNumBarcos()==0)System.out.println("El cliente ha encontrado todos los barcos. Desconectando servidor");
			else if(intentos==0)System.out.println("El cliente se ha quedado sin intentos. Desconectando servidor");
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
	public String comprobarPosicion(String posicion) {
		String resultado="";
		try {
			int posicionNumero=Integer.parseInt(posicion);
			int i=posicionNumero/10;
			int j=posicionNumero%10;
			if(tablero.getTablero()[i][j]=='B') {
				resultado="Hundido";
				tablero.getTablero()[i][j]='A';
				tablero.setNumBarcos(tablero.getNumBarcos()-1);
			}
			else resultado="Agua";
		}catch(Exception e) {
			resultado="Posicion no valida"; 
		}
		return resultado;
	}
}
