package ejercicio1;

public class BucleSaludo {
	public static void main(String[] args) {
		if(args.length==1) {
			for(int i=0;i<5;i++) {
				System.out.println(args[0]);
			}
		}
		else {
			System.out.println("Numero de parametros incorreto");
			System.exit(1);
		}
	}
}
