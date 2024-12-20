package Graficos;

import Juego.EntidadLogica;

public class ObserverEntidad extends ObserverGrafico {

	private static final long serialVersionUID = 1L;

	protected ObserverEntidad(EntidadLogica el) {
		super(el);
		actualizar();
	}

	public EntidadLogica getEntidadObservada() {
		return super.entidadObservada;
	}
	
	public void setInvisible(boolean b) {
		setVisible(!b);
	}
	
}
