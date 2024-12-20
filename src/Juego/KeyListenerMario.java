package Juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Sonidos.CrearSonido;
import Sonidos.GestorSonido;

public class KeyListenerMario implements KeyListener {
	
	private EntidadJugador jugador;
	private CrearSonido Sonidos = new CrearSonido();
	private GestorSonido saltoSonido = Sonidos.crearSonido("salto");

	public KeyListenerMario(EntidadJugador mario){
		this.jugador = mario;
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			jugador.setIzquierda(true);  // Cambiar estado a izquierda
			break;
		case KeyEvent.VK_D:
			jugador.setDerecha(true);    // Cambiar estado a derecha
			break;
		case KeyEvent.VK_W:
			saltoSonido.reproducir();
			jugador.setArriba(true);
			break;
		case KeyEvent.VK_SPACE: // Lanzar bola de fuego
			jugador.accion();
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			jugador.setIzquierda(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			jugador.setDerecha(false);
		}
	}

	public void keyTyped(KeyEvent e) {
		//
	}

}
