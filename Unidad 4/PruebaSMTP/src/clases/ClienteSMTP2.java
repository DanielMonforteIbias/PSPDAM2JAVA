package clases;

import java.io.IOException;
import java.net.SocketException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;

public class ClienteSMTP2 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		AuthenticatingSMTPClient client=new AuthenticatingSMTPClient();
		String server="smtp.gmail.com";
		String username="";
		String password="";
		int puerto=587;
		
		try {
			KeyManagerFactory key=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			key.init(null,null);
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			client.connect(server,puerto);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("a");
		
	}
}
