package Launcher;

import java.awt.EventQueue;

import Graficos.ControladorGraficos;
import Juego.Juego;

public class Launcher {
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego juego = new Juego();
					ControladorGraficos controladorVistas = new ControladorGraficos(juego);
					juego.setControladorGraficos(controladorVistas);
					controladorVistas.mostrarPantallaUsuario();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}