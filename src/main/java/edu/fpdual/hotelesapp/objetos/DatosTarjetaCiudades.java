package edu.fpdual.hotelesapp.objetos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosTarjetaCiudades {

	
	private String localizacion;
	private int cantidad;
	private double mediaEstrellas;
	
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
