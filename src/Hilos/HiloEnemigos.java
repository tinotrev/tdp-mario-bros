package Hilos;

import java.util.LinkedList;

import Enemigos.Enemigo;
import Juego.Entidad;
import Juego.Escenario;
import Plataformas.Plataforma;
import Proyectil.Proyectil;

public class HiloEnemigos extends Thread {

	private LinkedList<Enemigo> enemigos;
	private LinkedList<Proyectil> proyectiles;
	private Escenario escenario;
	private boolean running;

	public void setRunning(boolean b) {
		running = b;
	}

	public HiloEnemigos(Escenario e) {
		enemigos = e.getEnemigos();
		proyectiles= e.getProyectiles();
		escenario=e;
		running = true;
	}

	public void run() {
		while(running) {
			synchronized (enemigos) {
				for(Entidad enemigo : enemigos) {
					if(running) {
						enemigo.mover();
						chequearColisiones(enemigo);
					}
				}
			}
		synchronized (proyectiles) {
			for(Entidad proyectil : proyectiles) {
				if(running && proyectil!=null) {
					proyectil.mover();
					chequearColisiones(proyectil);
				}
			}
		}
		escenario.actualizarEntidades();
		try {
			Thread.sleep(75);
			
			} catch (InterruptedException e) {
				//e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}	
	}

	public void chequearColisiones(Entidad e) {
		//Obtener el nivel actual para acceder a los enemigos, power-ups, plataformas, etc.
		LinkedList<Enemigo> enemigos = escenario.getEnemigos();
		LinkedList<Plataforma> plataformas = escenario.getPlataformas();
		//Comprobar colisiones con enemigos
		for(Enemigo enemigo : enemigos) {
			if(!e.equals(enemigo)) {
				if(e.getBounds().intersects(enemigo.getBounds())) {
					e.accept(enemigo.getVisitor());
					break;
				}
			}
		}

		// Comprobar colisiones con plataformas
		for(Plataforma plataforma : plataformas) {
			if(e.getBounds().intersects(plataforma.getBounds())) {
				e.accept(plataforma.getVisitor());
				break;
			}
		}
	}

}