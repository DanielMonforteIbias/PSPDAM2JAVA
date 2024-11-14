package clases;

public class Cocinero implements Runnable {

	private Olla laolla = null;

	public Cocinero(Olla o) {
		laolla = o;
		;
	}

	public void run() {
		int misionero = 0;
		while (true) {
			laolla.EcharAlaOlla(++misionero);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
