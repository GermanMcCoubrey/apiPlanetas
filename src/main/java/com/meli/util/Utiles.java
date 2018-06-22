package com.meli.util;

import java.awt.geom.Point2D;

public class Utiles {
	public static long rotar(long dias, long velocidad, long direccion) {
		long grados = dias * velocidad;
		grados = (grados * direccion) % 360;
		if (grados < 0) {
			grados = 360 + grados;
		}
		return grados;
	}

	public static Point2D posicionar(long rotacion, long distancia) {
		double x = distancia * Math.cos(Math.toRadians(rotacion));
		double y = distancia * Math.sin(Math.toRadians(rotacion));
		Point2D.Double punto = new Point2D.Double(x, y);
		return punto;
	}

	public static String calcularClima(Point2D posicionFerengi, Point2D posicionBetasoide, Point2D posicionVulcano) {

		Point2D.Double posicionSol = new Point2D.Double(0, 0);

		if (estanAlineados(posicionFerengi, posicionBetasoide, posicionVulcano)) {
			if (estanAlineados(posicionFerengi, posicionBetasoide, posicionSol)) {
				// contSequia += 1; debo guardar uno por unos estos registros
				return "sequia";
			} else {
				// contOptima += 1; 1; debo guardar uno por unos estos registros
				return "optima";
			}
		} else {
			if (solAdentro(posicionFerengi, posicionBetasoide, posicionVulcano, posicionSol)) {
				// contLluvia += 1; 1; debo guardar uno por unos estos registros
				return "lluvia";
			} else {
				// contNormal += 1; 1; debo guardar uno por unos estos registros
				return "normal";
			}
		}

	}

	public static boolean estanAlineados(Point2D punto1, Point2D punto2, Point2D punto3) {
		double areaDelTriangulo = calcularArea(punto1, punto2, punto3);

		if (areaDelTriangulo == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean solAdentro(Point2D punto1, Point2D punto2, Point2D punto3, Point2D punto4) {
		double areaDelTriangulo = calcularArea(punto1, punto2, punto3);
		double area1 = calcularArea(punto1, punto2, punto4);
		double area2 = calcularArea(punto2, punto3, punto4);
		double area3 = calcularArea(punto3, punto1, punto4);

		if ((area1 + area2 + area3) == areaDelTriangulo) {
			return true;
		} else {
			return false;
		}

	}

	public static double calcularArea(Point2D punto1, Point2D punto2, Point2D punto3) {
		return (punto1.getX() * (punto2.getY() - punto3.getY()) + punto2.getX() * (punto3.getY() - punto1.getY())
				+ punto3.getX() * (punto1.getY() - punto2.getY())) / 2;
	}

	public static double calcularPerimetro(Point2D punto1, Point2D punto2, Point2D punto3) {
		return calcularDistancia(punto1, punto2) + calcularDistancia(punto2, punto3)
				+ calcularDistancia(punto3, punto1);
	}

	public static double calcularDistancia(Point2D punto1, Point2D punto2) {
		double distanciaHorizontal = Math.pow(punto2.getX() - punto1.getX(), 2);
		double distanciaVertical = Math.pow(punto2.getY() - punto1.getY(), 2);
		return Math.sqrt(distanciaHorizontal + distanciaVertical);
	}

}
