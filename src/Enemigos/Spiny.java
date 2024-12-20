package Enemigos;

import java.util.Map;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorEnemigo;

public class Spiny extends Enemigo {

	protected int velocidad = 2;
	protected Map<Integer, Sprite> sprites;
	protected boolean enSuelo = false;
	protected int velocidadY = 0;
	protected final int GRAVEDAD = 1;
	protected final int VELOCIDAD_CAIDA_MAXIMA = 10;

	public Spiny(Map<Integer, Sprite> sp, int e, int i) {
		super(null, e, i);
		sprites = sp;
		sprite = sp.get(0);
		visitor = new VisitorEnemigo(this);
	}

	public void mover() {
		if(!enSuelo) {
			velocidadY += GRAVEDAD;
			if (velocidadY > VELOCIDAD_CAIDA_MAXIMA) {
				velocidadY = VELOCIDAD_CAIDA_MAXIMA;
			}
		} else {
			velocidadY = 0;
		}
		if(direccion > 0) {
			sprite = sprites.get(0);
		} else {
			sprite = sprites.get(1);
		}
		x += direccion * velocidad;
		y += velocidadY;
		enSuelo = false;
		this.observer.actualizar();
	}

	public void meSaltaron(EntidadJugador m) {
		m.getEstado().recibirDanio();
	}

	public int getPuntajeMata() {
		return Constantes.Constantes.SPINY_MATA;
	}

	public int getPuntajeMuere() {
		return Constantes.Constantes.SPINY_MUERE;
	}
	
	public void setEnSuelo(boolean enSuelo) {
		this.enSuelo = enSuelo;
	}

	public void resetearVelocidadY() {
		this.velocidadY = 0;
	}
	
	public void meMataron(EntidadJugador m) {
		m.actualizarPuntaje(getPuntajeMuere());
		morir();
	}
	
	public boolean tieneGravedad() {
        return true;
    }
	
}