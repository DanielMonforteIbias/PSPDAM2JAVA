package classes;

public class Tablero {
	private char tablero[][];
	private int numBarcos;
	
	public Tablero() {
		tablero=new char[10][10];
		numBarcos=10;
		rellenarTablero();
	}
	public Tablero(int numBarcos) {
		tablero=new char[10][10];
		this.numBarcos=numBarcos;
		rellenarTablero();
	}
	
	public char[][] getTablero() {
		return tablero;
	}

	public void setTablero(char[][] tablero) {
		this.tablero = tablero;
	}

	public int getNumBarcos() {
		return numBarcos;
	}
	public void setNumBarcos(int numBarcos) {
		this.numBarcos = numBarcos;
	}
	public void rellenarTablero() {
		for(int i=0;i<tablero.length;i++) {
			for(int j=0;j<tablero[0].length;j++) {
				tablero[i][j]='A';
			}
		}
		int contadorBarcos=0;
		while(contadorBarcos<10) {
			int i=(int)(Math.random()*10);
			int j=(int)(Math.random()*10);
			if(!comprobarPosicion(i, j)) {
				tablero[i][j]='B';
				contadorBarcos++;
			}
		}
		imprimirTablero();
	}
	public boolean comprobarPosicion(int i,int j) {
		boolean ocupada=false;
		if(tablero[i][j]=='B') ocupada=true;
		return ocupada;
	}
	public void imprimirTablero() {
		for(int i=0;i<tablero.length;i++) {
			for(int j=0;j<tablero[0].length;j++) {
				System.out.print(tablero[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Tablero t=new Tablero();
		System.out.println(t.getTablero()[0][0]==' ');
	}
}
