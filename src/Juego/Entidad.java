package Juego;

import java.awt.Rectangle;
import Fabricas.Sprite;
import Graficos.Observer;

public abstract class Entidad implements EntidadLogica, ElementoColisionable {

	protected Sprite sprite;
	public int x;
	protected int y;
	public Observer observer;

	public Entidad(Sprite s, int e, int i) {
		sprite = s;
		x = e;
		y = i;
	}

	public void registrarObserver(Observer o) {
		observer = o;
	}

	//---METODOS ENEMIGOS---//

	public void mover() {	
	}

	public void cambiarDireccion(){
	}

	//---METODOS ENEMIGOS---//

	public Sprite getSprite() {
		return sprite;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 32, 32);
	}

	public void morir() {
		observer.eliminar();
		this.observer = null;
	}

}