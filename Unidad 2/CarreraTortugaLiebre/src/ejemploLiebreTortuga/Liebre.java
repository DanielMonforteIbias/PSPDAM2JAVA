package ejemploLiebreTortuga;

public class Liebre implements Runnable{
	private int numeroLiebre;
	public Liebre(int numeroLiebre) {
		this.numeroLiebre=numeroLiebre;
	}
	public void run() {
		int i=0;
		System.out.println("Empieza la liebre "+numeroLiebre);
		while(i<5) {
			try {
				Thread.sleep(2000);
				System.out.print("  L"+numeroLiebre+"  ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("La liebre "+numeroLiebre+" llega a la meta");
	}
}
