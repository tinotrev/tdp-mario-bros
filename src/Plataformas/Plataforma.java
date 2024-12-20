package Plataformas;

import java.awt.Rectangle;
import java.util.LinkedList;
import Fabricas.Sprite;
import Juego.ElementoColisionable;
import Juego.Entidad;
import Juego.EntidadJugador;
import PowerUps.PowerUp;
import Visitor.VisitorColisiones;
import Visitor.VisitorPlataforma;

public abstract class Plataforma extends Entidad implements ElementoColisionable {

	protected VisitorPlataforma visitor;
	protected boolean rompible;
	static protected LinkedList<Plataforma> lista;

	public Plataforma(Sprite s, int e, int i) {
		super(s, e, i);
	}

	public static void setLista(LinkedList<Plataforma> l) {
		lista = l;
	}

	public static LinkedList<Plataforma> getLista() {
		return lista;
	}

	public void accept(VisitorColisiones v) {
		v.visit(this);
	}

	public Rectangle getBounds() {
		//Se asume q el ancho y alto de la plataforma es 32x32, lo cuual es incorrecto para algunas plataformas
		return new Rectangle(getX(), getY(), 32, 32);
	}

	public VisitorColisiones getVisitor() {
		return visitor;
	}

	public void morir() {
		Plataforma.getLista().remove(this);
		observer.eliminar();
		this.observer = null;
	}

	public boolean rompible() {
		return rompible;
	}

	public abstract void interactuar(EntidadJugador m);

	public boolean tieneHitbox() {
		return true;
	}
	
	public void setPowerUp(PowerUp pu) {
		
	}
}
