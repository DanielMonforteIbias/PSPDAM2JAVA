package ejercicio3;

public class Lonja {
	
	private int numero=0;
	//lonja puede ser una estructura estática, es decir un array, o dinámica un ArrayList
	private int lonja[];
	//Declara las variables que necesites
	private int sacar=0, echar=0, contadorAtunes=0;
	private final int MAXIMOATUNES=3;
	
	public Lonja(int numero) {
		this.numero=numero;
		this.lonja=new int[numero];
	}
	
	public synchronized void echarAlalonja(int numatun) {
		while(contadorAtunes==lonja.length) {
			System.out.println("\tNo se puede echar el atún "+numatun+". LONJA  LLENA: Esperando comprador ");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Si no está llena se ponen a la venta
		System.out.println("****************************************************");
		System.out.println("************A la venta el atun  "+numatun+"*****************");
		System.out.println("****************************************************");
		lonja[echar]=numatun;
		echar=(echar+1)%lonja.length;
		contadorAtunes++;
		notifyAll();
		
	}
	public synchronized int sacardelalonja(int numerocomprador) {
		int numatun=0;
		while(contadorAtunes==0) {
			System.out.println("LONJA  VACÍA: Comprador "+numerocomprador+" esperando al pescador");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Si hay atunes en la lonja	se lleva 1, 2 o 3 si hay
		int atunesComprados=0;
		if(contadorAtunes>3)atunesComprados=3; //Si hay más de 3 atunes, compraremos 3
		else atunesComprados=contadorAtunes; //Si no, compraremos todos los que hay
		System.out.println("El comprador "+numerocomprador+" se lleva "+atunesComprados+" atunes");
		int i=0;
		while(i<atunesComprados) {
			System.out.println("\tEl atun "+lonja[sacar]);
			sacar=(sacar+1)%lonja.length;
			contadorAtunes--;
			i++;
		}
		notifyAll();
		return numatun;
		}
}