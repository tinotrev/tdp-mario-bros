package Graficos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.*;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Juego.EntidadLogica;
//import Sonidos.CrearSonido;
//import Sonidos.GestorSonido;

public class PantallaGameplay extends JPanel {

	private static final long serialVersionUID = 1L;
	private int nivelAncho = 3900;
	private ImageIcon fondo;
	//private CrearSonido Sonidos = new CrearSonido();
	//private GestorSonido saltoSonido = Sonidos.crearSonido("salto");

	public PantallaGameplay(Sprite s) {
		fondo = new ImageIcon(s.getRutaImagen());
		setLayout(null);
		setPreferredSize(new Dimension(nivelAncho, 480));
	}

	public void centrarCamara(EntidadJugador mario) {
		int anchoVentana = getParent().getWidth();
		int altoVentana = getParent().getHeight();
		Rectangle vistaMario = new Rectangle(
				mario.getX() - anchoVentana / 2 + 32 / 2,
				mario.getY() - altoVentana / 2 + 32 / 2,
				anchoVentana,
				altoVentana
				);
		scrollRectToVisible(vistaMario);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int x = 0; x < nivelAncho; x += fondo.getIconWidth()) {
			g.drawImage(fondo.getImage(), x, 0, null);
		}
	}

	public Observer incorporarJugador(EntidadJugador jugador) {
		ObserverEntidad observerEntidad = new ObserverEntidad(jugador);
		add(observerEntidad);
		PantallaHud.actualizarLabelsInformacion(jugador);          
		this.setFocusable(true);
		return observerEntidad;
	}

	public Observer incorporarEntidad(EntidadLogica entLogica) {
		ObserverEntidad observerEntidad = new ObserverEntidad(entLogica);
		add(observerEntidad);
		return observerEntidad;
	}

	public void setFondo(Sprite s) {
		fondo = new ImageIcon(s.getRutaImagen());
		repaint();
	}
	
}