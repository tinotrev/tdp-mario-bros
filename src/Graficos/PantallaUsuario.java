package Graficos;

import javax.swing.*;

import Constantes.Constantes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PantallaUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField campoUsuario;
	private JLabel fondo;
	public static String usuario;
	private JLayeredPane layeredPane = new JLayeredPane();

	public PantallaUsuario() {
		setLayout(null);
		agregarGif();
		agregarCampoTexto();
		agregarBotonContinuar();
	}

	public void agregarGif() {
		ImageIcon gifIcon = new ImageIcon("src/Imagenes/Pantallas/fondoUsuario.gif");
		fondo = new JLabel();
		Image scaledImage = gifIcon.getImage().getScaledInstance(Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO, Image.SCALE_DEFAULT);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		fondo.setIcon(scaledIcon);
		add(fondo);
		fondo.setBounds(0,0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		layeredPane.add(fondo, JLayeredPane.DEFAULT_LAYER);  
		layeredPane.setBounds(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
		add(layeredPane);	
	}

	public void agregarCampoTexto() {    
		//Campo de texto para el nombre de usuario
		campoUsuario = new JTextField(20);
		Font valorFont;	
		try {
			valorFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Constantes/SuperMarioBros.NES.ttf")).deriveFont(Font.BOLD, 15);
			campoUsuario.setFont(valorFont);	
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		campoUsuario.setBounds((Constantes.VENTANA_ANCHO / 2) - 200, (Constantes.VENTANA_ALTO / 2) - 80, 400, 30);
		add(campoUsuario);
		layeredPane.add(campoUsuario, JLayeredPane.PALETTE_LAYER);
	}

	//Botón para continuar
	public void agregarBotonContinuar() {
		JButton botonContinuar = new JButton();
		botonContinuar.setBounds((Constantes.VENTANA_ANCHO / 2) - 110, (Constantes.VENTANA_ALTO / 2) - 30, 205, 75);
		layeredPane.add(botonContinuar, JLayeredPane.PALETTE_LAYER);  
		transparentarBoton(botonContinuar);
		registrarOyenteBotonContinuar(botonContinuar);
		registrarMouseListener(botonContinuar);
	}

	public void registrarOyenteBotonContinuar(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(campoUsuario.getText().length() > 10){
					JOptionPane.showMessageDialog(PantallaUsuario.this, "Su nombre de usuario es muy largo(<= 10) .", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (!campoUsuario.getText().trim().isEmpty()) {
					establecerUsuario();
				} 
				else {
					JOptionPane.showMessageDialog(PantallaUsuario.this, "Por favor, ingrese un nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void establecerUsuario() {
		usuario = campoUsuario.getText().trim();
		ControladorGraficos.volverMenu();
	}

	public void registrarMouseListener(JButton b) {
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}

	public void transparentarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}

}