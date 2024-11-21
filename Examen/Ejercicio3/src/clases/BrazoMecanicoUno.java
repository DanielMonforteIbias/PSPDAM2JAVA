package clases;

public class BrazoMecanicoUno implements Runnable{
	private Cinta c=null;
    
    public BrazoMecanicoUno(Cinta c){
           this.c=c;
    }
    public void run(){
    	double item;
            while (true){
                //sacar producto de la cinta
            	item=c.extraer();
                   try {
   					Thread.sleep(300);
   				} catch (InterruptedException e) {
   					e.printStackTrace();
   				}
            }
    }
}
