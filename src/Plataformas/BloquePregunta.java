package Plataformas;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import PowerUps.PowerUp;
import Visitor.VisitorPlataforma;

public class BloquePregunta extends Plataforma {

	protected PowerUp powerUp;
	protected boolean activado;
	protected Sprite[] sprites;

	public BloquePregunta(Sprite[] s, int e, int i) {
		super(s[0], e, i);
		rompible = false;
		visitor = new VisitorPlataforma(this);
		activado = true;
		sprites = s;
	}

	public void interactuar(EntidadJugador m) {
		if(activado) {
			powerUp.setActivado(true);
			activado = false;
			sprite = sprites[1];
			observer.actualizar();
		}
	}

	public void setPowerUp(PowerUp pu) {
		powerUp = pu;
	}

}