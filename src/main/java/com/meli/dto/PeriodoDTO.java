package com.meli.dto;

public class PeriodoDTO {
	private double intensidad;
	private String periodo;
	private boolean maximo;
	
 	public boolean isMaximo() {
		return maximo;
	}
	public void setMaximo(boolean maximo) {
		this.maximo = maximo;
	}
	public double getIntensidad() {
		return intensidad;
	}
	public void setIntensidad(double intensidad) {
		this.intensidad = intensidad;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
}
