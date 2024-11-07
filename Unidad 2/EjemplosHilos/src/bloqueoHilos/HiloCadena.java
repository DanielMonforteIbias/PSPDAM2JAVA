package bloqueoHilos;

public class HiloCadena extends Thread{
	private ObjetoCompartido compartido;
	private String cadena;
	public HiloCadena(ObjetoCompartido compartido, String cadena) {
		super();
		this.compartido = compartido;
		this.cadena = cadena;
	}
	public void run() {
		synchronized(compartido){
			for(int i=0;i<10;i++) {
				compartido.pintaCadena(cadena);
				compartido.notify();
				try {
					compartido.wait();
				}catch(InterruptedException e) {}
			}
			compartido.notify();
		}
		System.out.println(cadena+" Finalizado");
	}
}
