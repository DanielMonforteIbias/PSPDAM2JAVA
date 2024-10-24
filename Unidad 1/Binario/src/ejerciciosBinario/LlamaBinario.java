package ejerciciosBinario;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class LlamaBinario {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","ejerciciosBinario.Binario");
		pb.directory(directorio);
		Process p=pb.start();

		OutputStream os=p.getOutputStream();
		os.write("12\n".getBytes());
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
