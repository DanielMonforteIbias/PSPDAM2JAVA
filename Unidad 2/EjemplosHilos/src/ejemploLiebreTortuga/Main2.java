package ejemploLiebreTortuga;

public class Main2 {
	public static void main(String[] args) {
		Thread tortuga=new Thread(new Tortuga());
		Thread tortuga2=new Thread(new Tortuga());
		Thread tortuga3=new Thread(new Tortuga());
		Thread liebre=new Thread(new Liebre());
		Thread liebre2=new Thread(new Liebre());
		Thread liebre3=new Thread(new Liebre());
		tortuga.start();
		tortuga2.start();
		tortuga3.start();
		liebre.start();
		liebre2.start();
		liebre3.start();
	}
}
