package Juego;

import java.awt.Rectangle;
import java.util.LinkedList;
import Estado.EstadoMario;
import Graficos.Observer;
import Graficos.ObserverJugador;
import Plataformas.Plataforma;
import Visitor.VisitorColisiones;

public interface EntidadJugador extends EntidadLogica, ElementoColisionable {
	public void registrarObserver(Observer o);
	public void mover();
	public boolean saltar();
	public void morir();
	public void accion();
	public Rectangle getBounds();
	public void accept(VisitorColisiones v);
	public void actualizarPuntaje(int p);
	public int getX();
	public int getY();
	public int getPuntaje();
	public int getMonedas();
	public int getVidas();
	public EstadoMario getEstado();
	public Juego getJuego();
	public char getUltimaDireccion();
	public void setX(int x);
	public void setY(int y);
	public void setIzquierda(boolean b);
	public void setDerecha(boolean b);
	public void setArriba(boolean b);
	public void setPlataformas(LinkedList<Plataforma> p);
	public void setEstado(EstadoMario estadoAnterior);
	public void setJuego(Juego j);
	public void setSpriteActual(int s);
	public void registrarObserverCamara(ObserverJugador o);
	public Observer getObserver();
	public void perderVida();
	public void teletransportar();
	public void setVidas(int i);
	public void comprobarColisiones();
}