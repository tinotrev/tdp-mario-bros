package Fabricas;

import Enemigos.BuzzyBeetle;
import Enemigos.Enemigo;
import Enemigos.Goomba;
import Enemigos.KoopaTroopa;
import Enemigos.Lakitu;
import Enemigos.PiranhaPlant;
import Enemigos.Spiny;
import Juego.EntidadJugador;
import Juego.Juego;
import Juego.Mario;
import Plataformas.Bandera;
import Plataformas.BloquePregunta;
import Plataformas.Solido;
import Plataformas.Ladrillo;
import Plataformas.Plataforma;
import Plataformas.Tuberias;
import Plataformas.Vacio;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.PowerUp;
import PowerUps.SuperChampinion;
import Proyectil.BolaDeFuego;
import Proyectil.TiroLakitu;

public class JuegoFactory {

	protected FabricaSprites fabricaSprites;
	protected Juego juego;
	
	public JuegoFactory(FabricaSprites fabrica, Juego j) {
		fabricaSprites = fabrica;
		juego = j;
	}

	public Plataforma crearPlataforma(String tipo,int x,int y) {
		switch (tipo.toLowerCase()) {
		case "solido":
			return new Solido(fabricaSprites.getSolido(), x, y);
		case "pregunta":
			return new BloquePregunta(fabricaSprites.getPregunta(),x,y);
		case "ladrillo":
			return new Ladrillo(fabricaSprites.getLadrillo(), x, y);
		case "bandera":
			return new Bandera(fabricaSprites.getBandera(), x, y);
		case "vacio":
			return new Vacio(fabricaSprites.getVacio(), x, y);
		case "tuberia":
			return new Tuberias(fabricaSprites.getTuberia(), x, y);
		default:
			System.out.println("Tipo de entidad no reconocido (Dentro de la fabrica): " + tipo);
			return null; 
		}
	}

	public Enemigo crearEnemigo(String tipo,int x,int y) {
		switch (tipo.toLowerCase()) {
		case "buzzybeetle":
			return new BuzzyBeetle(fabricaSprites.getBuzzy(), x, y);
		case "spiny":
			return new Spiny(fabricaSprites.getSpiny(), x, y);
		case "goomba":
			return new Goomba(fabricaSprites.getGoomba(), x, y);
		case "lakitu":
			return new Lakitu(fabricaSprites.getLakitu(), x, y, juego); 
		case "koopatroopa":
			return new KoopaTroopa(fabricaSprites.getKoopa(), x, y);
		case "piranhaplant":
			return new PiranhaPlant(fabricaSprites.getPiranha(), x, y);
		default:
			System.out.println("Tipo de entidad no reconocido (Dentro de la fabrica): " + tipo);
			return null; 
		}
	} 

	public PowerUp crearPowerUp(String tipo,int x,int y) {
		switch (tipo.toLowerCase()) {
		case "estrella":
			return new Estrella(fabricaSprites.getEstrella(), x, y);
		case "campinionverde":
			return new ChampinionVerde(fabricaSprites.getChampinionVerde(), x, y);  
		case "superchampinion":
			return new SuperChampinion(fabricaSprites.getSuperChampinion(), x, y);  
		case "flordefuego":
			return new FlorDeFuego(fabricaSprites.getFlorDeFuego(), x, y);  
		case "moneda":
			return new Moneda(fabricaSprites.getMoneda(),x,y);  
		default:
			System.out.println("Tipo de entidad no reconocido (Dentro de la fabrica): " + tipo);
			return null;  
		}
	}

	public EntidadJugador crearMario(int x,int y) {
		EntidadJugador mario = new Mario(fabricaSprites.getMario(),x,y);
		return mario;
	}
	
	public BolaDeFuego crearBolaDeFuego(int x, int y, int direccion) {
		Sprite sprite = fabricaSprites.getBolaDeFuego();
        BolaDeFuego bola = new BolaDeFuego(sprite, x, y, direccion);
        return bola;
	}
	
	public TiroLakitu crearTiroLakitu(int x, int y, int direccion) {
		Sprite sprite = fabricaSprites.getBolaDeFuego();
        TiroLakitu tiro = new TiroLakitu(sprite, x, y, direccion,juego);
        return tiro;
	}
	
}
