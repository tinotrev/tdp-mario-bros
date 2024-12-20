package Graficos;

import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import Juego.EntidadJugador;
import Juego.EntidadLogica;
import Juego.Juego;
import Juego.KeyListenerMario;
import Ranking.Ranking;
import Constantes.Constantes;
import Fabricas.FabricaSprites;

public class ControladorGraficos implements ControladorEntreJuegoGrafico {

	protected static JFrame ventana;
	protected PantallaUsuario pu;
	protected PantallaGameplay pg;
	protected static PantallaPrincipal pp;
	protected PantallaRanking pr;
	protected PantallaHud hud;
	protected JScrollPane scrollPane;
	protected JPanel contenedorPrincipal;
	protected Ranking ranking;
	protected PantallaGameOver pgo;
	protected PantallaCambioNivel pcn;
	protected Juego juego;
	protected FabricaSprites fs;

	public ControladorGraficos(Juego j) {
		if(ventana == null) {     
			juego = j;
			fs = j.getFabricaSprite();
			pu = new PantallaUsuario();
			hud = new PantallaHud(j);
			pg = new PantallaGameplay(fs.getFondo());
			contenedorPrincipal = new JPanel(new BorderLayout());
			pp = new PantallaPrincipal(this);
			pgo = new PantallaGameOver(this);
			pcn = new PantallaCambioNivel(this,juego);
			ranking = new Ranking();
			pr = new PantallaRanking(ranking);
			ventana = new JFrame("Super Mario Bros: ");
			configurarVentana();
		}
	}

	public void reiniciarPantallas() {
		pg = new PantallaGameplay(fs.getFondo());
		pp = new PantallaPrincipal(this);
		pgo = new PantallaGameOver(this);
		pcn = new PantallaCambioNivel(this,juego);
		contenedorPrincipal = new JPanel(new BorderLayout());
		hud.resetearHiloTiempo();
		hud = new PantallaHud(juego);
		scrollPane = null;
		configurarVentana();
	}

	public void iniciarJuego() {
		juego.cargarNivel();
		mostrarPantallaGameplay();      
	}

	private void configurarVentana() {
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setSize(Constantes.VENTANA_ANCHO, 633);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		// Crear JScrollPane solo para PantallaGameplay
		scrollPane = new JScrollPane(pg);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		// Agregar hud (parte superior fija) y el scrollPane con PantallaGameplay al contenedor principal
		contenedorPrincipal.add(hud, BorderLayout.NORTH);
		contenedorPrincipal.add(scrollPane, BorderLayout.CENTER);
		ventana.setContentPane(contenedorPrincipal); // Establecer el contenedor principal en la ventana
	}

	public void actualizarPosPantalla(EntidadJugador mario, char direccionMovimiento) {
		int anchoVentana = scrollPane.getViewport().getWidth();
		int anchoPantallaGameplay = pg.getWidth();
		int nuevaPosX = scrollPane.getViewport().getViewPosition().x;
		if(direccionMovimiento == 'i') {
			nuevaPosX = Math.max(nuevaPosX - 5, 0);
		}else if (direccionMovimiento == 'd') {
			nuevaPosX = Math.min(nuevaPosX + 5, anchoPantallaGameplay - anchoVentana);
		}
		int nuevaPosCentral = mario.getX() - (anchoVentana / 2) + (mario.getBounds().width / 2);
		JViewport viewport = scrollPane.getViewport();
		viewport.setViewPosition(new Point(Math.max(0, Math.min(nuevaPosCentral, anchoPantallaGameplay - anchoVentana)), viewport.getViewPosition().y));
		refrescar();
	}

	public Observer registrarEntidad(EntidadLogica el) {
		Observer o = pg.incorporarEntidad(el);
		refrescar();
		return o;
	}

	public Observer registrarJugador(EntidadJugador el) {
		Observer o = pg.incorporarJugador(el);
		refrescar();
		return o;
	}

	public void registrarOyenteVentana(KeyListenerMario oyente) {
		ventana.addKeyListener(oyente); 
		ventana.requestFocusInWindow(); 
	}

	protected void refrescar() {
		ventana.revalidate();
		ventana.repaint();
	}

	public void mostrarPantallaPrincipal() {
		ventana.setContentPane(pp);
		ventana.revalidate();
	}

	public void mostrarPantallaGameplay() {
		ventana.setContentPane(contenedorPrincipal);
		ventana.revalidate();
	}

	public void mostrarPantallaRanking() {
		ventana.setContentPane(pr);
		ventana.revalidate();
	}

	public PantallaGameplay getPantallaGameplay() {
		return pg;
	}

	public void mostrarPantallaGameOver() {
		ventana.setContentPane(pgo);
		ventana.revalidate();
	}

	public void reiniciarHUD() {
		hud.reiniciar();
	}

	public void mostrarPantallaCambioNivel() {
		ventana.setContentPane(pcn);
		ventana.revalidate();
	}

	public void mostrarPantallaUsuario() {
		ventana.setContentPane(pu);
		ventana.revalidate();
	}

	public static void volverMenu() {
		ventana.setContentPane(pp);
		ventana.revalidate();
	}

	public Juego getJuego() {
		return juego;
	}

	public void iniciarJuegoOriginal() {
		juego.cargarOriginal();
		fs = juego.getFabricaSprite();
		pg.setFondo(fs.getFondo());
		mostrarPantallaGameplay();
	}

	public void iniciarJuegoAlternativo() {
		juego.cargarAlternativo();
		fs = juego.getFabricaSprite();
		pg.setFondo(fs.getFondo());
		mostrarPantallaGameplay();
	}

}
