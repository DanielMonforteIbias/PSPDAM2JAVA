package clases;

import java.util.Scanner;

public class SumaCifras {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String numeroString="";
		do { //Bucle hasta que se introduzca un numero valido
			System.out.println("Introduce un numero: ");
			numeroString=s.next(); //Al principio lo guardamos como String por si no es un numero
			if(!isNumber(numeroString))System.out.println("El valor introducido no es un numero"); //Si el numero no es valido avisamos al usuario
		}while(!isNumber(numeroString));
		int numero=Integer.parseInt(numeroString);
		int numeroOriginal=numero; //Guardamos el numero original para mostrarlo mas adelante
		numero=Math.abs(numero); //Pasamos el numero a valor absoluto, por si era negativo
		int sumaCifras=0; //Variable que contendra el total de la suma de las cifras
		while(numero>=10) { //Mientras el numero tenga mas de una cifra
			sumaCifras+=numero%10; //Sumamos a la suma de las cifras el resto de dividir el numero entre 10
			numero=numero/10; //Dividimos el numero entre 10 para quitarle la ultima cifra
		}
		sumaCifras+=numero; //Al salir le sumamos la ultima cifra
		System.out.println("La suma de las cifras de "+numeroOriginal+" es "+sumaCifras);
	}
	public static boolean isNumber(String numero) {
		return numero.matches("-?[0-9]+"); //Devuelve true si la cadena es un numero (negativo o no, por eso el -?), y false si no
	}
}