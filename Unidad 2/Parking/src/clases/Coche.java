package clases;

public class Coche extends Thread{

	
	private Aparcamiento p ;
	 
	public Coche ( String matrícula,Aparcamiento p ) {
	setName(matrícula) ;
	this.p = p ;

	}
	 
	public void run ( ) {
//	while ( true ) {
		try {
			sleep ((int)(Math.random()*1000)) ; // Parar antes de entrar en el parking
		} catch (InterruptedException e) { }
	 
	p.entra(getName());
	//System.out.println (getName() + ": entra en el parking" ) ;
	 
	try {
	sleep ((int)(Math.random()*2000)) ; // Simular estancia esperando un tiempo aleatorio
	} catch (InterruptedException e) { }
	 
	p.sale(getName()) ;
	//System.out.println(getName()+": sale de parking" ) ;
	//}
}
}