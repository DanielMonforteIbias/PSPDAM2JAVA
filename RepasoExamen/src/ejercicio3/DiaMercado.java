package ejercicio3;

public class DiaMercado {
	public static void main(String[] args) {
		int tam=6;
		//crear la lonja y los hilos
		Lonja lonja=new Lonja(tam);
		Pescador pescador=new Pescador(lonja);
		Thread t=new Thread(pescador);
		t.start();
		for(int i=0;i<3;i++) {
			t=new Thread(new Comprador(lonja,i+1));
			t.start();
		}
	}
}