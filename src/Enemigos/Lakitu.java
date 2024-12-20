package Enemigos;

import java.util.concurrent.TimeUnit;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Juego.Juego;
import Juego.Mario;
import Visitor.VisitorEnemigo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Lakitu extends Enemigo {

	protected int velocidad = 5;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private boolean primerDisparo = true;
	private Juego juego;
	private boolean permitirAtaque = true;

	public Lakitu(Sprite s, int e, int i, Juego j) {
		super(s, e, i);
		juego = j;
		visitor = new VisitorEnemigo(this);
	}

	public void mover() {
		if(Math.abs(this.x-Mario.posicionX)<350) {
			direccion = Mario.posicionX-this.x<0 ? -1 : 1;
			if(primerDisparo) {
				iniciarTemporizador();
				primerDisparo = false;
			}
		}
		x += direccion * velocidad;
		this.observer.actualizar();
	}

	private void atacar() {
		if(permitirAtaque) {
			int direccion = Mario.posicionX-this.x<0 ? -1 : 1;
			juego.agregarProyectil(this.x+(direccion*32),this.y, direccion);
			iniciarTemporizador();
		}
	}

	public void meSaltaron(EntidadJugador m) {
		m.actualizarPuntaje(getPuntajeMuere());
		morir();
	}

	public int getPuntajeMata() {
		return 0;
	}

	public int getPuntajeMuere() {
		return Constantes.Constantes.LAKITU_MUERE;
	}
	private void iniciarTemporizador() {
		scheduler.schedule(new Runnable() {
			public void run() {
				atacar();
			}
		}, 4, TimeUnit.SECONDS);
	}

	public void setEnSuelo(boolean b) {
		//
	}

	public void resetearVelocidadY() {
		//
	}

	public void morir() {
		Enemigo.getLista().remove(this);
		observer.eliminar();
		this.observer = null;
		permitirAtaque = false; 
		if (scheduler != null) { 
			scheduler.shutdownNow();
		}
	}
	
	public boolean tieneGravedad() {
        return true;
    }
	
	public void meMataron(EntidadJugador m) {
		m.actualizarPuntaje(getPuntajeMuere());
		morir();
	}
	
}