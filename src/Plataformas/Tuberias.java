package Plataformas;

import java.awt.Rectangle;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorPlataforma;

public class Tuberias extends Plataforma {

	public Tuberias(Sprite s, int e, int i) {
		super(s, e, i);
		rompible = false;
		visitor = new VisitorPlataforma(this);
	}
	
	public Rectangle getBounds() {
		//Se asume q el ancho y alto de la plataforma es 32x32, lo cuual es incorrecto para algunas plataformas
		return new Rectangle(getX(), getY(), 64, 64);
	}

	public void interactuar(EntidadJugador m) {
		//
	}
	
}