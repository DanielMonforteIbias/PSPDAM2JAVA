package hilos;

public class Hilos1cMain {
	public static void main(String[] args) {
		System.out.println("Inicio del hilo principal");
		for(int i=0;i<5;i++) {
			HiloEjemplo1c t=new HiloEjemplo1c(i);
			t.start();
		}
		System.out.println("Fin del hilo principal");
	}
}
