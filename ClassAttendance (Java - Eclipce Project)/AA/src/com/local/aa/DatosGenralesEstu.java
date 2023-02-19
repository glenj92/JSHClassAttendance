package com.local.aa;

public class DatosGenralesEstu {

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
	public String getAsiste() {
		return asiste;
	}
	public void setAsiste(String asiste) {
		this.asiste = asiste;
	}
	public String getFallas() {
		return fallas;
	}
	public void setFallas(String fallas) {
		this.fallas = fallas;
	}
	public String getExusa() {
		return exusa;
	}
	public void setExusa(String exusa) {
		this.exusa = exusa;
	}
	private String nombre;
	private String apellido;
	private String ident;
	private String asiste;
	private String fallas;
	private String exusa;
	public DatosGenralesEstu(String nombre, String apellido, String ident,
			String asiste, String fallas, String exusa) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.ident = ident;
		this.asiste = asiste;
		this.fallas = fallas;
		this.exusa = exusa;
	}
}
