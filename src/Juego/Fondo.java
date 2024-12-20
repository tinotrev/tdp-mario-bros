package Juego;

import Fabricas.Sprite;
import Visitor.VisitorColisiones;

public class Fondo extends Entidad {
	
	public Fondo(Sprite sprite) {
		super(sprite, 0, 0);
	}

	public void accept(VisitorColisiones v) {
		//
	}
	
}