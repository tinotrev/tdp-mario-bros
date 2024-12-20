package Graficos;

import Juego.EntidadJugador;
import Juego.EntidadLogica;

public interface ControladorEntreJuegoGrafico {
	public Observer registrarEntidad(EntidadLogica el);
	public void mostrarPantallaPrincipal();
	public void mostrarPantallaGameplay();
	public Observer registrarJugador(EntidadJugador m);
}
