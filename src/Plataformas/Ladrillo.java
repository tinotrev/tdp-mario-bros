package Plataformas;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorPlataforma;

public class Ladrillo extends Plataforma {

	public Ladrillo(Sprite s, int e, int i) {
		super(s, e, i);
		rompible = true;
		visitor = new VisitorPlataforma(this);
	}

	public void interactuar(EntidadJugador m) {

	}

}