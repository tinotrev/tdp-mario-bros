package Enemigos;

import java.awt.Rectangle;

import Juego.EntidadJugador;

public interface EstadoKoopa {
	public void meSaltaron(EntidadJugador m);
	public int getKey();
	public void mover();
	public Rectangle getBounds();
	public void meMataron(EntidadJugador m);
}