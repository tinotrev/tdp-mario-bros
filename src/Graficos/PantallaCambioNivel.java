package Graficos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import Constantes.Constantes;
import Juego.Juego;

public class PantallaCambioNivel extends Pantalla {

	private static final long serialVersionUID = 1L;
	protected ControladorGraficos controlador;
	protected JLabel fondo;
	protected Juego juego;
	protected JLabel labelPuntaje;
	protected JLabel labelVidas;
	protected JLabel labelNivel;
	protected JLayeredPane layeredPane;

	public PantallaCambioNivel(ControladorGraficos cg, Juego juego) {
		controlador = cg;
		this.juego = juego;
		layeredPane = new JLayeredPane();
		setLayout(null);  // Usar un layout sencillo
		agregarImagenFondo();
		agregarLabelVidasPuntaje();
		agregarBotonContinuar();
		actualizarInformacionLabelNivel();
	}

	public void agregarImagenFondo() {
		fondo = new JLabel();
		ImageIcon icono_imagen = new ImageIcon(getClass().getResource("/Imagenes/Pantallas/pantallaNiveles.png"));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		fondo.setIcon(icono_imagen_escalado);
		add(fondo);
		fondo.setBounds(0,0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		layeredPane.add(fondo, JLayeredPane.DEFAULT_LAYER);  // Agregar la imagen en la capa base
		layeredPane.setBounds(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		add(layeredPane);
	}

	public void agregarLabelVidasPuntaje() {
		labelVidas = new JLabel("");
		labelPuntaje = new JLabel();
		labelNivel = new JLabel("");
		Font valorFont;	
		try {
			valorFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Constantes/SuperMarioBros.NES.ttf")).deriveFont(Font.BOLD, 30);
			labelVidas.setFont(valorFont);
			labelPuntaje.setFont(valorFont);
			labelNivel.setFont(valorFont);
			Color colorHex = Color.decode("#ff3131"); // Color rojo brillante
			labelNivel.setForeground(colorHex);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		labelVidas.setBounds(120, 20, 64, 54);
		labelPuntaje.setBounds(850, 20, 80, 53);
		labelNivel.setBounds((Constantes.VENTANA_ANCHO / 2) + 95, (Constantes.VENTANA_ALTO / 2) - 117, 80, 60);
		add(labelVidas);
		add(labelPuntaje);
		add(labelNivel);
		layeredPane.add(labelVidas, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(labelPuntaje, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(labelNivel, JLayeredPane.PALETTE_LAYER);
	}

	public void agregarBotonContinuar() {	// Crear el botón de volver al menú principal
		JButton continuarButton = new JButton();
		continuarButton.setBounds((Constantes.VENTANA_ANCHO / 2) + 240, (Constantes.VENTANA_ALTO / 2) + 240, 250, 30);
		add(continuarButton);
		transparentarBoton(continuarButton);
		layeredPane.add(continuarButton, JLayeredPane.PALETTE_LAYER);
		registrarMouseListener(continuarButton);
		registrarOyenteBotonContinuar(continuarButton);
	}

	public void actualizarInformacionLabelNivel() {
		if(juego.getNumeroNivel() + 1 < 4) {
			labelNivel.setText(String.valueOf(juego.getNumeroNivel()+1));
		}
		else {
			ImageIcon icono_imagen = new ImageIcon(getClass().getResource("/Imagenes/Pantallas/Congratulations.png"));
			Image imagen_escalada = icono_imagen.getImage().getScaledInstance(Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO, Image.SCALE_SMOOTH);
			Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
			fondo.setIcon(icono_imagen_escalado);
		}
	}

	public void setNumeroVidas(String s) {
		labelVidas.setText(s);
	}

	public void registrarOyenteBotonContinuar(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Acción al hacer clic en el botón
				juego.cargarNivel();
				if(Juego.numNivel <= 3) {
					controlador.mostrarPantallaGameplay();
				}
				if(Juego.numNivel == 4) {
					Juego.numNivel = 1;
				}
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
