package Juego;

import java.awt.Rectangle;

import Visitor.VisitorColisiones;

public interface ElementoColisionable {
	public void accept(VisitorColisiones v);
	public Rectangle getBounds();
}
