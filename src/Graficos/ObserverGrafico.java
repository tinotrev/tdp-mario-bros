package Graficos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Juego.EntidadLogica;


public abstract class ObserverGrafico extends JLabel implements Observer{

	private static final long serialVersionUID = 1L;
	protected EntidadLogica entidadObservada;

	protected ObserverGrafico(EntidadLogica el) {
		super(); //constructor de Label
		entidadObservada = el;
	}

	public void actualizar() {
		actualizarImagen();
		actualizarPosicionTamano();
	}

	protected void actualizarImagen() {
		String rutaImagen = entidadObservada.getSprite().getRutaImagen();
		ImageIcon icono = new ImageIcon(rutaImagen);
		setIcon(icono);
	}

	protected void actualizarPosicionTamano() {
		int x = entidadObservada.getX();
		int y = entidadObservada.getY();
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x,y, ancho, alto);
	}

	public void setEntidadObservada(EntidadLogica el) {
		entidadObservada = el;
	}

	public void eliminar() {
		entidadObservada = null;
		setIcon(null);
	}

	public void setInvisible(boolean b) {
		setVisible(!b);
	}

}
