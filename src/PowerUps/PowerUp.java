package PowerUps;

import java.awt.Rectangle;
import java.util.LinkedList;

import Estado.EstadoMario;
import Fabricas.Sprite;
import Juego.ElementoColisionable;
import Juego.Entidad;
import Juego.EntidadJugador;
import Sonidos.CrearSonido;
import Sonidos.GestorSonido;
import Visitor.VisitorColisiones;
import Visitor.VisitorPowerUp;

public abstract class PowerUp extends Entidad implements ElementoColisionable  {

	protected VisitorPowerUp visitor;
	static protected LinkedList<PowerUp> lista;
	protected boolean activado;
	protected Sprite spriteActivo;

	//SONIDOS
	protected CrearSonido Sonidos;
	protected GestorSonido sonido;

	public PowerUp(Sprite s, int e, int i) {
		super(s, e, i);
		activado = false;
		spriteActivo = s;
	}

	public void accept(VisitorColisiones v) {
		v.visit(this);
	}

	public static void setLista(LinkedList<PowerUp> l) {
		lista = l;
	}

	public static LinkedList<PowerUp> getLista() {
		return lista;
	}

	public Rectangle getBounds() {
		//Se asume que el ancho y alto del PowerUp es 32x32 esto siempre es asi
		return new Rectangle(getX(), getY(), 32, 32);
	}

	public VisitorColisiones getVisitor() {
		return visitor;
	}

	public abstract EstadoMario getEstado(EntidadJugador m);

	public void morir() {
		PowerUp.getLista().remove(this);
		observer.eliminar();
		this.observer = null;
	}

	public abstract void darPuntaje(EntidadJugador m);

	public boolean getActivado() {
		return activado;
	}
	
	public void setActivado(boolean b) {
		activado = b;
		chequearSprite();

	}
	
	public void chequearSprite() {
		if(!activado) {
			observer.setInvisible(true);
		}
		else {
			observer.setInvisible(false);
		}
	}

	public boolean esMoneda() {
		return false;
	}
	
}