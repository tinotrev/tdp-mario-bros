package Estado;

import java.awt.Rectangle;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.SuperChampinion;

public class SuperMario implements EstadoMario {

	private EntidadJugador mario;
	
	public SuperMario(EntidadJugador m) {
		mario = m;
		mario.setY(mario.getY() - 5);
	}
	
	public void recibirDanio() {
		mario.setEstado(new Normal(mario));
	}

	public void actualizarSprite() {
		//
	}
	
	public Rectangle getBounds() {
		return new Rectangle(mario.getX(), mario.getY(), 32, 64);
	}

	public int getKey() {
		return 1;
	}

	public void puntajePowerUp(ChampinionVerde champinionVerde) {
		mario.actualizarPuntaje(Constantes.Constantes.CHAMPINION_VERDE);
	}

	public void puntajePowerUp(Estrella estrella) {
		mario.actualizarPuntaje(Constantes.Constantes.ESTRELLA_SUPERMARIO);
	}

	public void puntajePowerUp(FlorDeFuego florDeFuego) {
		mario.actualizarPuntaje(Constantes.Constantes.FLORFUEGO_SUPERMARIO);
	}

	public void puntajePowerUp(SuperChampinion superChampinion) {
		mario.actualizarPuntaje(Constantes.Constantes.SUPERCHAMPINION_SUPERMARIO);
	}

	public boolean rompeBloques() {
		return true;
	}

	public void accion() {
		//
	}

	public boolean tocarEnemigo(Enemigo e) {
		return false;
	}

}