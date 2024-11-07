package hilosCompartirInformacion;

public class Contador {
	private int c=0;

	public Contador(int c) {
		super();
		this.c = c;
	}
	public void incrementa() {
		c+=1;
	}
	public void decrementa() {
		c-=1;
	}
	public int valor() {
		return c;
	}
}
