package clases;

import java.io.Serializable;

public class Resultado implements Serializable{
	private String palabra;
	private String mensaje;
	private boolean correcto;
	public Resultado(String palabra, String mensaje, boolean correcto) {
		super();
		this.palabra = palabra;
		this.mensaje=mensaje;
		this.correcto = correcto;
	}
	public Resultado() {
		this.palabra="";
		this.mensaje="";
		this.correcto=false;
	}
	public String getPalabra() {
		return palabra;
	}
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	public boolean isCorrecto() {
		return correcto;
	}
	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}