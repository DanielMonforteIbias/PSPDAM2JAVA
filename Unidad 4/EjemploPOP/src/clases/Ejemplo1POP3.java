package clases;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Ejemplo1POP3 {
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
				else System.out.println("Numero de mensajes: "+men.length);
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
}