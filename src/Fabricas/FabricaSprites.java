package Fabricas;

import java.util.HashMap;
import java.util.Map;

public abstract class FabricaSprites {

	protected String rutaCarpeta;

	public FabricaSprites(String ruta_a_carpeta) {
		rutaCarpeta = ruta_a_carpeta;
	}

	public Map<Integer,Map<Integer,Sprite>> getMario() {

		Map<Integer,Map<Integer,Sprite>> sprites = new HashMap<Integer,Map<Integer,Sprite>>();

		//mapeo sprites normal
		Map<Integer, Sprite> spritesNormal = new HashMap<Integer, Sprite>();
		spritesNormal.put(0,new Sprite(rutaCarpeta + "Mario_Sprites/mario_parado.png"));
		spritesNormal.put(1,new Sprite(rutaCarpeta + "Mario_Sprites/mario_caminando_derecha.gif"));
		spritesNormal.put(2,new Sprite(rutaCarpeta + "Mario_Sprites/mario_caminando_izquierda.gif"));
		spritesNormal.put(3,new Sprite(rutaCarpeta + "Mario_Sprites/mario_saltando_derecha.png"));
		spritesNormal.put(4,new Sprite(rutaCarpeta + "Mario_Sprites/mario_saltando_izquierda.png"));
		spritesNormal.put(5,new Sprite(rutaCarpeta + "Mario_Sprites/mario_parado_izquierda.png"));

		sprites.put(0,spritesNormal);

		//mapeo sprites SuperMario
		Map<Integer, Sprite> spritesSuperMario = new HashMap<Integer, Sprite>();
		spritesSuperMario.put(0,new Sprite(rutaCarpeta + "Mario_Sprites/supermario_parado.png"));
		spritesSuperMario.put(1,new Sprite(rutaCarpeta + "Mario_Sprites/supermario_caminando_derecha.gif"));
		spritesSuperMario.put(2,new Sprite(rutaCarpeta + "Mario_Sprites/supermario_caminando_izquierda.gif"));
		spritesSuperMario.put(3,new Sprite(rutaCarpeta + "Mario_Sprites/supermario_saltando_derecha.png"));
		spritesSuperMario.put(4,new Sprite(rutaCarpeta + "Mario_Sprites/supermario_saltando_izquierda.png"));
		spritesSuperMario.put(5,new Sprite(rutaCarpeta + "Mario_Sprites/supermario_parado_izquierda.png"));

		sprites.put(1, spritesSuperMario);

		//mapeo sprites MarioFuego
		Map<Integer, Sprite> spritesMarioFuego = new HashMap<Integer, Sprite>();
		spritesMarioFuego.put(0,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_parado.png"));
		spritesMarioFuego.put(1,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_caminando_derecha.gif"));
		spritesMarioFuego.put(2,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_caminando_izquierda.gif"));
		spritesMarioFuego.put(3,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_saltando_derecha.png"));
		spritesMarioFuego.put(4,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_saltando_izquierda.png"));
		spritesMarioFuego.put(5,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_parado_izquierda.png"));
		spritesMarioFuego.put(6,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_tirando_derecha.png"));
		spritesMarioFuego.put(7,new Sprite(rutaCarpeta + "Mario_Sprites/mariofuego_tirando_izquierda.png"));

		sprites.put(2, spritesMarioFuego);

		//mapeo Sprites MarioInvencible
		Map<Integer, Sprite> spritesMarioInvencible = new HashMap<Integer, Sprite>();
		spritesMarioInvencible.put(0,new Sprite(rutaCarpeta + "Mario_Sprites/marioinvencible_corriendo_derecha.gif"));
		spritesMarioInvencible.put(1,new Sprite(rutaCarpeta + "Mario_Sprites/marioinvencible_corriendo_derecha.gif"));
		spritesMarioInvencible.put(2,new Sprite(rutaCarpeta + "Mario_Sprites/marioinvencible_corriendo_izquierda.gif"));
		spritesMarioInvencible.put(3,new Sprite(rutaCarpeta + "Mario_Sprites/marioinvencible_corriendo_derecha.gif"));
		spritesMarioInvencible.put(4,new Sprite(rutaCarpeta + "Mario_Sprites/marioinvencible_corriendo_izquierda.gif"));
		spritesMarioInvencible.put(5,new Sprite(rutaCarpeta + "Mario_Sprites/marioinvencible_corriendo_izquierda.gif"));

		sprites.put(3, spritesMarioInvencible);
		return sprites;
	}

	//Fondo
	public Sprite getFondo() {
		return new Sprite(rutaCarpeta + "Background/fondo3.jpg");
	}

	//Plataformas
	public Sprite getSolido() {
		return new Sprite(rutaCarpeta + "Plataforma_Sprites/solido.png");
	}

	public Sprite[] getPregunta() {
		Sprite[] sprites = new Sprite[2];
		sprites[0] = new Sprite(rutaCarpeta + "Plataforma_Sprites/bloque_pregunta.gif");
		sprites[1] = new Sprite(rutaCarpeta + "Plataforma_Sprites/bloquepregunta_roto.png");
		return sprites;
	}
	
	public Sprite getLadrillo() {
		return new Sprite(rutaCarpeta + "Plataforma_Sprites/ladrillo.png");
	}
	
	public Sprite getBandera() {
		return new Sprite(rutaCarpeta + "Plataforma_Sprites/bandera.png");
	}
	
	public Sprite getVacio() {
		return new Sprite(rutaCarpeta + "Plataforma_Sprites/placeholder.png");
	}
	
	public Sprite getTuberia() {
		return new Sprite(rutaCarpeta + "Plataforma_Sprites/tuberia.png");
	}

	//Enemigos
	public Sprite getGoomba() {
		return new Sprite(rutaCarpeta + "/Enemigos_Sprites/goombaMovimiento.gif");
	}
	
	public  Map<Integer, Sprite> getBuzzy() {
		Map<Integer, Sprite> sprites = new HashMap<Integer, Sprite>();
		sprites.put(0, new Sprite(rutaCarpeta + "/Enemigos_Sprites/buzzy_beetle_derecha.gif"));
		sprites.put(1, new Sprite(rutaCarpeta + "/Enemigos_Sprites/buzzy_beetle_izquierda.gif"));
		return sprites;
	}
	
	public  Map<Integer, Sprite> getSpiny() {
		Map<Integer, Sprite> sprites = new HashMap<Integer, Sprite>();
		sprites.put(0, new Sprite(rutaCarpeta + "/Enemigos_Sprites/spiny_derecha.gif"));
		sprites.put(1, new Sprite(rutaCarpeta + "/Enemigos_Sprites/spiny_izquierda.gif"));
		return sprites;
	}
	
	public Sprite getLakitu() {
		return new Sprite(rutaCarpeta + "/Enemigos_Sprites/lakitu.png");
	}
	
	public Map<Integer, Sprite>  getKoopa() {
		Map<Integer, Sprite> sprites = new HashMap<Integer, Sprite>();
		sprites.put(0, new Sprite(rutaCarpeta + "/Enemigos_Sprites/koopa_troopa_derecha.gif"));
		sprites.put(1, new Sprite(rutaCarpeta + "/Enemigos_Sprites/koopa_troopa_izquierda.gif"));
		sprites.put(2, new Sprite(rutaCarpeta + "/Enemigos_Sprites/koopa_troopa_caparazon.gif"));
		return sprites;
	}
	
	public Sprite getPiranha() {
		return new Sprite(rutaCarpeta + "/Enemigos_Sprites/piranha.png");
	}

	//Power-Ups
	public Sprite getEstrella() {
		return new Sprite(rutaCarpeta + "PowerUps_Sprites/estrella.gif");
	}
	
	public Sprite getChampinionVerde() {
		return new Sprite(rutaCarpeta + "PowerUps_Sprites/champinion_Verde.png");
	}
	
	public Sprite getSuperChampinion() {
		return new Sprite(rutaCarpeta + "PowerUps_Sprites/champignon_rojo.png");
	}
	
	public Sprite getFlorDeFuego() {
		return new Sprite(rutaCarpeta + "PowerUps_Sprites/flor_de_fuego.gif");
	}
	
	public Sprite getMoneda() {
		return new Sprite(rutaCarpeta + "PowerUps_Sprites/moneda.gif");
	}
	
	public Sprite getBolaDeFuego() {
		return new Sprite(rutaCarpeta + "PowerUps_Sprites/bola_de_fuego.gif");
	}
	
}
