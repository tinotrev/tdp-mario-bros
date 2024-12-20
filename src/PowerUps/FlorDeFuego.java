package PowerUps;

import Estado.EstadoMario;
import Estado.MarioFuego;
import Fabricas.Sprite;
import Juego.EntidadJugador;
import Sonidos.CrearSonido;
import Visitor.VisitorPowerUp;

public class FlorDeFuego extends PowerUp {

	public FlorDeFuego(Sprite s, int e, int i) {
		super(s, e, i);
		visitor = new VisitorPowerUp(this);
		Sonidos = new CrearSonido();
		sonido = Sonidos.crearSonido("powerup");
	}

	public EstadoMario getEstado(EntidadJugador m) {
		return new MarioFuego(m);
	}
	
	public void darPuntaje(EntidadJugador m) {
		m.getEstado().puntajePowerUp(this);
	}
	
	public void destruir() {
		super.morir();
		sonido.reproducir();	
	}
	
}