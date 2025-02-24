package ejercicio2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

public class Ejercicio2 {
	private static ArrayList<String>correos=new ArrayList<String>();
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,UnrecoverableKeyException, KeyStoreException {
		AuthenticatingSMTPClient client=new AuthenticatingSMTPClient();
		String server="smtp.gmail.com";
		String username="daniel.monforteibias@gmail.com";
		String password="zudd ljtv ohfm yhit";
		int puerto=587;
		correos=leerCorreos();
		try {
			KeyManagerFactory kmf=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(null,null);
			KeyManager km=kmf.getKeyManagers()[0];
			client.connect(server,puerto);
			System.out.println("1 - "+client.getReplyString());
			int respuesta=client.getReplyCode();
			if(!SMTPReply.isPositiveCompletion(respuesta)) {
				System.out.println("Error en la respuesta");
				client.disconnect();
				System.exit(1);
			}
			client.ehlo(server);
			System.out.println("2 - "+client.getReplyString());
			if(client.execTLS()) {
				System.out.println("3 - "+client.getReplyString());
				if(client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
					System.out.println("4 - "+client.getReplyString());
					String asunto="Examen PSP 24 de febrero";
					String cuerpo="Este es mi ultimo examen de PSP. Un saludo";
					if(correos.size()>0) {
						for(String destino:correos) {
							client.setSender(username);
							SimpleSMTPHeader cabecera=new SimpleSMTPHeader(username,destino,asunto);
							client.addRecipient(destino);
							System.out.println("5 - "+client.getReplyString());
							Writer writer=client.sendMessageData();
							if(writer==null) {
								System.out.println("Error en el Writer");
								System.exit(1);
							}
							writer.write(cabecera.toString());
							writer.write(cuerpo);
							writer.close();
							System.out.println("6 - "+client.getReplyString());
							boolean exito=client.completePendingCommand();
							System.out.println("7 - "+client.getReplyString());
							if(!exito) {
								System.out.println("Error al enviar mensaje");
								System.exit(1);
							}
						}	
					}
					else System.out.println("No hay destinos");
				}
			}
			
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
			System.exit(1);
		}
		try {
			client.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FIN");
		System.exit(0);
		
	}
	
	private static ArrayList<String>leerCorreos(){
		ArrayList<String> correos=new ArrayList<String>();
		String archivo="files/correos.txt";
		try(BufferedReader br=new BufferedReader(new FileReader(archivo))){
			String linea;
			while((linea=br.readLine())!=null) {
				correos.add(linea);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return correos;
	}
}
