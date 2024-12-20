package Graficos;

import Juego.EntidadLogica;

public interface Observer {
	public void actualizar();
	public EntidadLogica getEntidadObservada();
	public void setEntidadObservada(EntidadLogica el);
	public void eliminar();
	public void setInvisible(boolean b);
}
