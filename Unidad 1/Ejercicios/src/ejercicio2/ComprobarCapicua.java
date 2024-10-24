package ejercicio2;

import java.util.Scanner;

public class ComprobarCapicua {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero por teclado: ");
		String numero=s.next();
		if(esNumero(numero)) {
			String numeroReves="";
			for(int i=numero.length()-1;i>=0;i--) {
				numeroReves+=numero.charAt(i);
			}
			if(numero.equals(numeroReves)) System.out.println(numero+" es capicua");
			else System.out.println(numero+" no es capicua");
		}
		else System.out.println("Lo introducido no es un numero");
	}
	public static boolean esNumero(String numero) {
		return numero.matches("[0-9]+");
	}
}
