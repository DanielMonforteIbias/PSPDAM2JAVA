package hilos;

public class HiloEjemploInterrupt extends Thread{
	public void run() {
		try {
			while(!isInterrupted()) {
				System.out.println("En el hilo");
				Thread.sleep(10);
			} 
		}
		catch (InterruptedException e) {
			System.out.println("Ha ocurrido una excepcion");
		}
		System.out.println("Fin del hilo");
	}
	public void interrumpir() {
		interrupt();
	}
	public static void main(String[] args) {
		HiloEjemploInterrupt h1=new HiloEjemploInterrupt();
		h1.start();
		for(int i=0;i<1000000000;i++);
		h1.interrumpir();
	}
}
