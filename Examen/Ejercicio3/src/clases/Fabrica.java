package clases;

public class Fabrica {

	public static void main(String[] args) {
		int ranuras=10;
		//crear la cintatransportadora y los hilos
		Cinta c=new Cinta(ranuras);
		Thread t=new Thread(new BrazoMecanicoUno(c));
		t.start();
		t=new Thread(new BrazoMecanicoDos(c));
		t.start();
	}

}
