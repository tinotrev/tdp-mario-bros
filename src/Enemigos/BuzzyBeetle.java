package Enemigos;

import java.util.Map;
import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorEnemigo;

public class BuzzyBeetle extends Enemigo {

	protected int velocidad = 2;
	protected Map<Integer, Sprite> sprites;

	public BuzzyBeetle(Map<Integer, Sprite> sp, int e, int i) {
		super(null, e, i);
		sprites = sp;
		sprite = sp.get(0);
		visitor = new VisitorEnemigo(this);
	}

	public void mover() {
		if(direccion > 0) {
			sprite = sprites.get(0);
		}
		else {
			sprite = sprites.get(1);
		}
		x += direccion * velocidad;
		this.observer.actualizar();
	}

	public void meSaltaron(EntidadJugador m) {
		m.actualizarPuntaje(getPuntajeMuere());
		morir();
	}
	
	public int getPuntajeMata() {
		return Constantes.Constantes.BUZZY_BEETLE_MATA;
	}

	public int getPuntajeMuere() {
		return Constantes.Constantes.BUZZY_BEETLE_MUERE;
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
