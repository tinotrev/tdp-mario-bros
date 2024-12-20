package Proyectil;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorProyectil;

public class BolaDeFuego extends Proyectil{

	private int velocidadY = 0;
	private EntidadJugador mario;
	
	public BolaDeFuego(Sprite s, int e, int i, int d) {
		super(s, e, i, d);
		sprite = s;
		visitor = new VisitorProyectil(this);
		this.velocidad = 10 * direccion;
	}

	public void mover() {
		//Movimiento horizontal
		x += velocidad;
		//Movimiento en parábola (aplicando gravedad en Y)
		velocidadY += 1; // La gravedad aumenta la velocidad en Y
		y += velocidadY;
		
		//Actualizar el observer
		if(observer != null) {
			observer.actualizar();
		}
	}

	public void setMario(EntidadJugador m) {
		mario = m;
		juego = mario.getJuego();
	}

	public void morir() {
		mario.getJuego().getEscenario().eliminarBola(this);
		if(observer != null) {
			observer.eliminar();
		}
		this.observer = null;
	}

	public boolean aliado() {
		return true;
	}
	
}