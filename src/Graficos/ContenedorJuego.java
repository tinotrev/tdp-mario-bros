package Graficos;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Juego.EntidadJugador;

public class ContenedorJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private PantallaHud hudPanel;
	private PantallaGameplay gameplayPanel;

	public ContenedorJuego(PantallaGameplay pg,PantallaHud hud) {
		setLayout(new BorderLayout());
		hudPanel = hud;
		gameplayPanel = pg;
		add(hudPanel, BorderLayout.NORTH);
		add(new JScrollPane(gameplayPanel), BorderLayout.CENTER);
	}

	public void actualizarHud(EntidadJugador jugador) {
		PantallaHud.actualizarLabelsInformacion(jugador);
	}

	public PantallaGameplay getGameplayPanel() {
		return gameplayPanel;
	}
	
}
