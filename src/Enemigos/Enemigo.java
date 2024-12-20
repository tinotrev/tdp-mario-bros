package Enemigos;

import java.awt.Rectangle;
import java.util.LinkedList;

import Fabricas.Sprite;
import Juego.ElementoColisionable;
import Juego.Entidad;
import Juego.EntidadJugador;
import Visitor.VisitorColisiones;
import Visitor.VisitorEnemigo;

public abstract class Enemigo extends Entidad implements ElementoColisionable {

	protected int velocidad;
	protected int direccion = -1;
	protected VisitorEnemigo visitor;
	static protected LinkedList<Enemigo> lista;

	public Enemigo(Sprite s, int e, int i) {
		super(s, e, i);
	}

	public static void setLista(LinkedList<Enemigo> l) {
		lista = l;
	}

	public static LinkedList<Enemigo> getLista() {
		return lista;
	}

	public void accept(VisitorColisiones v) {
		v.visit(this);
	}

	public Rectangle getBounds() {
		//Se asume q el ancho y alto del enemigo es 32x32
		return new Rectangle(getX(), getY(), 32, 32);
	}

	public void cambiarDireccion(){
		if(direccion == -1)
			direccion = 1;
		else
			direccion = -1;
	}

	public void mover() {
		x += direccion * velocidad;
		observer.actualizar();
	}

	public void meSaltaron(EntidadJugador m) {
		
	}

	public VisitorColisiones getVisitor() {
		return visitor;
	}

	public void morir() {
		Enemigo.getLista().remove(this);
		observer.eliminar();
		this.observer = null;
	}

	abstract public int getPuntajeMata();

	abstract public int getPuntajeMuere();

	public boolean meTocaron(EntidadJugador m) {
		return m.getEstado().tocarEnemigo(this);
	}

	public abstract void setEnSuelo(boolean b);

	public abstract void resetearVelocidadY();

	public void setPosicionX(int x) {
		this.x = x;
	}

	public void setPosicionY(int y) {
		this.y = y;
	}

	public void meMataron(EntidadJugador m) {
		
	}

	public boolean tieneGravedad() {
		return false;
	}

}