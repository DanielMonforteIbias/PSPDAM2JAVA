package ejemploLiebreTortuga;

public class Tortuga implements Runnable{
	private int numeroTortuga;
	
	public Tortuga(int numeroTortuga) {
		this.numeroTortuga = numeroTortuga;
	}

	public void run() {
		int i=0;
		System.out.println("Empieza la tortuga "+numeroTortuga);
		while(i<5) {
			try {
				Thread.sleep(5000);
				System.out.print("  T"+numeroTortuga+"  ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("La tortuga "+numeroTortuga+" llega a la meta");
	}
}
