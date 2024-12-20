package Fabricas;

import java.awt.Image;

public class Sprite {

	protected String rutaImagen;
	private Image imagen;

	public Sprite (String ri) {
		rutaImagen = ri;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public Image getImage() {
		return imagen;
	}
	
}
