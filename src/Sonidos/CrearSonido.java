package Sonidos;

import java.util.HashMap;
import java.util.Map;

public class CrearSonido {

	//Mapa que asocia el tipo de sonido con su archivo correspondiente
	private Map<String, String> mapaSonidos = new HashMap<>();

	//Inicializar el mapa con los sonidos disponibles   
	public CrearSonido() {
		mapaSonidos.put("salto", "src/Sonidos/jump-small.wav");
		mapaSonidos.put("musica", "src/Sonidos/mario-bros-music.wav");
		mapaSonidos.put("powerup", "src/Sonidos/powerup.wav");
		mapaSonidos.put("moneda", "src/Sonidos/coin.wav");
		mapaSonidos.put("1up", "src/Sonidos/1-up.wav");
		mapaSonidos.put("powerup", "src/Sonidos/powerup.wav");
		mapaSonidos.put("bloque", "src/Sonidos/break-block.wav");
	}

	//Método para crear y devolver un ReproductorSonido según el tipo de sonido
	public GestorSonido crearSonido(String tipo) {
		String rutaSonido = mapaSonidos.get(tipo); 
		if(rutaSonido != null) {
			return new GestorSonido(rutaSonido);
		} else {
			System.out.println("Sonido no encontrado: " + tipo);
			return null; 
		}
	}
	
}