package ejerciciosBinario;


public class BinarioArgs {
	public static void main(String[] args) {
		if(args.length==1) {
			try {
				int numero=Integer.parseInt(args[0]);
				int num=numero; //Guardamos el valor en otra variable para conservar el valor inicial, pues lo vamos a modificar
				if (numero>=0) {
					String binario=""; //El binario sera un String para ir concatenando los restos
					//Para pasar un numero a binario, lo dividiremos entre 2 hasta que no podamos más y cogeremos los restos y el ultimo cociente
					while(num>1) { //Mientras sea mayor que 1
						binario=(num%2)+binario; //Añadimos el resto a la cadena del binario
						num=num/2; //Lo dividimos entre 2
					}
					binario=num+binario; //Añadimos el ultimo valor al salir del bucle
					System.out.println(numero+" en binario es: "+binario);
				}
				else System.out.println("El numero no puede ser negativo");
			}
			catch(Exception e) {
				System.out.println("Error leyendo el parametro");
			}
		}
		else System.out.println("Numero de parametros incorrecto");
	}
}
