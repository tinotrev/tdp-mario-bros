package Visitor;

import Enemigos.Enemigo;
import Juego.EntidadJugador;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Proyectil.Proyectil;

public interface VisitorColisiones {
	void visit(EntidadJugador m);
	void visit(Enemigo e);
	void visit(PowerUp pu);
	void visit(Plataforma plataforma);
	void visit(Proyectil proyectil);
}