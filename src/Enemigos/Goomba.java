package Enemigos;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorEnemigo;

public class Goomba extends Enemigo {

	protected int velocidad = 2;

	public Goomba(Sprite s, int e, int i) {
		super(s, e, i);
		visitor = new VisitorEnemigo(this);
	}

	public void mover() {
		x += direccion * velocidad;
		this.observer.actualizar();
	}

	public void meSaltaron(EntidadJugador m) {
		m.actualizarPuntaje(getPuntajeMuere());
		morir();
	}

	public int getPuntajeMata() {
		return Constantes.Constantes.GOOMBA_MATA;
	}

	public int getPuntajeMuere() {
		return Constantes.Constantes.GOOMBA_MUERE;
	}

	public void setEnSuelo(boolean b) {
		//
	}

	public void resetearVelocidadY() {
		//
	}
	
	public void meMataron(EntidadJugador m) {
		m.actualizarPuntaje(getPuntajeMuere());
		morir();
	}
	
}