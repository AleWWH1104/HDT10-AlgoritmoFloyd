package com.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;

public class FloydTest {
    @Test
    public void testObtenerDistanciaMasCorta() {
        // Crear un grafo con conexiones
        Grafo grafo = new Grafo();
        grafo.agregarConexion("CiudadA", "CiudadB", 50);
        grafo.agregarConexion("CiudadB", "CiudadC", 100);
        
        Floyd floyd = new Floyd(grafo.getConexiones());
        
        // Verificar distancia más corta entre CiudadA y CiudadC
        Assertions.assertEquals(150, floyd.obtenerDistanciaMasCorta("CiudadA", "CiudadC"));
    }
    
    @Test
    public void testObtenerRuta() {
        // Crear un grafo con conexiones
        Grafo grafo = new Grafo();
        grafo.agregarConexion("CiudadA", "CiudadB", 50);
        grafo.agregarConexion("CiudadB", "CiudadC", 100);
        
        Floyd floyd = new Floyd(grafo.getConexiones());
        
        // Verificar la ruta más corta entre CiudadA y CiudadC
        List<String> rutaEsperada = Arrays.asList("CiudadA", "CiudadB", "CiudadC");
        Assertions.assertEquals(rutaEsperada, floyd.obtenerRuta("CiudadA", "CiudadC"));
    }
}
