package Visitor;

import java.awt.Rectangle;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Proyectil.Proyectil;
import Sonidos.*;

public class VisitorPlataforma implements VisitorColisiones{

	protected Plataforma plataforma;

	//SONIDOS
	CrearSonido Sonidos;
	GestorSonido sonido;

	public VisitorPlataforma(Plataforma p) {
		plataforma = p;
		Sonidos = new CrearSonido();
		sonido = Sonidos.crearSonido("bloque");
	}

	public void visit(EntidadJugador m) {
		//Detecta si Mario golpea desde abajo y está en SuperMario o MarioFuego
		if(m.getEstado().rompeBloques() && plataforma.rompible()) {
			plataforma.morir();
			sonido.reproducir();
		}
		plataforma.interactuar(m);
	}

	public void visit(Enemigo e) {
		Rectangle enemigoBounds = e.getBounds();
		Rectangle plataformaBounds = plataforma.getBounds();

		if(enemigoBounds.intersects(plataformaBounds)) {
			int difDerecha = Math.abs(enemigoBounds.x + enemigoBounds.width - plataformaBounds.x);
			int difIzquierda = Math.abs(plataformaBounds.x + plataformaBounds.width - enemigoBounds.x);
			int difAbajo = Math.abs(enemigoBounds.y + enemigoBounds.height - plataformaBounds.y);
			int difArriba = Math.abs(plataformaBounds.y + plataformaBounds.height - enemigoBounds.y);

			if (e.tieneGravedad()) {
				//Manejo de colisiones para enemigos con gravedad
				if(difAbajo < difDerecha && difAbajo < difIzquierda && difAbajo < difArriba) {
					// Colisión con el suelo desde arriba
					e.setEnSuelo(true);
					e.resetearVelocidadY();
					e.setPosicionY(plataformaBounds.y - enemigoBounds.height);
				} else if(difArriba < difDerecha && difArriba < difIzquierda) {
					//Colisión con el techo
					e.setPosicionY(plataformaBounds.y + plataformaBounds.height);
					e.resetearVelocidadY();
				} else if(difDerecha < difIzquierda) {
					//Colisión lateral derecha
					e.setPosicionX(plataformaBounds.x - enemigoBounds.width);
					e.cambiarDireccion();
				} else {
					//Colisión lateral izquierda
					e.setPosicionX(plataformaBounds.x + plataformaBounds.width);
					e.cambiarDireccion();
				}
			} else {
				//Manejo de colisiones para enemigos sin gravedad, solo colisiones laterales
				if(difDerecha < difIzquierda) {
					e.cambiarDireccion();
				} else {
					e.cambiarDireccion();
				}
			}
		}
	}

	public void visit(PowerUp pu) {
		//
	}

	public void visit(Plataforma plataforma) {
		//
	}

	public void visit(Proyectil proyectil) {
		if(plataforma.rompible() && proyectil.aliado()) {
			plataforma.morir();
		}
		proyectil.morir();
	}

}