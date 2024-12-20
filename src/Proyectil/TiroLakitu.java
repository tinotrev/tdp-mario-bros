package Proyectil;

import Fabricas.Sprite;
import Juego.Juego;
import Visitor.VisitorProyectil;

public class TiroLakitu extends Proyectil {

	private int velocidadY = 0;

	public TiroLakitu(Sprite s, int e, int i, int d, Juego j) {
		super(s, e, i, d);
		sprite = s;
		visitor = new VisitorProyectil(this);
		this.velocidad = 10 * direccion;
		juego = j;
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

	public void morir() {
		juego.getEscenario().eliminarProyectil(this);
		if(observer != null) {
			observer.eliminar();
		}
		this.observer = null;
		juego.agregarSpiny(this.x,this.y-16, direccion);
	}

	public boolean aliado() {
		return false;
	}
	
}