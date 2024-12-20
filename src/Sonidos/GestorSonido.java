package Sonidos;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GestorSonido {
	
	private Clip clip;
	private AudioInputStream audioStream;

	//Constructor: cargar el archivo de sonido
	public GestorSonido(String rutaSonido) {
		try {
			File archivoSonido = new File(rutaSonido);
			audioStream = AudioSystem.getAudioInputStream(archivoSonido);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	//Método para reproducir el sonido 
	public void reproducir() {
		clip.setMicrosecondPosition(0);
		clip.start();
	}

	//Método para pausar el sonido
	public void pausar() {
		if(clip.isRunning()) {
			clip.stop(); // Pausar el clip
		}
	}

	//Método para detener el sonido (se reinicia al principio)
	public void detener() {
		clip.stop(); //Detener la reproducción
		clip.setMicrosecondPosition(0); //Reiniciar la posición al principio
	}

	//Método para repetir el sonido en bucle
	public void repetir(int veces) {
		clip.setMicrosecondPosition(0); //Reiniciar la posición al principio
		clip.loop(veces); //Repetir el sonido el número de veces indicado
	}

	//Método para cerrar el clip y liberar recursos
	public void cerrar() {
		if(clip != null) {
			clip.close();
		}
		try {
			if(audioStream != null) {
				audioStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}