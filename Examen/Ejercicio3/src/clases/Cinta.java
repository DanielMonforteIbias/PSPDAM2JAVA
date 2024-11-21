package clases;

public class Cinta {
	private int dimension=0; //Cantidad de piezas que puede tener la cinta
	private double[] cintatransportadora; //Array que guardara los valores de la cinta
	private int cont=0; //Contador de piezas en la cinta actualmente
	private int sacar=0; //Index del que sacar la proxima pieza
	private int echar=0; //Index en el que echar la proxima pieza
	
	public Cinta(int dimension) {
		this.dimension=dimension;
		cintatransportadora=new double[dimension]; //Le damos tamaño a la cinta
	}
	
	public synchronized void insertar(double valor) {
		 while(cont==dimension) { //Mientras el contador de piezas sea igual al tamaño de la cinta, esta estara llena
			 System.out.println("\t CINTA TRANSPORTADORA LLENA");
			 try {
				wait(); //Esperamos a que deje de estar llena
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
		//Si hay hueco en la cinta
		 //No hace falta un if porque si hemos llegado aqui sabemos que cont no es igual a dimension, es decir, hay hueco en la cinta
		System.out.println("NUEVA PIEZA EN LA CINTA "+valor);
		cintatransportadora[echar]=valor; //Echamos en la posicion correcta la nueva pieza
		echar=(echar+1)%cintatransportadora.length; //Aumentamos el indice de echar. Haciendo (echar+1)%cintatransportadora.length aseguramos que el indice esté siempre dentro del tamaño de la cinta, volviendo al 0 cuando alcanza el final
		cont++; //Aumentamos en 1 el numero de piezas en la cinta
		notifyAll(); //Notificamos para que el otro brazo sepa que se puede sacar una pieza
	}
	public synchronized double extraer() {
		double valor;
		//cintatransportadora vacia
		while(cont==0) { //Si el contador de piezas en la cinta es 0, esta vacia
			System.out.println("\t CINTA TRANSPORTADORA VACIA");
			try {
				wait(); //Esperamos a que haya piezas
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Si hay producto lo saca y lo pasa a la cadena de montaje
		//No hace falta un if porque si hemos llegado hasta aqui sabemos que cont no es 0, es decir, que hay pieza
		valor=cintatransportadora[sacar]; //Sacamos el valor que esté en la posicion sacar
		sacar=(sacar+1)%cintatransportadora.length; //Aumentamos el indice de sacar. Haciendo (sacar+1)%cintatransportadora.length aseguramos que el indice esté siempre dentro del tamaño de la cinta, volviendo al 0 cuando alcanza el final
		System.out.println("\t"+valor+" PASA A LA CADENA DE MONTAJE");
		cont--; //Restamos una pieza de la cinta
		notifyAll(); //Notificamos para que el otro brazo sepa que puede echar una pieza
		return valor;
		}
	}//cintatransportadora
