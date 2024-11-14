package clases;

public class Olla {

	private int numero = 0; //Capacidad de la olla
	private int[] olla = null;
	private int echar = 0, sacar = 0;
	private int cont = 0; //Cantidad de misioneros en la olla

	public Olla(int numero) {
		this.numero = numero;
		olla = new int[numero];
	}

	public synchronized void EcharAlaOlla(int nummisionero) {
		// Si la olla está llena
		while (cont == numero) {
			System.out.println("No se puede echar a la olla al misionero: "+nummisionero+". OLLA  LLENA ");
			try {
				wait(); //Esperamos para echarlo
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Si se puede cocinar habrá que echar otro
			olla[echar] = nummisionero;
			echar = (echar + 1) % numero; //Asi no se sale nunca del limite de array, al llegar al fin vuelve al principio
			cont++;
			System.out.println("Cocinando al misionero:  " + nummisionero);
			notifyAll(); //Notificamos para que los canibales puedan comer
	}

	public synchronized int SacardelaOlla(int numerocanibal) {
		int misionero = 0;
		// Si la olla está vacía no se puede sacar
		while (cont == 0) {
			System.out.println("El caníbal "+numerocanibal+" no come porque no hay nada más que sacar: OLLA VACÍA ");
			try {
				wait(); //Esperamos
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Si hay misioneros cocinados se podrán sacar y comer
		misionero = olla[sacar];
		sacar = (sacar + 1) % numero;
		cont--;
		System.out.println("\tEl canibal " + numerocanibal + " está comiendo al misionero " + misionero);
		notifyAll(); //Notificamos para que el cocinero vuelva a cocinar
		return misionero;

	}//

}