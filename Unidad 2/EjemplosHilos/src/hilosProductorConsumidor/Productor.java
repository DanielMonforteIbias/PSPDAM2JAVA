package hilosProductorConsumidor;

public class Productor extends Thread {
	private Cola cola;
	private int n;
	public Productor(Cola cola, int n) {
		super();
		this.cola = cola;
		this.n = n;
	}
	public void run() {
		for(int i=0;i<5;i++) {
			cola.put(i);
			System.out.println(i+"=>Productor "+n+" produce "+i);
			try {
				sleep(100);
			}catch(InterruptedException e) {}
		}
	}
}
