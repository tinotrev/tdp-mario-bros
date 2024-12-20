package Plataformas;

import java.awt.Rectangle;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorPlataforma;

public class Vacio extends Plataforma {

	public Vacio(Sprite s, int e, int i) {
		super(s, e, i);
		rompible = false;
		visitor = new VisitorPlataforma(this);
	}

	public void interactuar(EntidadJugador m) {
		m.perderVida();
		m.teletransportar();
		m.actualizarPuntaje(Constantes.Constantes.VACIO_MATA);
	}
	
	public boolean tieneHitbox() {
		return false;
	}
	
	public Rectangle getBounds() {
		//Se asume q el ancho y alto de la plataforma es 32x32, lo cuual es incorrecto para algunas plataformas
		return new Rectangle(getX(), getY(), 32, 32);
	}

}