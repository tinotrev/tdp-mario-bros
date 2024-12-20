package Juego;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Map;
import Fabricas.Sprite;
import Graficos.Observer;
import Graficos.ObserverJugador;
import Plataformas.Plataforma;
import Visitor.VisitorColisiones;
import Constantes.Constantes;
import Estado.EstadoMario;
import Estado.Normal;

public class Mario implements EntidadJugador {

	public static int posicionX;   
	private int posicionY;
	private EstadoMario estado;
	private int puntaje;   
	private Map<Integer,Map<Integer,Sprite>> sprites;
	private int[] spriteActual;
	private LinkedList<Plataforma> plataformas;
	private Observer observer;
	private ObserverJugador camara;
	private char direccion;
	private char ultimaDireccion;
	private int monedas;
	private int velocidadSalto = 0;
	private boolean enSuelo = false;   
	private boolean izquierda = false;
	private boolean derecha = false;
	private boolean arriba = false;
	private int vidas;
	protected Juego game;

	//Constructor
	public Mario(Map<Integer,Map<Integer,Sprite>> mapSprite,int x, int y) {
		Mario.posicionX = x;
		this.posicionY = y;
		this.puntaje = 0;
		sprites = mapSprite;
		spriteActual= new int[2];
		spriteActual[0] = 0;
		spriteActual[1] = 0;
		estado = new Normal(this);
		vidas = 3;
		monedas = 0;
		puntaje = 0;
		ultimaDireccion = 'd';
	}

	public void setIzquierda(boolean b) {
		izquierda = b;
		spriteActual[0] = estado.getKey();
		spriteActual[1] = 2;
	}

	public void setDerecha(boolean b) {
		derecha = b;
		spriteActual[0] = estado.getKey();
		spriteActual[1] = 1;
	}

	public void setArriba(boolean b) {
		arriba = b;
		if(ultimaDireccion == 'i') {
			spriteActual[0] = estado.getKey();
			spriteActual[1] = 4;
		} 
		else {
			spriteActual[0] = estado.getKey();
			spriteActual[1] = 3;
		}
	}

	public void setEstado(EstadoMario em) {
		estado = em;
		estado.actualizarSprite();
	}

	public void setPlataformas(LinkedList<Plataforma> p) {
		plataformas = p;
	}

	public void mover() {
		direccion = 'n';//direccion neutra

		//Lógica de salto
		if(arriba) {
			if(enSuelo) {
				velocidadSalto = -20;
				enSuelo = false;
			}
			arriba = false;
		}

		//Aplicar gravedad
		velocidadSalto += Constantes.GRAVEDAD;
		if(velocidadSalto > 15) {
			velocidadSalto = 15;
		}

		//Aplicar movimiento vertical
		setY(getY() + velocidadSalto);

		//Resetear estado de suelo
		enSuelo = false;

		//Movimiento horizontal primero
		if(izquierda) {
			direccion = 'i';
			ultimaDireccion = 'i';
			if(posicionX > 5) {
				posicionX -= 5;
			}
		}

		if(derecha) {
			direccion = 'd';
			ultimaDireccion = 'd';
			if(posicionY < 3900) {
				posicionX += 5;
			}
		}

		actualizarSprite();
		observer.actualizar();
		camara.actualizar(direccion);
	}

	private void actualizarSprite() {
		//Actualizar sprite y observer
		if(!arriba && !derecha && !izquierda) {
			spriteActual[0] = estado.getKey();
			if(ultimaDireccion == 'd') {
				spriteActual[1] = 0;
			} else {
				spriteActual[1] = 5;
			}
		}
	}

	public void comprobarColisiones() {
		//Guardar posición anterior
		int oldX = posicionX;
		int oldY = posicionY;
		//Comprobar colisiones con orden de prioridad
		for(Plataforma plataforma : plataformas) {
			Plataforma p = plataforma;
			//Ignorar plataformas vacías
			if(!p.tieneHitbox()) {
				continue;
			}

			if(getBounds().intersects(plataforma.getBounds())) {
				Rectangle bounds = getBounds();
				Rectangle platBounds = plataforma.getBounds();

				//Primero resolver colisiones verticales
				if(velocidadSalto >= 0 &&
						bounds.y + bounds.height - velocidadSalto <= platBounds.y) {
					//Colisión desde arriba
					setY(platBounds.y - bounds.height);
					enSuelo = true;
					velocidadSalto = 0;
				}
				else if(velocidadSalto < 0 &&
						bounds.y >= platBounds.y + platBounds.height - Math.abs(velocidadSalto)) {
					//Colisión desde abajo
					setY(platBounds.y + platBounds.height);
					velocidadSalto = 0;
					accept(plataforma.getVisitor());
					break;
				}
				//Luego resolver colisiones horizontales
				else if(getBounds().intersects(plataforma.getBounds())) {
					if(direccion == 'i') {
						posicionX = plataforma.getX() + plataforma.getBounds().width;
					} else if (direccion == 'd') {
						posicionX = plataforma.getX() - getBounds().width;
					}

					// Si hay colisión después de resolver, volver a la posición anterior
					if (getBounds().intersects(plataforma.getBounds())) {
						posicionX = oldX;
						setY(oldY);
					}
				}
			}
		}
	}

	public boolean saltar() {
		if(enSuelo) {
			velocidadSalto = -20;
			enSuelo = false;
			return false;  //Importante: retornar false para que no siga saltando
		}
		return false;
	}

	public void actualizarPuntuacion(int puntos) {
		puntaje += puntos;
		System.out.println("La puntuación de Mario se actualiza. Puntaje actual : " + puntaje);
	}

	public void lanzarBolaDeFuego() {
		
	}

	public void setPuntaje(int puntos) {
		puntaje = puntos;
	}

	public int getX() {
		return posicionX;
	}

	public int getY() {
		return posicionY;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public Sprite getSprite() {
		return sprites.get(spriteActual[0]).get(spriteActual[1]);
	}

	public Rectangle getBounds() {
		return estado.getBounds();  //Obtener la hitbox desde el estado actual
	}

	public void registrarObserver(Observer o) {
		observer = o;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setX(int x) {
		posicionX = x;
	}

	public void setY(int y) {
		posicionY = y;
	}

	public void accept(VisitorColisiones v) {
		v.visit(this);
	}

	public void morir() {
		//
	}

	public EstadoMario getEstado() {
		return estado;
	}

	public void registrarObserverCamara(ObserverJugador o) {
		camara = o;
	}

	public Observer getObserver() {
		return observer;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public void actualizarPuntaje(int p) {
		puntaje += p;
	}

	public void perderVida() {
		vidas--;
		if(getVidas() == 0) {
			game.terminarNivel();
			game.setPuntosTotales(0);
		}
	}

	public void teletransportar() {
		posicionX = 35;
		posicionY = 470 - getBounds().height;
	}

	public void accion() {
		estado.accion();
	}

	public char getUltimaDireccion() {
		return ultimaDireccion;
	}

	public void setSpriteActual(int s) {
		spriteActual[1]=s;
	}

	public void setJuego(Juego j) {
		game = j;
	}

	public Juego getJuego() {
		return game;
	}

}