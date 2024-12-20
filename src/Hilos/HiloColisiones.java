package Hilos;

import java.util.LinkedList;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import Juego.Escenario;
import Plataformas.Plataforma;
import PowerUps.PowerUp;

public class HiloColisiones extends Thread {

	protected EntidadJugador mario;
	protected LinkedList<Enemigo> enemigos;
	protected LinkedList<PowerUp> powerUps;
	protected LinkedList<Plataforma> plataformas;
	private boolean running;

	public void setRunning(boolean b) {
		running = b;
	}

	public HiloColisiones(Escenario escenario) {
		mario = escenario.getMario();
		enemigos = escenario.getEnemigos();
		powerUps = escenario.getPowerUps();
		plataformas = escenario.getPlataformas();
		running = true;
	}

	public void run() {
		while(running) {
			chequearColisiones();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void chequearColisiones() {
		for(Enemigo e : enemigos) {
			if(mario.getBounds().intersects(e.getBounds())) {
				detectarColisionEnemigo(e);
				break;
			}
		}
		for(Plataforma p : plataformas) {
			Plataforma pp = p; 
			if(!pp.tieneHitbox()) {
				if(mario.getBounds().intersects(p.getBounds())) {
					detectarColisionPlataforma(pp);
					break;
				}
			}
		}
		for(PowerUp pu : powerUps) {
			if(mario.getBounds().intersects(pu.getBounds())) {
				detectarColisionPowerUp(pu);
				break;
			}
		}
	}

	private void detectarColisionPlataforma(Plataforma p) {
		mario.accept(p.getVisitor());
	}

	private void detectarColisionEnemigo(Enemigo e) {
		mario.accept(e.getVisitor());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private void detectarColisionPowerUp(PowerUp p) {
		mario.accept(p.getVisitor());
	}
	
}