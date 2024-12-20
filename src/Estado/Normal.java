package Estado;

import java.awt.Rectangle;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.SuperChampinion;

public class Normal implements EstadoMario {
	
	private EntidadJugador mario;
	
	public Normal(EntidadJugador m) {
		mario = m;
		mario.setY(mario.getY() - 5);
	}
	
	public void recibirDanio() {
		mario.perderVida();
	}

	public void actualizarSprite() {
		//
	}

	public int getKey() {
		return 0;
	}

	public Rectangle getBounds() {
		return new Rectangle(mario.getX(), mario.getY(), 32, 32);
	}

	public void puntajePowerUp(ChampinionVerde champinionVerde) {
		mario.actualizarPuntaje(Constantes.Constantes.CHAMPINION_VERDE);
	}

	public void puntajePowerUp(Estrella estrella) {
		mario.actualizarPuntaje(Constantes.Constantes.ESTRELLA_NORMAL);
	}

	public void puntajePowerUp(FlorDeFuego florDeFuego) {
		mario.actualizarPuntaje(Constantes.Constantes.FLORFUEGO_NORMAL);
	}

	public void puntajePowerUp(SuperChampinion superChampinion) {
		mario.actualizarPuntaje(Constantes.Constantes.SUPERCHAMPINION_NORMAL);
	}

	public boolean rompeBloques() {
		return false;
	}

	public void accion() {
		//
	}

	public boolean tocarEnemigo(Enemigo e) {
		return false;
	}

}