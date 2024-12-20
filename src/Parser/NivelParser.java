package Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import Fabricas.JuegoFactory;
import Juego.EntidadJugador;
import Juego.Escenario;
import Juego.Fondo;
import Plataformas.Bandera;
import Plataformas.Plataforma;
import PowerUps.PowerUp;

public class NivelParser {

	private JuegoFactory fabrica;
	private BufferedReader br;

	//Mapeo de números a entidades
	private static Map<Integer, String> mapeoEntidades;

	//Constructor
	public NivelParser(JuegoFactory fr) {
		fabrica = fr;
		configurarMapeo();
	}

	//Definir mapeo entre números y entidades
	private void configurarMapeo() {
		mapeoEntidades = new HashMap<>();

		mapeoEntidades.put(1, "mario");             

		mapeoEntidades.put(2, "solido");           
		mapeoEntidades.put(3, "ladrillo");    
		mapeoEntidades.put(4, "bandera");          
		mapeoEntidades.put(5, "vacio");            
		mapeoEntidades.put(6, "tuberia");  
		mapeoEntidades.put(7,"pregunta");

		mapeoEntidades.put(8, "campinionverde");    
		mapeoEntidades.put(9, "superchampinion");   
		mapeoEntidades.put(10, "flordefuego"); 
		mapeoEntidades.put(11, "moneda"); 
		mapeoEntidades.put(12, "estrella");

		mapeoEntidades.put(13, "lakitu");          
		mapeoEntidades.put(14, "koopatroopa");      
		mapeoEntidades.put(15, "piranhaplant");     
		mapeoEntidades.put(16, "spiny");            
		mapeoEntidades.put(17, "buzzybeetle");
		mapeoEntidades.put(18, "goomba");
	}

	public Escenario cargarArchivoNivel(String rutaArchivo, int nivel, Fondo f) throws IOException {
		try {
			//BufferedReader permite leer el archivo línea por línea, lo que es útil para procesar archivos de texto.
			//new FileReader(rutaArchivo) crea un lector de archivos que abre el archivo en modo de lectura.
			br = new BufferedReader(new FileReader(rutaArchivo));
		} catch (FileNotFoundException e) {	
		}
		Escenario escenario = new Escenario(f, nivel);
		String linea;
		Plataforma ultimoBloque = null;
		//el while se repite hasta el final del archivo
		while ((linea = br.readLine()) != null) {
			//eliminas los espacios en blanco al principio y al final de la cadena
			if(linea.trim().isEmpty()) {
				linea = br.readLine();
				continue;
			}
			//Parsear cada línea
			String[] partes = linea.split(",");  //las partes en el txt están separadas por comas

			if(partes.length != 3) {    
				break;
			}

			int tipoEntidad = Integer.parseInt(partes[0]);  //Número de la entidad
			int x = Integer.parseInt(partes[1]);            //Coordenada X
			int y = Integer.parseInt(partes[2]);            //Coordenada Y

			//Usar la fábrica para crear la entidad
			if(tipoEntidad != 0) {
				if(tipoEntidad == 1) {
					EntidadJugador el = fabrica.crearMario(x, y);
					escenario.agregarMario(el); 
				}
				else {
					String tipo = mapeoEntidades.get(tipoEntidad);
					if(tipoEntidad >= 2 && tipoEntidad <=7 ) {
						Plataforma plat = fabrica.crearPlataforma(tipo, x, y);
						escenario.agregarPlataformas(plat);
						if(tipoEntidad == 7) {
							ultimoBloque=plat;
						}
						if(tipoEntidad == 4) {
							escenario.agregarBandera((Bandera) plat);
						}
					}
					else if(tipoEntidad >= 8 && tipoEntidad <=12) {
						PowerUp power = fabrica.crearPowerUp(tipo, x, y);
						escenario.agregarPowerUp(power);
						if(tipoEntidad!=11) {
							ultimoBloque.setPowerUp(power);
						}
					}
					else {
						escenario.agregarEnemigo(fabrica.crearEnemigo(tipo, x, y));
					}                    	
				}
			} 
		}
		return escenario;
	}

}