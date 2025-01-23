package clases;

public class ObjetoCompartido {
	private int numero;
	private boolean acabo;
	private int ganador;
	
	public ObjetoCompartido(int numero) {
		this.numero=numero;
		this.acabo=false;
	}

	public boolean isAcabo() {
		return acabo;
	}

	public int getGanador() {
		return ganador;
	}

	public synchronized String nuevaJugada(int jugador, int suNumero) {
		String cad="";
		if(!isAcabo()) {
			if(suNumero>numero) cad="Numero demasiado grande";
			else if(suNumero<numero) cad="Numero demasiado pequeño";
			else if(suNumero==numero) {
				cad="Jugador "+jugador+" gana, adivino el numero "+numero;
				acabo=true;
				ganador=jugador;
			}
		}
		else cad="Jugador "+ganador+" adivino el numero "+numero;
		return cad;
	}
}
