package Hilos;

import Graficos.PantallaHud;
import Juego.EntidadJugador;

public class HiloMario extends Thread {

	private EntidadJugador mario;
	private boolean running;

	public void setRunning(boolean b) {
		running = b;
	}

	public HiloMario(EntidadJugador e) {
		mario = e;
		running = true;
	}

	public void run() {
		while(running) {
			//Primero, mover a Mario 
			mario.mover();
			mario.comprobarColisiones();
			
			if(mario.getY() + mario.getBounds().height >= 566) {
				mario.setY(563);
			}
			PantallaHud.actualizarLabelsInformacion(mario);
			// Pausar el hilo para controlar la frecuencia de actualización
			try {
				Thread.sleep(19);  // Ajusta el tiempo según sea necesario
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}