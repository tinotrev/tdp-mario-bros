package Ranking;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {
	
	private final String archivoRanking;
	private List<EntidadRankeable> jugadores;

	public Ranking() {
		this.archivoRanking = "src/Ranking/ranking.txt";
		this.jugadores = new ArrayList<>();
		cargarRanking();
	}

	public void cargarRanking() {
		jugadores.clear(); 
		try(BufferedReader br = new BufferedReader(new FileReader(archivoRanking))) {
			String linea;
			while((linea = br.readLine()) != null) {
				String[] partes = linea.split(" - ");
				if(partes.length == 2) {
					String nombre = partes[0];
					int puntaje = Integer.parseInt(partes[1]);
					jugadores.add(new EntidadRankeable(nombre, puntaje));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(jugadores, (a, b) -> Integer.compare(b.getPuntaje(), a.getPuntaje())); //Ordenar de mayor a menor
	}

	public void agregarJugador(String nombre, int puntaje) {
		jugadores.add(new EntidadRankeable(nombre, puntaje));
		Collections.sort(jugadores, (a, b) -> Integer.compare(b.getPuntaje(), a.getPuntaje())); //Ordenar
		if(jugadores.size() > 5) {
			jugadores.remove(jugadores.size() - 1);
		}
		guardarRanking();
	}

	public void guardarRanking() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoRanking))) {
			for(EntidadRankeable jugador : jugadores) {
				bw.write(jugador.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<EntidadRankeable> obtenerMejoresEntidades(int limite) {
		return jugadores.subList(0, Math.min(limite, jugadores.size()));
	}

}