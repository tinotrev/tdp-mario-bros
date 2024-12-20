package Visitor;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Proyectil.Proyectil;

public class VisitorProyectil  implements VisitorColisiones {
	
	@SuppressWarnings("unused")
	private Proyectil proyectil;
	
	public VisitorProyectil(Proyectil p) {
		proyectil = p;
	}
	
	public void visit(EntidadJugador m) {
		//
	}

	public void visit(Enemigo e) {
		//
	}

	public void visit(PowerUp pu) {
		//
	}

	public void visit(Plataforma plataforma) {
		//
	}

	public void visit(Proyectil proyectil) {
		//
	}

}