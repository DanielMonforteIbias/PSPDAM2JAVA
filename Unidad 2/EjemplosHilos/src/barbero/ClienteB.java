package barbero;

public class ClienteB extends Thread{
	
	  BarberoDurmiente b;
	  int num;

	  public ClienteB(BarberoDurmiente b, int num){
		  this.b=b;
		  this.num=num;
	  }
	 
	  public void run(){
		  boolean t=false;
		  while(true){
			  try {
				
				Thread.sleep(2000);
				 t=b.entrar(num);
				if(t)//Espera para entrar otra vez porque se lo he cortado
					Thread.sleep(200000);
					
				else //Se da una vuelta e intentar� entrar de nuevo
					Thread.sleep(4000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		  }
	  }
}