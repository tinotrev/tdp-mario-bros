package Graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Constantes.*;

public class PantallaPrincipal extends Pantalla {

	private static final long serialVersionUID = 1L;
	private ControladorGraficos controladorGrafico;
	private JLabel fondo;
	private JLayeredPane layeredPane;

	public PantallaPrincipal(ControladorGraficos controladorGrafico) {
		this.controladorGrafico = controladorGrafico;
		setLayout(null);
		layeredPane = new JLayeredPane();
		agregarImagenFondo();
		agregarBotonesIniciar();
		agregarBotonRanking();
	}

	public void agregarImagenFondo() {
		fondo = new JLabel();
		ImageIcon icono_imagen = new ImageIcon(getClass().getResource("/Imagenes/Pantallas/fondoMenu.png"));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		fondo.setIcon(icono_imagen_escalado);
		fondo.setBounds(0,0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		layeredPane.add(fondo, JLayeredPane.DEFAULT_LAYER);  //Agregar la imagen en la capa base
		layeredPane.setBounds(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		add(layeredPane);
	}

	public void agregarBotonesIniciar() {
		JButton botonIniciarOriginal = new JButton();
		JButton botonIniciarAlternativo = new JButton();
		transparentarBoton(botonIniciarOriginal);
		transparentarBoton(botonIniciarAlternativo);
		botonIniciarAlternativo.setBounds((Constantes.VENTANA_ANCHO / 2) - 415, (Constantes.VENTANA_ALTO / 2) + 65, 470, 30);
		layeredPane.add(botonIniciarAlternativo, JLayeredPane.PALETTE_LAYER);  //Agregar el botón en una capa superior
		botonIniciarOriginal.setBounds((Constantes.VENTANA_ANCHO / 2) - 415, (Constantes.VENTANA_ALTO / 2) - 0, 410, 30);
		layeredPane.add(botonIniciarOriginal, JLayeredPane.PALETTE_LAYER);  //Agregar el botón en una capa superior
		registrarOyenteBotonIniciarOriginal(botonIniciarOriginal); 
		registrarMouseListener(botonIniciarOriginal);
		registrarOyenteBotonIniciarAlternativo(botonIniciarAlternativo); 
		registrarMouseListener(botonIniciarAlternativo);
	}

	public void agregarBotonRanking() {
		JButton botonRanking = new JButton();
		botonRanking.setBounds((Constantes.VENTANA_ANCHO / 2) - 290, (Constantes.VENTANA_ALTO / 2) + 120, 160, 90);
		layeredPane.add(botonRanking, JLayeredPane.PALETTE_LAYER);  //Agregar el botón en una capa superior
		transparentarBoton(botonRanking);
		registrarOyenteBotonRanking(botonRanking);
		registrarMouseListener(botonRanking);
	}

	public void registrarMouseListener(JButton b) {
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));  //Cambiar a cursor de mano
			}
			
			public void mouseExited(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  //Restaurar el cursor normal
			}
		});
	}

	public void registrarOyenteBotonIniciarOriginal(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorGrafico.iniciarJuegoOriginal();
			}
		});
	}

	public void registrarOyenteBotonIniciarAlternativo(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorGrafico.iniciarJuegoAlternativo();
			}
		});
	}

	public void registrarOyenteBotonRanking(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorGrafico.mostrarPantallaRanking();
			}
		});
	}

	public void transparentarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}

}
