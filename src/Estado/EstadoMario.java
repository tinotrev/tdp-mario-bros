package Estado;

import java.awt.Rectangle;

import Enemigos.Enemigo;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.SuperChampinion;

public interface EstadoMario {
	public void recibirDanio();
	public void actualizarSprite();
	public int getKey();
	public Rectangle getBounds();
	public void puntajePowerUp(ChampinionVerde champinionVerde);
	public void puntajePowerUp(Estrella estrella);
	public void puntajePowerUp(FlorDeFuego florDeFuego);
	public void puntajePowerUp(SuperChampinion superChampinion);
	public boolean rompeBloques();
	public void accion();
	public boolean tocarEnemigo(Enemigo e);
}