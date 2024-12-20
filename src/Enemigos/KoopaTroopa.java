package Enemigos;

import java.awt.Rectangle;
import java.util.Map;

import Fabricas.Sprite;
import Juego.EntidadJugador;
import Visitor.VisitorEnemigo;

public class KoopaTroopa extends Enemigo {

	protected int velocidad = 3;
	private EstadoKoopa estado;
	protected Map<Integer, Sprite> sprites;

	public KoopaTroopa(Map<Integer, Sprite> sp, int e, int i) {
		super(null, e, i);
		sprites = sp;
		visitor = new VisitorEnemigo(this);
		estado = new EstadoEstandar(this);
		sprite = sp.get(0);
	}

	public void mover() {
		estado.mover();
	}

	public int getPuntajeMata() {
		return Constantes.Constantes.KOOPA_TROOPA_MATA;
	}

	public int getPuntajeMuere() {
		return Constantes.Constantes.KOOPA_TROOPA_MUERE;
	}

	public EstadoKoopa getEstado() {
		return estado;
	}

	public void setEstado(EstadoKoopa estado) {
		this.estado = estado;
	}

	public void meSaltaron(EntidadJugador m) {
		estado.meSaltaron(m);
	}

	public void setSprite(Sprite s) {
		sprite = s;
		observer.actualizar();		
	}

	public Map<Integer, Sprite> getSprites() {
		return sprites;
	}

	public Rectangle getBounds() {
		return estado.getBounds();
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setEnSuelo(boolean b) {
		//
	}

	public void resetearVelocidadY() {
		//
	}
	
	public void meMataron(EntidadJugador m) {
		estado.meMataron(m);
	}
	
}
