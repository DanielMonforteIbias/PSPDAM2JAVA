package pspunit1;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ejemplo6 {
	public static void main(String[] args) {
		ProcessBuilder test=new ProcessBuilder();
		Map entorno=test.environment();
		System.out.println("Variables del entorno: "+entorno);
		test=new ProcessBuilder("java","Hola","Maria");
		//Command devuelve los argumentos de test
		List l=test.command();
		Iterator iter=l.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		//Command con parámetros define un nuevo proceso y sus argumentos
		test.command("CMD","/C","DIR");
		try {
			Process p=test.start();
			InputStream is=p.getInputStream();
			int c;
			while((c=is.read())!=-1) {
				System.out.print((char)c);
			}
			is.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
