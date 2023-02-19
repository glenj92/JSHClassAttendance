package com.local.aa;

public class Estu {
	

	public String getApellid() {
		return apellid;
	}
	public void setApellid(String apellid) {
		this.apellid = apellid;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	
	private String nombre;
	private String apellid;
	private String ident;
	
	public Estu(String nombre, String apellid, String ident) {
		super();
		this.nombre = nombre;
		this.apellid = apellid;
		this.ident = ident;
		
	}
	
	
	
	

}
