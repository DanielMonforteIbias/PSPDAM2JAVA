package ejercicio2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class LlamaPrimo {
	public static void main(String[] args) throws IOException {
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un número: ");
		int numero=s.nextInt();
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","ejercicio2.Primo");
		pb.directory(directorio);
		Process p=pb.start();

		OutputStream os=p.getOutputStream();
		os.write((numero+"\n").getBytes()); //\n para que tome el argumento y siga
		os.flush();
		
		try {
			InputStream is=p.getInputStream();
			BufferedReader brer=new BufferedReader(new InputStreamReader(is));
			String liner="";
			while((liner=brer.readLine())!=null) {
				System.out.println(liner);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
