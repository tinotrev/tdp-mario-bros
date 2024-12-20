package Enemigos;

import java.awt.Rectangle;

import Juego.EntidadJugador;

public class EstadoEstandar implements EstadoKoopa {

	protected KoopaTroopa koopa;

	public EstadoEstandar(KoopaTroopa kt) {
		koopa = kt;
	}

	public void mover() {
		if(koopa.direccion > 0) {
			koopa.setSprite(koopa.getSprites().get(0));
		}
		else { koopa.setSprite(koopa.getSprites().get(1));
		}
		koopa.x += koopa.direccion * koopa.velocidad;
		koopa.observer.actualizar();
	}

	public void meSaltaron(EntidadJugador m) {
		koopa.setEstado(new EstadoCaparazon(koopa));
	}

	public int getKey() {
		return 0;
	}

	public Rectangle getBounds() {
		return new Rectangle(koopa.getX(), koopa.getY(), 32, 48);
	}
	
	public void meMataron(EntidadJugador m) {
		koopa.setEstado(new EstadoCaparazon(koopa));
	}

}