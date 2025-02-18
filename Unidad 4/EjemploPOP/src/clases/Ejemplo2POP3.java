package clases;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Ejemplo2POP3 {
	public static void main(String[] args) {
		String server="localhost", username="usu1", password="usu1";
		int puerto=110;
		POP3SClient pop3=new POP3SClient();
		try {
			pop3.connect(server,puerto);
			System.out.println("Conexion realizada al servidor POP3 "+server);
			if(!pop3.login(username, password)) System.err.println("Error al hacer login");
			else {
				POP3MessageInfo[]men=pop3.listMessages();
				if(men==null) System.out.println("Imposible recuperar mensajes");
				else {
					System.out.println("Numero de mensajes: "+men.length);
					if(men.length>0) recuperarMensajes(men,pop3);
				}
				pop3.logout();
			}
			pop3.disconnect();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}
	
	
	private static void recuperarMensajes(POP3MessageInfo[]men, POP3SClient pop3) throws IOException {
		for(int i=0;i<men.length;i++) {
			System.out.println("Mensaje "+(i+1));
			POP3MessageInfo msgInfo=men[i];
			System.out.println("Identificador: "+msgInfo.identifier+", Number: "+msgInfo.number+", Tamaño: "+msgInfo.size);
			
			System.out.println("Prueba de listUniqueIdentifier: ");
			POP3MessageInfo pmi=pop3.listUniqueIdentifier(i+1);
			System.out.println("\tIdentificador: "+pmi.identifier+", Number: "+pmi.number+", Tamaño: "+pmi.size);
			
			System.out.println("Cabecera del mensaje");
			BufferedReader reader=(BufferedReader)pop3.retrieveMessageTop(msgInfo.number,0);
			String line;
			while((line=reader.readLine())!=null) {
				System.out.println(line.toString());
			}
			reader.close();
			
			BufferedReader reader2=(BufferedReader)pop3.retrieveMessage(msgInfo.number);
			String linea;
			while((linea=reader2.readLine())!=null) {
				System.out.println(linea.toString());
			}
			reader2.close();
		}
	}
}
