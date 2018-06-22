package com.meli.services;

import java.awt.geom.Point2D;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.meli.models.PeriodoModel;
import com.meli.repository.PeriodoRepository;
import com.meli.util.Utiles;


@Service
@Transactional
public class PeriodoService {

	static long VelocidadFerengi = 1;
	static long DireccionFerengi = 1;
	static long DistanciaFerengi = 500;

	static long VelocidadBetasoide = 3;
	static long DireccionBetasoide = 1;
	static long DistanciaBetasoide = 2000;

	static long VelocidadVulcano = 5;
	static long DireccionVulcano = -1;
	static long DistanciaVulcano = 1000;

	private PeriodoRepository repository;

	public PeriodoService(PeriodoRepository repository) {
		this.repository = repository;
	}

	public PeriodoModel iniciarCarga() {
		int diasXRecorrer = 3650;
		double maximaIntensidad = 0;
		long diaPicoLLuvia = 0;
		
		//Si ya hay registros no vuelvo a insertar..
		long hayRegistros = repository.count();
		
		if(hayRegistros == 0){
			for (long dia = 0; dia < diasXRecorrer; dia++) {
				PeriodoModel periodoModel = new PeriodoModel();
				
				long rotacionFerengi = Utiles.rotar(dia, VelocidadFerengi, DireccionFerengi);
				long rotacionBetasoide = Utiles.rotar(dia, VelocidadBetasoide, DireccionBetasoide);
				long rotacionVulcano = Utiles.rotar(dia, VelocidadVulcano, DireccionVulcano);

				Point2D posicionFerengi = Utiles.posicionar(rotacionFerengi, DistanciaFerengi);
				Point2D posicionBetasoide = Utiles.posicionar(rotacionBetasoide, DistanciaBetasoide);
				Point2D posicionVulcano = Utiles.posicionar(rotacionVulcano, DistanciaVulcano);

				String periodo = Utiles.calcularClima(posicionFerengi, posicionBetasoide, posicionVulcano);
				double intensidad = 0;

				if (periodo == "lluvia") {
					intensidad = Utiles.calcularPerimetro(posicionFerengi, posicionBetasoide, posicionVulcano);

					periodoModel.setIntensidad(intensidad);

					if (intensidad > maximaIntensidad) {
						maximaIntensidad = intensidad;
						diaPicoLLuvia = dia;
					}

				} else {
					periodoModel.setIntensidad(intensidad);
				}
				
				periodoModel.setMaximo(false);
				periodoModel.setPeriodo(periodo);

				repository.save(periodoModel);

			}

			// Guardo el maximo
			if (diaPicoLLuvia > 0) {
				PeriodoModel periodoMax = new PeriodoModel();
				periodoMax.setDia(diaPicoLLuvia);
				periodoMax.setIntensidad(maximaIntensidad);
				periodoMax.setPeriodo("lluvia");
				periodoMax.setMaximo(true);
				repository.save(periodoMax);
			}	
		}

		return null;

		/*
		 * int sumatoria = contLluvia + contSequia + contNormal + contOptima;
		 * System.out.println("El dia que hubo mas lluvia fue el " +
		 * diaPicoLLuvia + " perimetro maximo del triangulo: " +
		 * maximaIntensidad);
		 * 
		 * System.out.println("Contador dias de sequia: " + contSequia);
		 * System.out.println("Contador dias de lluvia: " + contLluvia);
		 * System.out.println("Contador de dias normales: " + contNormal);
		 * System.out.println("Contador de dias optimos: " + contOptima);
		 * 
		 * System.out.println("Sumatoria: " + sumatoria);
		 * 
		 * System.out.println("Cantidad de años calculados: " + (diasRecorrer /
		 * 365 ));
		 */
	}

	public PeriodoModel save(double intensidad, String periodo) {
		PeriodoModel clima = new PeriodoModel();
		clima.setIntensidad(intensidad);
		clima.setPeriodo(periodo);
		return repository.save(clima);
	}

	public PeriodoModel getPeriodoPorDia(long dia) {
		return repository.findByDia(dia);
	}

	public List<PeriodoModel> getPorPeriodos(String condicion) {
		System.out.println("Service Me llego el periodo " + condicion);
		switch (condicion) {
		case "lluvia":
			return repository.findByPeriodo("lluvia");
		case "normal":
			return repository.findByPeriodo("normal");
		case "sequia":
			return repository.findByPeriodo("sequia");
		default:
			return null;
		}
	}

	public PeriodoModel getMaximo() {
		return repository.findByMaximo(true);
	}


}
