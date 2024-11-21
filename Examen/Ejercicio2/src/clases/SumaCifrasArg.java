package clases;

public class SumaCifrasArg {
	public static void main(String[] args) {
		if(args.length==1) {
			try {
				int numero=Integer.parseInt(args[0]);
				int numeroOriginal=numero;
				int sumaCifras=0;
				while(numero>=10) {
					sumaCifras+=numero%10;
					numero=numero/10;
				}
				sumaCifras+=numero;
				System.out.println("La suma de las cifras de "+numeroOriginal+" es "+sumaCifras);
			}catch(NumberFormatException e) {
				System.out.println("El parametro introducido no es un numero");
			}
		}
		else System.out.println("Numero de parametros incorrecto");
	}
}