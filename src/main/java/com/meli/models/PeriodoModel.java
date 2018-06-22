package com.meli.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clima")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class PeriodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dia;
	private double intensidad;
	private String periodo;
	boolean maximo;
		
	public boolean isMaximo() {
		return maximo;
	}
	public void setMaximo(boolean maximo) {
		this.maximo = maximo;
	}
	public Long getDia() {
		return dia;
	}
	public void setDia(Long dia) {
		this.dia = dia;
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
