package com.local.aa;

public class Grupos {
public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getNommate() {
		return nommate;
	}
	public void setNommate(String nommate) {
		this.nommate = nommate;
	}
private String grupo;
private String nommate;
public Grupos(String grupo, String nommate) {
	super();
	this.grupo = grupo;
	this.nommate = nommate;
}
}
