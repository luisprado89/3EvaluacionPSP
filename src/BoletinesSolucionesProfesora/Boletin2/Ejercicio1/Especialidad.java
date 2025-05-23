package BoletinesSolucionesProfesora.Boletin2.Ejercicio1;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Especialidad implements Serializable {
	int id;
	String nombreespe;


	public Especialidad(int id, String nombreespe) {
		super();
		this.id = id;
		this.nombreespe = nombreespe;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombreespe() {
		return nombreespe;
	}


	public void setNombreespe(String nombreespe) {
		this.nombreespe = nombreespe;
	}

	
}
