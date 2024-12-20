package Visitor;

import java.awt.Rectangle;
import Enemigos.Enemigo;
import Estado.EstadoMario;
import Juego.EntidadJugador;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Proyectil.Proyectil;

public class VisitorPowerUp implements VisitorColisiones{

	protected PowerUp powerUp;

	public VisitorPowerUp(PowerUp p) {
		powerUp = p;
	}

	public void visit(EntidadJugador m) {
		if(powerUp.getActivado()) {
			EstadoMario estado = powerUp.getEstado(m);
			powerUp.darPuntaje(m);
			powerUp.morir();
			if(estado != null && m.getEstado().getKey()!=3) { //es un PowerUP de estado
				Rectangle hitBoxMarioAntiguo = m.getBounds();
				m.setEstado(estado);
				if(!hitBoxMarioAntiguo.equals(m.getBounds())) {
					m.setY(m.getY() - m.getBounds().height);
				}
			}
		}
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