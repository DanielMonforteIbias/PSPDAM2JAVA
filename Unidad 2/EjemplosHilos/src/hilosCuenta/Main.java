package hilosCuenta;

public class Main {
	public static void main(String[] args) {
		Cuenta c=new Cuenta(40);
		SacarDinero h1=new SacarDinero(c,"Maria");
		SacarDinero h2=new SacarDinero(c,"Ivan");
		h1.start();
		h2.start();
	}
}
