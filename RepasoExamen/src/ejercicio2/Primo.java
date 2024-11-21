package ejercicio2;

import java.util.Scanner;

public class Primo {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un número: ");
		int numero=s.nextInt();
		int divisores=0;
		for(int i=1;i<=numero;i++) {
			if(numero%i==0) divisores++;
		}
		if(divisores==2) System.out.println(numero+" es primo");
		else System.out.println(numero+" no es primo");
	}
}
