package PowerUps;

import Estado.EstadoMario;
import Fabricas.Sprite;
import Juego.EntidadJugador;
import Sonidos.CrearSonido;
import Visitor.VisitorPowerUp;

public class ChampinionVerde extends PowerUp {

	public ChampinionVerde(Sprite s, int e, int i) {
		super(s, e, i);
		visitor = new VisitorPowerUp(this);
		Sonidos = new CrearSonido();
		sonido = Sonidos.crearSonido("1up");
	}

	public EstadoMario getEstado(EntidadJugador m) {
		return null;
	}

	public void darPuntaje(EntidadJugador m) {
		m.getEstado().puntajePowerUp(this);
		m.setVidas(m.getVidas() + 1);
	}

	public void destruir() {
		super.morir();
		sonido.reproducir();
	}

}