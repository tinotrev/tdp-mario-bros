package Plataformas;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Juego.Juego;
import Visitor.VisitorPlataforma;

public class Bandera extends Plataforma {
	
	private Juego juego;
	
	public Bandera(Sprite s, int e, int i) {
		super(s, e, i);
		rompible = false;
		visitor = new VisitorPlataforma(this);
	}

	public void interactuar(EntidadJugador m) {
		juego.cambiarNivel();
	}
	
	public void setJuego(Juego j) {
		juego = j;
	}

}