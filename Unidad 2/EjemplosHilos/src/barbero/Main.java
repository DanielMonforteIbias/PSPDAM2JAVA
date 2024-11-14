package barbero;

public class Main {
	 public static void main(String[] args) {
	      int numSillas = 4;
	      int numClientes = 6;
	      BarberoDurmiente s = new BarberoDurmiente(numSillas);
	      new BarberoB(s).start();
	      for(int i = 0; i < numClientes; i++)
	        new ClienteB(s,i).start();
	      
	  }
}
