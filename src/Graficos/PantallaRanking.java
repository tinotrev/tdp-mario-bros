package Graficos;

import javax.swing.*;
import Constantes.Constantes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import Ranking.EntidadRankeable;
import Ranking.Ranking;

public class PantallaRanking extends JPanel {

	private static final long serialVersionUID = 1L;
	private Ranking ranking;
	private JTextArea rankingArea;
	private JLabel fondo;

	public PantallaRanking(Ranking ranking) {
		this.ranking = ranking;
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		agregarFondo(layeredPane);
		agregarJText(layeredPane);
		agregarBotones(layeredPane);
	}

	private void agregarJText(JLayeredPane layeredPane) {
		// Crear un JTextArea para mostrar el ranking
		rankingArea = new JTextArea();
		Font valorFont;	
		rankingArea.setLineWrap(true); // Habilitar el ajuste de línea
		rankingArea.setWrapStyleWord(true); // Ajustar las palabras en lugar de cortar
		rankingArea.setMargin(new Insets(10, 110, 10, 80)); // Ajustar según sea necesario
		try {
			valorFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Constantes/SuperMarioBros.NES.ttf")).deriveFont(Font.BOLD, 11);
			rankingArea.setFont(valorFont); // Establecer la fuente en el JTextArea
		} catch (FontFormatException | IOException e) {
			System.out.println("Error al cargar la fuente");
		} 
		JScrollPane scrollPane = new JScrollPane(rankingArea); 
		scrollPane.setBounds((Constantes.VENTANA_ANCHO / 2) - 200, (Constantes.VENTANA_ALTO / 2) - 180, 400, 320); 
		layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);
	}

	public void agregarFondo(JLayeredPane layeredPane) {
		fondo = new JLabel();
		ImageIcon icono_imagen = new ImageIcon(getClass().getResource("/Imagenes/Pantallas/fondoRanking.png"));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		fondo.setIcon(icono_imagen_escalado);
		fondo.setBounds(0,0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		layeredPane.add(fondo, JLayeredPane.DEFAULT_LAYER);  // Agregar la imagen en la capa base
		layeredPane.setBounds(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		add(layeredPane);
	}

	public void agregarBotones(JLayeredPane layeredPane) {
		JButton botonVolver = new JButton();
		JButton botonActualizar = new JButton();
		transparentarBoton(botonVolver);
		transparentarBoton(botonActualizar);
		botonVolver.setBounds((Constantes.VENTANA_ANCHO / 2) + 40, (Constantes.VENTANA_ALTO / 2) + 160, 180, 70);
		layeredPane.add(botonVolver, JLayeredPane.PALETTE_LAYER);  // Agregar el botón en una capa superior
		botonActualizar.setBounds((Constantes.VENTANA_ANCHO / 2) - 220, (Constantes.VENTANA_ALTO / 2) + 160, 180, 70);
		layeredPane.add(botonActualizar, JLayeredPane.PALETTE_LAYER);  // Agregar el botón en una capa superior
		registrarOyenteBotonVolver(botonVolver); 
		registrarMouseListener(botonVolver);
		registrarOyenteBotonActualizar(botonActualizar); 
		registrarMouseListener(botonActualizar);
	}

	public void mostrarRanking() {
		ranking.cargarRanking();
		List<EntidadRankeable> mejoresJugadores = ranking.obtenerMejoresEntidades(5);
		StringBuilder sb = new StringBuilder("\n");
		for(EntidadRankeable jugador : mejoresJugadores) {
			sb.append(jugador.getUsuario()).append(" - ").append(jugador.getPuntaje()).append("\n");
		}
		rankingArea.setText(sb.toString());
	}

	private void volverAPantallaPrincipal() {
		ControladorGraficos.volverMenu();
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

	public void registrarOyenteBotonVolver(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAPantallaPrincipal();
			}
		});
	}

	public void registrarOyenteBotonActualizar(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarRanking();
			}
		});
	}

	public void transparentarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}

}