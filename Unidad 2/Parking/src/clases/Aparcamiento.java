package clases;

public class Aparcamiento {
	private int plazas;

	public Aparcamiento(int plazas) {
		if (plazas < 0)
			plazas = 0;

		this.plazas = plazas;
	}

	public synchronized void entra(String matricula) { // coche entra en el parking
		while(plazas==0) {
			System.out.println(matricula+" esperando, parking lleno.");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(matricula + ": entra en el parking");
		plazas--;
	}

	public synchronized void sale(String matricula) { // el coche deja el parking
		System.out.println(matricula + ": sale del parking");
		plazas++;
		notifyAll();
	}
}