package clases;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

public class Actividad1SMTP {
	public static void main(String[] args) throws NoSuchAlgorithmException,UnrecoverableKeyException,KeyStoreException, NoSuchAlgorithmException,KeyManagementException,IOException {
		System.out.println(args.length);
		if (args.length == 4) {
			String server = args[0]; //smtp.gmail.com
			String username = args[1]; //daniel.monforteibias@gmail.com
			String password = args[2]; //zudd ljtv ohfm yhit
			int puerto = 0; //587
			try {
				puerto = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				System.out.println("El puerto no es valido");
				System.exit(1);
			}

			AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();
			try {
				KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				keyManagerFactory.init(null, null);

				// Conectar al servidor SMTP
				client.connect(server, puerto);
				if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
					System.out.println("Error: No se pudo conectar al servidor.");
					client.disconnect();
					System.exit(1);
				}
				System.out.println("Conectado a " + server);

				client.ehlo(server);
				if (client.execTLS()) {
					System.out.println("TLS habilitado.");
					if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
						System.out.println("Autenticación exitosa.");

						String destinatario = "daniel.monforteibias@riberadeltajo.es";
						client.setSender(username);
						client.addRecipient(destinatario);
						String subject="Hola";
						String mensaje="Mensaje";
						client.sendSimpleMessage(username, destinatario, "Subject: " + subject + "\n\n" + mensaje);

						System.out.println("Correo enviado exitosamente.");
					} else {
						System.out.println("Error de autenticación.");
					}
				} else {
					System.out.println("No se pudo habilitar TLS.");
				}
				client.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else System.out.println("Numero de parametros incorrecto");
	}
}
