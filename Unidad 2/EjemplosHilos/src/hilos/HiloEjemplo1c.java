package hilos;

public class HiloEjemplo1c extends Thread{
	int numeroHilo=0;
	public HiloEjemplo1c(int numero) {
		numeroHilo=numero;
	}
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("Hola, soy el hilo numero "+numeroHilo+" y estoy en la iteracion "+i);
		}
	}
}
