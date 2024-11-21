package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LlamaSumaCifrasArg {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","clases.SumaCifrasArg","45");
		pb.directory(directorio);
		Process p=pb.start();
		//Imprimimos el stream de entrada, el resultado del otro programa
		InputStream in=p.getInputStream();
		BufferedReader brer=new BufferedReader(new InputStreamReader(in));
		String liner="";
		while((liner=brer.readLine())!=null) {
			System.out.println(liner);
		}
		//Imprimimos el stream de errores, por si ocurren casos como un parametro que no es un numero
		InputStream err=p.getErrorStream();
		BufferedReader brerErr=new BufferedReader(new InputStreamReader(err));
		String linerErr="";
		while((linerErr=brerErr.readLine())!=null) {
			System.out.println("ERROR>: "+linerErr);
		}
	}
}
