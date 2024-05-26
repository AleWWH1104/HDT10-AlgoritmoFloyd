package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GrafoTest {
    @Test
    public void testAgregarConexion() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("CiudadA", "CiudadB", 50);
        grafo.agregarConexion("CiudadB", "CiudadC", 100);

        Map<String, Map<String, Integer>> conexiones = grafo.getConexiones();

        Assertions.assertTrue(conexiones.containsKey("CiudadA"));
        Assertions.assertTrue(conexiones.containsKey("CiudadB"));
        Assertions.assertTrue(conexiones.containsKey("CiudadC"));

        Assertions.assertEquals(50, conexiones.get("CiudadA").get("CiudadB"));
        Assertions.assertEquals(50, conexiones.get("CiudadB").get("CiudadA"));
        Assertions.assertEquals(100, conexiones.get("CiudadB").get("CiudadC"));
        Assertions.assertEquals(100, conexiones.get("CiudadC").get("CiudadB"));
    }

    @Test
    public void testEliminarConexion() {
    Grafo grafo = new Grafo();
    grafo.agregarConexion("CiudadA", "CiudadB", 50);
    grafo.agregarConexion("CiudadB", "CiudadC", 100);

    grafo.eliminarConexion("CiudadA", "CiudadB");

    Map<String, Map<String, Integer>> conexiones = grafo.getConexiones();

    Assertions.assertTrue(conexiones.containsKey("CiudadA"));
    Assertions.assertTrue(conexiones.containsKey("CiudadB"));
    Assertions.assertTrue(conexiones.containsKey("CiudadC"));

    Assertions.assertNull(conexiones.get("CiudadA").get("CiudadB"));
    Assertions.assertNotNull(conexiones.get("CiudadB").get("CiudadA")); // Verifica que la conexión inversa todavía exista
    Assertions.assertEquals(100, conexiones.get("CiudadB").get("CiudadC"));
    Assertions.assertEquals(100, conexiones.get("CiudadC").get("CiudadB"));
}

}

