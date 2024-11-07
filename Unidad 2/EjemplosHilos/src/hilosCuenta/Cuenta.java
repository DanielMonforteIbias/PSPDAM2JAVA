package hilosCuenta;

public class Cuenta {
	private int saldo;
	
	public Cuenta(int saldo) {
		this.saldo=saldo;
	}
	public int getSaldo() {
		return saldo;
	}
	public void restarSaldo(int cantidad) {
		saldo=saldo-cantidad;
	}
	public synchronized void retirarDinero(int cantidad, String nombre) {
		if(getSaldo()>=cantidad) {
			System.out.println("Se va a retirar saldo (el saldo actual es "+getSaldo()+")");
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.getStackTrace();
			}
			restarSaldo(cantidad);
			System.out.println("\t"+nombre+" retira "+cantidad+"€.   ACTUAL: "+getSaldo());
		}
		else {
			System.out.println(nombre+" no se puede retirar dinero porque no hay saldo suficiente (actual: "+getSaldo()+")");
		}
		if(getSaldo()<0) System.out.println("SALDO NEGATIVO: "+getSaldo());
	}
}
