package hilosCuenta;

public class SacarDinero extends Thread {
	private Cuenta cuenta;
	private String nombre;
	public SacarDinero(Cuenta cuenta, String nombre) {
		super(nombre);
		this.cuenta = cuenta;
	}
	public void run() {
		for(int i=0;i<=4;i++) {
			cuenta.retirarDinero(10, getName());
		}
	}
}
