package com.local.aa;

public class Persona {
	


	public boolean isChecked2() {
		return checked2;
	}
	public void setChecked2(boolean checked2) {
		this.checked2 = checked2;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	private String nombre;
	private String apellido;
	private String ident;
	private boolean checked;
	private boolean checked2;
	public Persona(String nombre, String apellido, String ident,
			boolean checked, boolean checked2) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.ident = ident;
		this.checked = checked;
		this.checked2 = checked2;
	}
	
	
	

}
