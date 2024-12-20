package Juego;

import java.io.IOException;
import java.util.LinkedList;

import Enemigos.Enemigo;
import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesAlternativos;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.JuegoFactory;
import Graficos.ControladorGraficos;
import Graficos.Observer;
import Graficos.ObserverJugador;
import Graficos.PantallaUsuario;
import Hilos.HiloColisiones;
import Hilos.HiloEnemigos;
import Hilos.HiloMario;
import Parser.NivelParser;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Proyectil.BolaDeFuego;
import Proyectil.Proyectil;
import Proyectil.TiroLakitu;
import Ranking.Ranking;
import Sonidos.*;

public class Juego {
	protected Escenario nivelActual;
	protected int puntosTotales;
	protected ControladorGraficos controladorGraficos;
	protected NivelParser parser;
	protected JuegoFactory fabrica;
	protected FabricaSprites fabricaSprites;
	protected Ranking ranking;
	public static int numNivel;

	//HILOS
	protected HiloMario hiloMario;
	protected HiloEnemigos hiloEnemigos;
	protected HiloColisiones hiloColisiones;

	//SONIDOS
	protected CrearSonido Sonidos;
	protected GestorSonido musica;

	public Juego() {
		fabricaSprites = new FabricaSpritesOriginales();
		fabrica = new JuegoFactory(fabricaSprites, this);
		parser = new NivelParser(fabrica);
		ranking = new Ranking();
		numNivel = 1;
		puntosTotales = 0;
	}

