package com.example;
import java.util.*;

public class Grafo {
    private Map<String, Map<String, Integer>> conexiones;
    private List<String> ciudades;

    public Grafo() {
        conexiones = new HashMap<>();
        ciudades = new ArrayList<>();
    }

    public void agregarConexion(String ciudad1, String ciudad2, int distancia) {
        agregarCiudad(ciudad1);
        agregarCiudad(ciudad2);

        conexiones.computeIfAbsent(ciudad1, k -> new HashMap<>()).put(ciudad2, distancia);
        conexiones.computeIfAbsent(ciudad2, k -> new HashMap<>()).put(ciudad1, distancia);
    }

    public void eliminarConexion(String ciudadOrigen, String ciudadDestino) {
        if (conexiones.containsKey(ciudadOrigen) && conexiones.get(ciudadOrigen).containsKey(ciudadDestino)) {
            conexiones.get(ciudadOrigen).remove(ciudadDestino);
        } else {
            System.out.println("La conexión especificada no existe en el grafo.");
        }
    }
    
    private void agregarCiudad(String ciudad) {
        if (!ciudades.contains(ciudad)) {
            ciudades.add(ciudad);
        }
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public int obtenerDistancia(String ciudad1, String ciudad2) {
        if (conexiones.containsKey(ciudad1) && conexiones.get(ciudad1).containsKey(ciudad2)) {
            return conexiones.get(ciudad1).get(ciudad2);
        } else {
            return Integer.MAX_VALUE; // Distancia infinita si no hay conexión directa
        }
    }

    public Map<String, Map<String, Integer>> getConexiones() {
        return conexiones;
    }

    public void setConexiones(Map<String, Map<String, Integer>> conexiones) {
        this.conexiones = conexiones;
        for (String ciudad1 : conexiones.keySet()) {
            for (String ciudad2 : conexiones.get(ciudad1).keySet()) {
                agregarCiudad(ciudad1);
                agregarCiudad(ciudad2);
            }
        }
    }
    
}
