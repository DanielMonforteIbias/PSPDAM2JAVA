package actividadCuenta;

public class Persona extends Thread{
	private String nombre;
	private Cuenta c;

	public Persona(String nombre, Cuenta c) {
		super();
		this.nombre = nombre;
		this.c=c;
	}

	public String getNombre() {
		return nombre;
	}

	public void run() {
		 int aleatorio = ((int) (Math.random()*500+1));
		 c.ingreso(aleatorio);
		 aleatorio = ((int) (Math.random()*500+1));
		 try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.getStackTrace();
			}
		 c.reintegro(aleatorio);
		 try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.getStackTrace();
			}
		 aleatorio = ((int) (Math.random()*500+1));
		 c.reintegro(aleatorio);
		 try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.getStackTrace();
			}
		 aleatorio = ((int) (Math.random()*500+1));
		 c.ingreso(aleatorio);
	}
}
