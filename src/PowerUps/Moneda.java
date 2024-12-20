package PowerUps;

import Estado.EstadoMario;
import Fabricas.Sprite;
import Juego.EntidadJugador;
import Sonidos.CrearSonido;
import Visitor.VisitorPowerUp;

public class Moneda extends PowerUp {
	
	public Moneda(Sprite string, int e, int i) {
		super(string, e, i);
		visitor = new VisitorPowerUp(this);
		Sonidos = new CrearSonido();
		sonido = Sonidos.crearSonido("moneda");
		activado = true;
	}

	public EstadoMario getEstado(EntidadJugador m) {
		return null;
	}

	public void darPuntaje(EntidadJugador m) {
		m.actualizarPuntaje(5);
	}
	
	public void destruir() {
		super.morir();
		sonido.reproducir();	
	}
	
	public void setActivado(boolean b) {
		//
	}
	
	public boolean esMoneda() {
		return true;
	}
	
}