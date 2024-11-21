package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class LlamaSumaCifras {
	public static void main(String[] args) throws IOException {
		File directorio=new File("./bin");
		ProcessBuilder pb=new ProcessBuilder("java","clases.SumaCifras");
		pb.directory(directorio); //Nos ubicamos en el directorio que hemos definido arriba
		Process p=pb.start();
		
		Scanner s=new Scanner(System.in);
		String numeroString="";
		do {
			System.out.println("Introduce un numero: ");
			numeroString=s.next();
			if(!isNumber(numeroString))System.out.println("El valor introducido no es un numero");
		}while(!isNumber(numeroString));
		int numero=Integer.parseInt(numeroString);
		
		OutputStream os=p.getOutputStream();
		os.write((numero+"\n").getBytes()); //Ponemos el numero que hemos introducido por teclado
		os.flush();
		//Leemos el resultado del otro programa
		InputStream in=p.getInputStream();
		BufferedReader brer=new BufferedReader(new InputStreamReader(in));
		String liner="";
		while((liner=brer.readLine())!=null) {
			System.out.println(liner);
		}
	}
	public static boolean isNumber(String numero) {
		return numero.matches("-?[0-9]+"); //Devuelve true si la cadena es un numero (negativo o no, por eso el -?), y false si no
	}
}
