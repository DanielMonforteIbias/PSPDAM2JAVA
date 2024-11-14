package clases;

public class Canibales implements Runnable {

private Olla laolla=null;
private int numero=0;
    
    public Canibales(Olla o,int num){
           laolla=o;
           numero=num;
    }
    public void run(){
    	
    	int canibal=0;
            while (true){
                   canibal=laolla.SacardelaOlla(numero);
                   try {
   					Thread.sleep(400);
   				} catch (InterruptedException e) {
   					e.printStackTrace();
   				}
            }
    }
}