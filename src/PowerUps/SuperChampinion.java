package PowerUps;

import Estado.EstadoMario;
import Estado.SuperMario;
import Fabricas.Sprite;
import Juego.EntidadJugador;
import Sonidos.CrearSonido;
import Visitor.VisitorPowerUp;

public class SuperChampinion extends PowerUp {

	public SuperChampinion(Sprite s, int e, int i) {
		super(s, e, i);
		visitor = new VisitorPowerUp(this);
		Sonidos = new CrearSonido();
		sonido = Sonidos.crearSonido("powerup");
	}

	public EstadoMario getEstado(EntidadJugador m) {
		return new SuperMario(m);
	}
	
	public void darPuntaje(EntidadJugador m) {
		m.getEstado().puntajePowerUp(this);
	}
	
	public void destruir() {
		super.morir();
		sonido.reproducir();	
	}
	
}