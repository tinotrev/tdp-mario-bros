package Graficos;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Constantes.Constantes;

public class PantallaGameOver extends Pantalla {

	private static final long serialVersionUID = 1L;
	protected ControladorGraficos controlador;
	protected JLabel fondo;
	protected JLayeredPane layeredPane;

	public PantallaGameOver(ControladorGraficos cg) {
		controlador = cg;
		layeredPane = new JLayeredPane();
		setLayout(null);
		agregarGif();
		agregarBoton();
	}

	public void agregarGif() {
		ImageIcon gifIcon = new ImageIcon("src/Imagenes/Pantallas/fondoGameOver.gif");
		fondo = new JLabel();
		Image scaledImage = gifIcon.getImage().getScaledInstance(Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO, Image.SCALE_DEFAULT);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		fondo.setIcon(scaledIcon);
		add(fondo);
		fondo.setBounds(0,0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		layeredPane.add(fondo, JLayeredPane.DEFAULT_LAYER);  // Agregar la imagen en la capa base
		layeredPane.setBounds(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		add(layeredPane);	
	}

	public void agregarBoton() {
		// Crear el botón de volver al menú principal
		JButton volverMenuButton = new JButton();
		volverMenuButton.setBounds((Constantes.VENTANA_ANCHO / 2) + 340, (Constantes.VENTANA_ALTO / 2) + 250, 160, 30);
		add(volverMenuButton);  // Colocar el botón en la parte inferior
		transparentarBoton(volverMenuButton);
		layeredPane.add(volverMenuButton, JLayeredPane.PALETTE_LAYER);  // Agregar el botón en una capa superior
		registrarMouseListener(volverMenuButton);
		registrarOyenteBotonVolver(volverMenuButton);
	}

	public void registrarOyenteBotonVolver(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Acción al hacer clic en el botón
				controlador.reiniciarPantallas();
				controlador.mostrarPantallaPrincipal();
			}
		});
	}

	public void registrarMouseListener(JButton b) {
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Cambiar a cursor de mano
			}

			public void mouseExited(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  // Restaurar el cursor normal
			}
		});
	}

	public void transparentarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}
	
}
