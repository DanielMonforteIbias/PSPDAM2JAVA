package ejercicio3;

import java.io.BufferedReader;
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

public class Ejercicio3 {
	private static ArrayList<String> correos;

	public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException,
			KeyStoreException, InvalidKeyException, InvalidKeySpecException {
//Hereda de la clase SMTP permite que el usuario se autentifique
		AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

		String server = "smtp.gmail.com";
		String username = "daniel.monforteibias@gmail.com"; // correo que envía
		String password = "zudd ljtv ohfm yhit"; // contraseña de aplicación
//El servidor SMTP.gmail.com  que usa el puerto 587 soporta autenticación y permite
// STARTTLS es un protocolo-->Permite cifrar las comunicaciones entre el cliente y el servidor tal que
//todos los mensajes estarán cifrados
		int puerto = 587;

		correos=leerCorreos();
		try {
			int respuesta;
			// Creación de la clave para establecer un canal seguro
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(null, null);
			KeyManager km = kmf.getKeyManagers()[0];

			// nos conectamos al servidor SMTP
			client.connect(server, puerto);
			System.out.println("1 - " + client.getReplyString());
			// se establece la clave
			client.setKeyManager(km);

			respuesta = client.getReplyCode();
			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				client.disconnect();
				System.err.println("SMTP server refused connection.");
				System.exit(1);
			}

			client.ehlo(server);// necesario
			System.out.println("2 - " + client.getReplyString());

			// Ejecuta la orden STARTTLS para indicar iniciar sesión con TLS (protocolo de
			// seguridad)
			// de la capa de transporte e intercambiar correos similar a un túnel seguro
			if (client.execTLS()) { //
				System.out.println("3 - " + client.getReplyString());
				if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
					System.out.println("4 - " + client.getReplyString());
					String asunto = "Prueba de SMTPClient";
					String mensaje = "Hola. \nEnviando saludos.\nUsando GMAIL.\nBuenas tardes.";
					for(String destino:correos) {
						client.setSender(username);
						System.out.println(destino);
						// se crea la cabecera
						SimpleSMTPHeader cabecera = new SimpleSMTPHeader(username, destino, asunto);
						client.addRecipient(destino);
						System.out.println("5 - " + client.getReplyString());

						// se envia DATA
						Writer writer = client.sendMessageData();

						if (writer == null) { // fallo
							System.out.println("FALLO AL ENVIAR DATA.");
							System.exit(1);
						}
						writer.write(cabecera.toString()); // primero escribo
															// cabecera
						writer.write(mensaje);// luego mensaje
						writer.close();
						System.out.println("6 - " + client.getReplyString());

						boolean exito = client.completePendingCommand();

						System.out.println("7 - " + client.getReplyString());
						if (!exito) { // fallo
							System.out.println("FALLO AL FINALIZAR LA TRANSACCIÓN.");
							System.exit(1);
						}
					}	
				} else
					System.out.println("USUARIO NO AUTENTICADO.");

			} else
				System.out.print("FALLO AL EJECUTAR  STARTTLS.");

		} catch (IOException e) {
			System.err.println("Could not connect to server.");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			client.disconnect();
		} catch (IOException f) {
			f.printStackTrace();
		}

		System.out.println("Fin de envío.");
		System.exit(0);
	}// main

	private static ArrayList leerCorreos() {
		ArrayList<String> correos = new ArrayList<>();
		String archivo = "correos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                correos.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return correos;
	}
}
