package actividadCuenta;

public class Main {
	public static void main(String[] args) {
		Cuenta c=new Cuenta(300,1000);
		Persona p1=new Persona("Pablo",c);
		Persona p2=new Persona("Carmen",c);
		p1.start();
		p2.start();
	}
}
