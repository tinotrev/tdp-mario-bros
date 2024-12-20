package Juego;

import java.util.LinkedList;
import Enemigos.Enemigo;
import Plataformas.Bandera;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Proyectil.Proyectil;

public class Escenario {

	protected EntidadJugador mario;
	protected LinkedList<Enemigo> enemigos;
	protected LinkedList<PowerUp> powerUps;
	protected LinkedList<Plataforma> plataformas;
	protected LinkedList<Proyectil> proyectiles;
	protected int cantidadBolas;
	protected Bandera bandera;
	protected int nivel;
	protected Fondo fondo;
	protected LinkedList<Proyectil> proyectilesPendientesAgregar = new LinkedList<>();
	protected LinkedList<Entidad> entidadesPendientesEliminar = new LinkedList<>();

	public Escenario(Fondo f, int n) {
		fondo = f;
		nivel = n;
		enemigos = new LinkedList<Enemigo>();
		Enemigo.setLista(enemigos);
		powerUps =  new LinkedList<PowerUp>();
		PowerUp.setLista(powerUps);
		plataformas = new LinkedList<Plataforma>();
		Plataforma.setLista(plataformas);
		proyectiles = new LinkedList<Proyectil>();
		Proyectil.setLista(proyectiles);
		cantidadBolas = 0;
	}

	public void agregarMario(EntidadJugador j) {
		mario = j;
	}

	public LinkedList<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void agregarPlataformas(Plataforma p) {
		plataformas.addLast(p);
	}

	public void agregarEnemigo(Enemigo e) {
		enemigos.addLast(e);
	}

	public void agregarPowerUp(PowerUp p) {
		powerUps.addLast(p);
	}

	public LinkedList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public EntidadJugador getMario() {
		return mario;
	}

	public LinkedList<PowerUp> getPowerUps() {
		return powerUps;
	}

	public void eliminarEntidades() { 
		LinkedList<Entidad> entidadesParaEliminar = new LinkedList<>();
		synchronized (enemigos) {
			for (Entidad enemigo : enemigos) { 
				if (enemigo != null) { 
					entidadesParaEliminar.add(enemigo);
				}
			}	
		} 
		synchronized (powerUps) { 
			for (Entidad powerUp : powerUps) { 
				entidadesParaEliminar.add(powerUp);
				} 
		}
		synchronized (plataformas) {
			for (Entidad plataforma : plataformas) { 
				entidadesParaEliminar.add(plataforma); 
			}
		}
		synchronized (proyectiles) {
			for (Entidad proyectil : proyectiles) { 
				entidadesParaEliminar.add(proyectil); 
			} 
		}
		
		for (Entidad entidad : entidadesParaEliminar) { 
			entidad.morir(); 
		}
		
		enemigos.clear();
		powerUps.clear();
		plataformas.clear();
		proyectiles.clear();
		entidadesPendientesEliminar.clear(); 
		proyectilesPendientesAgregar.clear(); 
		mario = null; 
	}
		
	

	public void agregarBandera(Bandera b) {
		bandera = b;
	}

	public Bandera getBandera() {
		return bandera;
	}

	public void agregarBola(Proyectil proy) {
		proyectilesPendientesAgregar.addLast(proy);
		cantidadBolas++;
	}

	public void eliminarBola(Proyectil proy) {
		entidadesPendientesEliminar.add(proy);
		cantidadBolas--;
	}

	public  void agregarProyectil(Proyectil proy) {
		proyectilesPendientesAgregar.addLast(proy);
	}

	public void eliminarProyectil(Proyectil proy) {
		entidadesPendientesEliminar.add(proy);
	}

	public LinkedList<Proyectil> getProyectiles() {
		return proyectiles;
	}

	public int cantBolas() {
		return cantidadBolas;
	}

	public void marcarEntidadParaEliminar(Entidad entidad) {
		entidadesPendientesEliminar.add(entidad);
	}

	public void actualizarEntidades() {
		proyectiles.addAll(proyectilesPendientesAgregar);
		proyectilesPendientesAgregar.clear();
		proyectiles.removeAll(entidadesPendientesEliminar);
		entidadesPendientesEliminar.clear();
	}

}