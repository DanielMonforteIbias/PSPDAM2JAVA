package clases;

import java.net.Socket;

public class ComunHilos {
	int conexiones; //Numero de conexiones ocupadas en el array
	int actuales; //Numero de conexiones actuales
	int maximo; //Maximo de conexiones permitidas
	Socket tabla[]=new Socket[maximo];
	String mensajes;
	public ComunHilos(int conexiones, int actuales, int maximo, Socket[] tabla) {
		this.conexiones = conexiones;
		this.actuales = actuales;
		this.maximo = maximo;
		this.tabla = tabla;
		mensajes="";
	}
	
	public ComunHilos(){
		super();
	}
	public int getMaximo() {
		return this.maximo;
	}
	public void setMaximo(int maximo) {
		this.maximo=maximo;
	}
	public int getConexiones(){
		return this.conexiones;
	}
	public synchronized void setConexiones(int conexiones) {
		this.conexiones=conexiones;
	}
	public String getMensajes() {
		return this.mensajes;
	}
	public synchronized void setMensajes(String mensajes) {
		this.mensajes=mensajes;
	}
	public int getActuales() {
		return this.getActuales();
	}
	public synchronized void setActuales(int actuales) {
		this.actuales=actuales;
	}
	public synchronized void addTabla(Socket s, int i) {
		tabla[i]=s;
	}
	public Socket getElementoTabla(int i){
		return tabla[i];
	}
}
