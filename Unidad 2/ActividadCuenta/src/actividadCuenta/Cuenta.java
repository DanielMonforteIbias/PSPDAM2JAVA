package actividadCuenta;

public class Cuenta {
	private double saldo;
	private double maximo;

	public Cuenta(double saldo, double maximo) {
		super();
		this.saldo = saldo;
		this.maximo=maximo;
	}

	public double getSaldo() {
		return saldo;
	}
	public double getMaximo() {
		return maximo;
	}
	
	public synchronized void ingreso(double cantidad) {
		saldo=saldo+cantidad;
		if(saldo>maximo) {
			saldo-=cantidad;//Lo revertimos
			System.out.println("Error. Saldo actual: "+saldo+", no es posible ingresar "+cantidad+" porque el maximo es "+maximo);
			System.exit(1);
		}
		else {
			System.out.println("Saldo ingresado: "+cantidad+". Saldo actual: "+saldo);
		}
		
	}
	public synchronized void reintegro(double cantidad) {
		saldo=saldo-cantidad;
		if(saldo<0) {
			saldo+=cantidad;//Lo revertimos
			System.out.println("Error. Saldo actual: "+saldo+", no es posible retirar "+cantidad);
			System.exit(1);
		}
		else {
			System.out.println("Saldo retirado: "+cantidad+". Saldo actual: "+saldo);
		}
	}
	
}
