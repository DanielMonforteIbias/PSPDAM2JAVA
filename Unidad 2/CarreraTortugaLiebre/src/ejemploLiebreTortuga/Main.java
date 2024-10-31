package ejemploLiebreTortuga;

public class Main {
	public static void main(String[] args) {
		for(int i=0;i<3;i++) {
			Thread tortuga=new Thread(new Tortuga(i+1));
			Thread liebre=new Thread(new Liebre(i+1));
			tortuga.start();
			liebre.start();
		}
	}
}
