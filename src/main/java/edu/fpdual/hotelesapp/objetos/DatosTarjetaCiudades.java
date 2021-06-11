package edu.fpdual.hotelesapp.objetos;

import lombok.Getter;
import lombok.Setter;
/**
 * Clase para los datos de hoteles en las tarjetas de ciudades
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 *
 */
@Getter
@Setter
public class DatosTarjetaCiudades {
	/**
	 * Localizacion del hotel
	 */
	private String localizacion;
	/**
	 * Cantidad de hoteles
	 */
	private int cantidad;
	/**
	 * Media de estrella de los hoteles
	 */
	private double mediaEstrellas;
	
	/**
	 * Constructor para los datos
	 * @param localizacion Localizacion del hotel
	 * @param cantidad Cantidad de hoteles
	 * @param sumaEstrellas Media de estrellas
	 */
	public DatosTarjetaCiudades(String localizacion, int cantidad, double sumaEstrellas) {
		this.localizacion = localizacion;
		this.cantidad = cantidad;
		if(cantidad!=0) {
			this.mediaEstrellas = sumaEstrellas/cantidad;
		}
	}

	@Override
	public String toString() {
		return "DatosTarjetaCiudades [localizacion=" + localizacion + ", cantidad=" + cantidad + ", mediaEstrellas="
				+ mediaEstrellas + "]";
	}
	
	
	
}
