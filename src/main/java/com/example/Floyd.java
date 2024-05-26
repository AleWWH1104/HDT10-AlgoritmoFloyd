package com.example;

import java.util.*;

public class Floyd {
    public int[][] distancias;
    public int[][] intermedios;
    public Map<String, Integer> indiceCiudades;
    public List<String> ciudades;

    public Floyd(Map<String, Map<String, Integer>> grafo) {
        inicializar(grafo);
        calcularDistancias();
    }

    private void inicializar(Map<String, Map<String, Integer>> grafo) {
        int numCiudades = grafo.size();
        distancias = new int[numCiudades][numCiudades];
        intermedios = new int[numCiudades][numCiudades];
        indiceCiudades = new HashMap<>();
        ciudades = new ArrayList<>(grafo.keySet());

        int indice = 0;
        for (String ciudad : grafo.keySet()) {
            indiceCiudades.put(ciudad, indice);
            indice++;
        }

        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                    intermedios[i][j] = -1;
                } else {
                    distancias[i][j] = Integer.MAX_VALUE / 2; // Evitar overflow
                    intermedios[i][j] = -1;
                }
            }
        }

        for (String ciudad1 : grafo.keySet()) {
            for (String ciudad2 : grafo.get(ciudad1).keySet()) {
                int distancia = grafo.get(ciudad1).get(ciudad2);
                int indiceCiudad1 = indiceCiudades.get(ciudad1);
                int indiceCiudad2 = indiceCiudades.get(ciudad2);
                distancias[indiceCiudad1][indiceCiudad2] = distancia;
                intermedios[indiceCiudad1][indiceCiudad2] = -1;
            }
        }
    }

    private void calcularDistancias() {
        int numCiudades = distancias.length;
        for (int k = 0; k < numCiudades; k++) {
            for (int i = 0; i < numCiudades; i++) {
                for (int j = 0; j < numCiudades; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        intermedios[i][j] = k;
                    }
                }
            }
        }
    }

    public int obtenerDistanciaMasCorta(String ciudadOrigen, String ciudadDestino) {
        Integer distancia = distancias[indiceCiudades.get(ciudadOrigen)][indiceCiudades.get(ciudadDestino)];
        return distancia != null ? distancia.intValue() : Integer.MAX_VALUE;
    }

    public List<String> obtenerRuta(String ciudadOrigen, String ciudadDestino) {
        List<String> ruta = new ArrayList<>();
        int origenIndex = indiceCiudades.get(ciudadOrigen);
        int destinoIndex = indiceCiudades.get(ciudadDestino);

        if (distancias[origenIndex][destinoIndex] == Integer.MAX_VALUE / 2) {
            return ruta; // Ruta no disponible
        }

        obtenerRutaRecursiva(origenIndex, destinoIndex, ruta);
        ruta.add(ciudadDestino);
        return ruta;
    }

    private void obtenerRutaRecursiva(int origenIndex, int destinoIndex, List<String> ruta) {
        if (intermedios[origenIndex][destinoIndex] == -1) {
            ruta.add(ciudades.get(origenIndex));
            return;
        }

        int intermediarioIndex = intermedios[origenIndex][destinoIndex];
        obtenerRutaRecursiva(origenIndex, intermediarioIndex, ruta);
        obtenerRutaRecursiva(intermediarioIndex, destinoIndex, ruta);
    }

    public void mostrarMatrizAdyacencia() {
        System.out.println("Matriz de Adyacencia:");
        int numCiudades = distancias.length;
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if (distancias[i][j] == Integer.MAX_VALUE / 2) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distancias[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
