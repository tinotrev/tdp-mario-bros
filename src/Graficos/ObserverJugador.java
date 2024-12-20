package Graficos;

import Juego.EntidadJugador;

public class ObserverJugador{

	private EntidadJugador jugador;
	private ControladorGraficos controladorGraficos;

	public ObserverJugador(EntidadJugador jugador, ControladorGraficos controladorGraficos) {
		this.jugador = jugador;
		this.controladorGraficos = controladorGraficos;
	}

	public void actualizar(char direccionMovimiento) {
		// Cada vez que se actualice el movimiento del jugador, ajustamos el scroll
		controladorGraficos.actualizarPosPantalla(jugador, direccionMovimiento);
		// Revalidamos la vista para refrescar la pantalla
		controladorGraficos.refrescar();
	}

}