package clases;

public class LaCocina {
	public static void main(String[] args) {
		int tamaño=5;//tamaño de la olla
		Olla laolla=new Olla(tamaño);
		//Crear el cocinero y lanzarlo
		Cocinero cocinero=new Cocinero(laolla);
		Thread t=new Thread(cocinero);
		t.start();
		//Crear 3 caníbales y lanzarlos
		Thread canibal1=new Thread(new Canibales(laolla,1));
		Thread canibal2=new Thread(new Canibales(laolla,2));
		Thread canibal3=new Thread(new Canibales(laolla,3));
		canibal1.start();
		canibal2.start();
		canibal3.start();
	}
}
