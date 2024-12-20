package Graficos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import Juego.Juego;
import Juego.EntidadJugador;

public class PantallaHud extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JLabel labelPuntaje;
	public static JLabel labelVidas;
	public static JLabel labelCronometro;
	public static JLabel labelNivelActual;
	public static int tiempoRestante = 303;
	private static Timer timer;
	private long tiempoInicio;
	private Image fondo;
	private Juego juego;

	public PantallaHud(Juego j) {
		juego = j;
		setLayout(null);
		setPreferredSize(new Dimension(800, 100)); 
		fondo = new ImageIcon("src/Imagenes/Hud/hudbackground.png").getImage();
		setBorder(new LineBorder(new Color(66, 132, 245), 10));
		ImageIcon iconVidas = new ImageIcon("src/Imagenes/Hud/vidas.png");
		ImageIcon iconPuntaje = new ImageIcon("src/Imagenes/Hud/moneda.png");
		JLabel labelIconoVidas = new JLabel(iconVidas);
		JLabel labelIconoPuntaje = new JLabel(iconPuntaje);
		labelNivelActual = new JLabel("Nivel Actual: " + Juego.numNivel);
		labelVidas = new JLabel("3");
		labelPuntaje = new JLabel("0");
		tiempoRestante = 300;
		labelCronometro = new JLabel(formatTime(tiempoRestante));		
		Font valorFont;	
		try {
			valorFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Constantes/SuperMarioBros.NES.ttf")).deriveFont(Font.BOLD, 20);
			labelVidas.setFont(valorFont);
			labelPuntaje.setFont(valorFont);
			labelCronometro.setFont(valorFont);
			labelNivelActual.setFont(valorFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		labelVidas.setForeground(Color.WHITE);
		labelPuntaje.setForeground(Color.WHITE);
		labelCronometro.setForeground(Color.WHITE);
		labelNivelActual.setForeground(Color.WHITE);
		labelIconoVidas.setBounds(40, 20, 64, 54);
		labelIconoPuntaje.setBounds(770, 20, 80, 53);
		labelVidas.setBounds(120, 20, 64, 54);
		labelPuntaje.setBounds(850, 20, 80, 53);
		labelCronometro.setBounds(420, 20, 150, 40);
		labelNivelActual.setBounds(330, 50, 400, 40);
		add(labelIconoVidas);
		add(labelIconoPuntaje);
		add(labelVidas);
		add(labelPuntaje);
		add(labelCronometro);
		add(labelNivelActual);
		setBackground(new Color(66, 132, 245)); 
		iniciarCronometro();
	}

	private void iniciarCronometro() {
		tiempoInicio = System.currentTimeMillis();
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long tiempoActual = System.currentTimeMillis();
				int tiempoTranscurrido = (int) ((tiempoActual - tiempoInicio) / 1000);
				int tiempoRestanteActual = 303 - tiempoTranscurrido;
				if(tiempoRestanteActual >= 0) {
					int minutos = tiempoRestanteActual / 60;
					int segundos = tiempoRestanteActual % 60;
					labelCronometro.setText(String.format("%02d:%02d", minutos, segundos));
				} else {
					timer.stop();
					juego.terminarNivel();
				}
			}
		});
		timer.start();
	}

	public String formatTime(int segundos) {
		int minutos = segundos / 60;
		int segundosRestantes = segundos % 60;
		return String.format("%02d:%02d", minutos, segundosRestantes);
	}

	public static void actualizarLabelsInformacion(EntidadJugador jugador) {
		labelPuntaje.setText(String.valueOf(jugador.getPuntaje()));
		labelVidas.setText(String.valueOf(jugador.getVidas()));
		labelNivelActual.setText("Nivel Actual: " + Juego.numNivel);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int imgWidth = fondo.getWidth(this);
		int imgHeight = fondo.getHeight(this);
		int panelWidth = getWidth();
		int panelHeight = getHeight();
		int x = (panelWidth - imgWidth) / 2;
		int y = (panelHeight - imgHeight) / 2;
		g.drawImage(fondo, x, y, this);
	}

	public void reiniciar() {
		labelVidas = new JLabel("3");
		labelPuntaje = new JLabel("0");
		labelCronometro = new JLabel(formatTime(tiempoRestante));
		labelNivelActual.setText("Nivel Actual: " + Juego.numNivel);
	}

	public void resetearHiloTiempo() {
		timer.stop();
		timer = null;
	}

}