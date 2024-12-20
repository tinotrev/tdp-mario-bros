package Proyectil;

import Fabricas.Sprite;
import Juego.Entidad;
import Juego.Juego;
import Visitor.VisitorColisiones;
import Visitor.VisitorProyectil;
import java.util.LinkedList;

public abstract class Proyectil extends Entidad {

	protected int velocidad;
	protected int direccion;
	protected VisitorProyectil visitor;
	protected static LinkedList<Proyectil> lista;
	protected Juego juego;
	
	public Proyectil(Sprite s, int e, int i, int d) {
		super(s, e, i);
		direccion = d;
	}

	public static void setLista(LinkedList<Proyectil> l) {
		lista = l;
	}

	public static LinkedList<Proyectil> getLista() {
		return lista;
	}

	public void accept(VisitorColisiones v) {
		v.visit(this);
	}

	public void mover() {
		x += direccion * velocidad;
		observer.actualizar();
	}

	public abstract void morir();
		
	public abstract boolean aliado();

	public Juego getJuego() {
		return juego;
	}
	
}