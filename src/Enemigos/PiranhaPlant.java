package Enemigos;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorEnemigo;

public class PiranhaPlant extends Enemigo {

	private boolean subiendo = true;
	private int alturaMaxima = y-32;
	private int posTuberiaY = y+20;
	protected int velocidad = 1;

	public PiranhaPlant(Sprite s, int e, int i) {
		super(s, e, i);
		visitor = new VisitorEnemigo(this);
	}

	public void mover() {
	    // Si la piraña esta subiendo
		if(subiendo) {
	        y -= velocidad; // Subir la piraña
	        if (y <= alturaMaxima) { // Si alcanza la altura máxima
	            subiendo = false; // Cambiar a bajando
	        }
	    } 
	    // Si la piraña está bajando
	    else {
	        y += velocidad; // Bajar la piraña
	        if(y >= posTuberiaY) { // Si regresa a la tubería
	            subiendo = true; // Cambiar a subiendo para el siguiente ciclo
	        }
	    }
	    this.observer.actualizar();
	}

	public int getPuntajeMata() {
		return Constantes.Constantes.PIRANHA_PLANT_MATA;
	}

	public int getPuntajeMuere() {
		return Constantes.Constantes.PIRANHA_PLANT_MUERE;
	}

	public void meSaltaron(EntidadJugador m) {
		m.getEstado().recibirDanio();
	}

	public void setEnSuelo(boolean b) {
		//
	}

	public void resetearVelocidadY() {
		//
	}
	
	public void meMataron(EntidadJugador m) {
		m.actualizarPuntaje(getPuntajeMuere());
		morir();
	}
	
}