package PowerUps;

import Estado.EstadoMario;
import Estado.Invencible;
import Fabricas.Sprite;
import Juego.EntidadJugador;
import Sonidos.CrearSonido;
import Visitor.VisitorPowerUp;

public class Estrella extends PowerUp {

	public Estrella(Sprite s, int e, int i) {
		super(s, e, i);
		visitor = new VisitorPowerUp(this);
		Sonidos = new CrearSonido();
		sonido = Sonidos.crearSonido("powerup");
	}

	public EstadoMario getEstado(EntidadJugador m) {
		//System.out.println("Creo un mario con estado anterior " + m.getEstado().getClass());
		return new Invencible(m, m.getEstado());
	}

	public void darPuntaje(EntidadJugador m) {
		m.getEstado().puntajePowerUp(this);
	}
	
	public void destruir() {
		super.morir();
		sonido.reproducir();	
	}

}