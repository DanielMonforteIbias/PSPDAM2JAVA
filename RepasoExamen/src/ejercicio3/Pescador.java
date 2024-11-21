package ejercicio3;

public class Pescador implements Runnable{

private Lonja lalonja=null;
    
    public Pescador(Lonja l){
           lalonja=l;
    }
    public void run(){
    	int atun=0;
            while (true){
                lalonja.echarAlalonja(++atun);
                 try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}      
            }
    }
}