package Plataformas;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorPlataforma;

public class Solido extends Plataforma {

	public Solido(Sprite s, int e, int i) {
		super(s, e, i);
		rompible = false;
		visitor = new VisitorPlataforma(this);
	}

	public void interactuar(EntidadJugador m) {
		//
	}
	
}
