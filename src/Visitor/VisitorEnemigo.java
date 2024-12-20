package Visitor;

import java.awt.Rectangle;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Proyectil.Proyectil;

public class VisitorEnemigo implements VisitorColisiones {

	protected Enemigo enemigo;

	public VisitorEnemigo(Enemigo e) {
		enemigo = e;
	}

	public void visit(Enemigo e) {
		e.cambiarDireccion();
	}

	public void visit(EntidadJugador m) {
		Rectangle hitboxMario = m.getBounds();
		Rectangle hitboxEnemigo = enemigo.getBounds();
		boolean loMate = enemigo.meTocaron(m); //si mato al enemigo devuelve true, caso contrario false y se analiza que pasa si lo salto
		//Colisión por arriba
		if(hitboxMario.y + hitboxMario.height - 25 < hitboxEnemigo.y && !loMate) {
			enemigo.meSaltaron(m);  // El enemigo es derrotado al ser pisado
		}
		else {
			m.getEstado().recibirDanio();
		}
	}

	public void visit(PowerUp pu) {
		//
	}

	public void visit(Plataforma plataforma) {
		//
	}

	public void visit(Proyectil proyectil) {
		if(proyectil.aliado()==true) {
			EntidadJugador mario = proyectil.getJuego().getEscenario().getMario();
			enemigo.meMataron(mario);
			proyectil.morir();
		}
	}
	
}