	public void cargarNivel() {		
		if(nivelActual != null) {
			nivelActual.eliminarEntidades();
			nivelActual = null;
		}
		try {
			switch (numNivel) {
			case 1:
				nivelActual = parser.cargarArchivoNivel("src/Niveles/nivel1.txt", 1, new Fondo(null));
				break;
			case 2:
				nivelActual = parser.cargarArchivoNivel("src/Niveles/nivel2.txt", 2, new Fondo(null));
				break;
			case 3:
				nivelActual = parser.cargarArchivoNivel("src/Niveles/nivel3.txt", 3, new Fondo(null));
				break;
			default:
				ranking.agregarJugador(PantallaUsuario.usuario, puntosTotales);
				terminarSonido();
				if(nivelActual != null) {
					nivelActual.eliminarEntidades();
					nivelActual = null;
				}
				puntosTotales = 0;
				terminarHilos();
				controladorGraficos.reiniciarPantallas();
				controladorGraficos.mostrarPantallaRanking();
				break;
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (numNivel <= 3) {
			cargar();
			empezarSonido();
		}
	}

	private void crearHilos() {
		//Iniciar hilo
		hiloColisiones = new HiloColisiones(nivelActual);
		hiloColisiones.start();
		//Iniciar el nuevo hilo juego
		hiloMario = new HiloMario(nivelActual.getMario());
		hiloMario.start();
		//Hilo separado para enemigos 
		hiloEnemigos = new HiloEnemigos(nivelActual);
		hiloEnemigos.start();
	}

	private void cargar() {
		nivelActual.getBandera().setJuego(this);
		//asignarPowerUps();
		registrarObservers();
		registrarListenerJugador(nivelActual.getMario());
		chequearPowerUps();
		nivelActual.getMario().setPlataformas(nivelActual.getPlataformas());
		nivelActual.getMario().setJuego(this);
		crearHilos();
	}
	
	public void empezarSonido() {
		Sonidos = new CrearSonido();
		musica = Sonidos.crearSonido("musica");
		musica.reproducir();
	}

	protected void registrarObservers() {
		registrarObserverJugador(nivelActual.getMario());
		registrarObserverPlataforma(nivelActual.getPlataformas());
		registrarObserverEnemigo(nivelActual.getEnemigos());
		registrarObserverPowerUp(nivelActual.getPowerUps());
		registrarObserverProyectil(nivelActual.getProyectiles());
	}

	protected void registrarObserverJugador(EntidadJugador m) {
		Observer observer = controladorGraficos.registrarJugador(m);
		m.registrarObserver(observer);
		ObserverJugador camara = new ObserverJugador(m,controladorGraficos);
		m.registrarObserverCamara(camara);
	}

	public void registrarObserverPlataforma(LinkedList<Plataforma> plataformas){
		for(Plataforma p : plataformas) {
			Observer observer = controladorGraficos.registrarEntidad(p);
			p.registrarObserver(observer);
		}
	}
	
	public void registrarObserverEnemigo(LinkedList<Enemigo> enemigos){
		for(Enemigo e : enemigos) {
			Observer observer = controladorGraficos.registrarEntidad(e);
			e.registrarObserver(observer);
		}
	}
	
	public void registrarObserverPowerUp(LinkedList<PowerUp> powerUp){
		for(PowerUp p : powerUp) {
			Observer observer = controladorGraficos.registrarEntidad(p);
			p.registrarObserver(observer);
		}
	}
	
	public void registrarObserverProyectil(LinkedList<Proyectil> proyectil){
		for(Proyectil p : proyectil) {
			Observer observer = controladorGraficos.registrarEntidad(p);
			p.registrarObserver(observer);
		}
	}

	protected void registrarListenerJugador(EntidadJugador jugador) {
		KeyListenerMario oyente = new KeyListenerMario(jugador);
		controladorGraficos.registrarOyenteVentana(oyente);
	}

	public void chequearPowerUps() {
		for(PowerUp e : nivelActual.getPowerUps()) {
			e.chequearSprite();
		}
	}

	public void setControladorGraficos(ControladorGraficos cg) {
		controladorGraficos = cg;
	}

	private void terminarSonido() {
		if(musica != null) {
			musica.detener();
		}
	}

	public void terminarNivel() {
		controladorGraficos.mostrarPantallaGameOver();
		terminarSonido();
		nivelActual.eliminarEntidades();
		nivelActual = null;
		terminarHilos();
		numNivel = 1;
	}

	public void cambiarNivel() {
		if(numNivel <= 3) {
			puntosTotales = puntosTotales + nivelActual.getMario().getPuntaje();
			terminarSonido();
			terminarHilos();
			nivelActual.eliminarEntidades();
			nivelActual = null;
			controladorGraficos.reiniciarPantallas();
			controladorGraficos.mostrarPantallaCambioNivel();
			numNivel++;
		}
	}

	private void terminarHilos() {
		hiloColisiones.setRunning(false);
		if (hiloEnemigos != null) { 
			hiloEnemigos.setRunning(false); 
			hiloEnemigos = null; 
		}
		hiloMario.setRunning(false);
	}

	public Escenario getEscenario() {
		return nivelActual;
	}

	public int getNumeroNivel() {
		return numNivel;
	}

	public void cargarOriginal() {
		fabricaSprites = new FabricaSpritesOriginales();
		fabrica = new JuegoFactory(fabricaSprites,this);
		parser = new NivelParser(fabrica);
		cargarNivel();
	}

	public void cargarAlternativo() {
		fabricaSprites = new FabricaSpritesAlternativos();
		fabrica = new JuegoFactory(fabricaSprites,this);
		parser = new NivelParser(fabrica);
		cargarNivel();
	}

	public void agregarProyectil(int x, int y, int velocidadX) {
		int direccion = (velocidadX >= 0) ? 1 : -1;
		TiroLakitu tiro = fabrica.crearTiroLakitu(x, y, direccion);
		Observer observer = controladorGraficos.registrarEntidad(tiro);
		tiro.registrarObserver(observer);
		nivelActual.agregarProyectil(tiro);
	}

	public void agregarBolaDeFuego(int x, int y, int velocidadX) {
		int direccion = (velocidadX >= 0) ? 1 : -1;
		BolaDeFuego bola = fabrica.crearBolaDeFuego(x, y, direccion);
		Observer observer = controladorGraficos.registrarEntidad(bola);
		bola.registrarObserver(observer);
		bola.setMario(nivelActual.getMario());
		nivelActual.agregarBola(bola);
	}

	public void agregarSpiny(int x, int y, int direccion) {
		Enemigo spi = fabrica.crearEnemigo("Spiny",x, y);
		Observer observer = controladorGraficos.registrarEntidad(spi);
		spi.registrarObserver(observer);
		nivelActual.agregarEnemigo(spi);
	}

	public FabricaSprites getFabricaSprite() {
		return fabricaSprites;
	}
	
	public void setPuntosTotales(int p) {
		puntosTotales = p;
	}

}