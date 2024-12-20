package Ranking;

import java.io.Serializable;

public class EntidadRankeable implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private String usuario;
    private int puntaje;

    public EntidadRankeable(String usuario, int puntaje) {
        this.usuario = usuario;
        this.puntaje = puntaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String toString() {
        return usuario + " - " + puntaje;
    }
    
}