package com.example;
import java.util.*;

public class CentroGrafo {
    private int[][] distancias;
    private Map<String, Integer> indiceCiudades;

    public CentroGrafo(int[][] distancias, Map<String, Integer> indiceCiudades) {
        this.distancias = distancias;
        this.indiceCiudades = indiceCiudades;
    }

    public String encontrarCiudadCentro() {
        int numCiudades = distancias.length;
        int[] sumasDistancias = new int[numCiudades];

        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                sumasDistancias[i] += distancias[i][j];
            }
        }

        int menorSuma = Integer.MAX_VALUE;
        int indiceCiudadCentro = -1;
        for (int i = 0; i < numCiudades; i++) {
            if (sumasDistancias[i] < menorSuma) {
                menorSuma = sumasDistancias[i];
                indiceCiudadCentro = i;
            }
        }

        for (Map.Entry<String, Integer> entry : indiceCiudades.entrySet()) {
            if (entry.getValue() == indiceCiudadCentro) {
                return entry.getKey();
            }
        }

        return "No se encontró ciudad centro";
    }
}
