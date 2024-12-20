package Estado;

import java.awt.Rectangle;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.SuperChampinion;

public class MarioFuego implements EstadoMario {

	private EntidadJugador mario;

	public MarioFuego(EntidadJugador m) {
		mario = m;
		mario.setY(mario.getY()-5);
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
		return 2;
	}

	public void puntajePowerUp(ChampinionVerde champinionVerde) {
		mario.actualizarPuntaje(Constantes.Constantes.CHAMPINION_VERDE);
	}

	public void puntajePowerUp(Estrella estrella) {
		// 0
	}

	public void puntajePowerUp(FlorDeFuego florDeFuego) {
		mario.actualizarPuntaje(Constantes.Constantes.FLORFUEGO_FUEGO);
	}

	public void puntajePowerUp(SuperChampinion superChampinion) {
		// 0
	}

	public boolean rompeBloques() {
		return true;
	}

	public void accion() {
		if(mario.getJuego().getEscenario().cantBolas()<2) {
			int direccion = mario.getUltimaDireccion() == 'i' ? -1 : 1;
			mario.setSpriteActual(direccion == -1 ? 7 : 6);
			mario.getJuego().agregarBolaDeFuego(mario.getX(), mario.getY(), direccion);
		}
	}

	public boolean tocarEnemigo(Enemigo e) {
		return false;
	}

}