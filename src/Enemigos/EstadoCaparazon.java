package Enemigos;

import java.awt.Rectangle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Juego.EntidadJugador;

public class EstadoCaparazon implements EstadoKoopa {

	private KoopaTroopa koopa;
	private boolean inmortal;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public EstadoCaparazon(KoopaTroopa kt) {
		koopa = kt;
		koopa.setY(koopa.getY() + 16);  // Ajuste inicial para mantenerlo en el suelo
		koopa.setSprite(koopa.getSprites().get(2));
		inmortal=true;
		iniciarTemporizador();
	}

	public void meSaltaron(EntidadJugador m) {
		if(!inmortal) {
			m.actualizarPuntaje(koopa.getPuntajeMuere());
			koopa.morir();
		}
	}

	public int getKey() {
		return 1;
	}

	public void mover() {
		//
	}

	public Rectangle getBounds() {
		return new Rectangle(koopa.getX(), koopa.getY(), 32, 32);
	}
	
	private void iniciarTemporizador() {
		scheduler.schedule(new Runnable() {
			public void run() {
				inmortal = false;
			}
		}, 1, TimeUnit.SECONDS);
	}
	
	public void meMataron(EntidadJugador m) {
		if(!inmortal) {
			m.actualizarPuntaje(koopa.getPuntajeMuere());
			koopa.morir();
		}
	}
	
}
