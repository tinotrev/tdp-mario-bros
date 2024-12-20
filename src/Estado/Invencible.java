package Estado;

import java.awt.Rectangle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.SuperChampinion;

public class Invencible implements EstadoMario {

	private EntidadJugador mario;
	private EstadoMario estadoAnterior;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public Invencible(EntidadJugador m, EstadoMario ea) {
		mario = m;
		estadoAnterior = ea;
		mario.setY(mario.getY()-5);
		iniciarTemporizador();
	}

	public boolean tocarEnemigo(Enemigo e) {
		e.morir();
		return true;
	}

	public void recibirDanio() {
		//
	}

	public void actualizarSprite() {
		//
	}

	public int getKey() {
		return 3;
	}

	public Rectangle getBounds() {
		return new Rectangle(mario.getX(), mario.getY(), 32, 32);
	}

	public void puntajePowerUp(ChampinionVerde champinionVerde) {
		mario.actualizarPuntaje(Constantes.Constantes.CHAMPINION_VERDE);
	}

	public void puntajePowerUp(Estrella estrella) {
		mario.actualizarPuntaje(Constantes.Constantes.ESTRELLA_INVENCIBLE);
	}

	public void puntajePowerUp(FlorDeFuego florDeFuego) {
		// 0
	}

	public void puntajePowerUp(SuperChampinion superChampinion) {
		// 0
	}

	private void iniciarTemporizador() {
		scheduler.schedule(new Runnable() {
			public void run() {
				retornarEstado();
			}
		}, 5, TimeUnit.SECONDS);
	}

	private void retornarEstado() {
		if(estadoAnterior.getKey() == 1 || estadoAnterior.getKey() == 2) {
			mario.setY(mario.getY() - 32);
		}
		mario.setEstado(estadoAnterior);
	}

	public boolean rompeBloques() {
		return false;
	}

	public void accion() {
		//
	}

}