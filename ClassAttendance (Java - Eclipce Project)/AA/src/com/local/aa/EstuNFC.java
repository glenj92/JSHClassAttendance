package com.local.aa;

public class EstuNFC {


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellid() {
		return apellid;
	}
	public void setApellid(String apellid) {
		this.apellid = apellid;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public boolean isSeleccion() {
		return seleccion;
	}
	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}
	private String nombre;
	private String apellid;
	private String ident;
	private boolean seleccion;
	public EstuNFC(String nombre, String apellid, String ident, boolean seleccion) {
		super();
		this.nombre = nombre;
		this.apellid = apellid;
		this.ident = ident;
		this.seleccion = seleccion;
	}
	
}
