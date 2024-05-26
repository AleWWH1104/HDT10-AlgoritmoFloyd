package src.main.java.com.example;
import java.util.*;

public class alg_Floyd{
    private int[][] distancias;
    private String[][] caminos;
    private int numCiudades;

    public alg_Floyd(Grafo grafo) {
        this.distancias = grafo.getDistancias();
        this.caminos = grafo.getCaminos();
        this.numCiudades = distancias.length;
    }

    public void calcularRutas() {
        for (int k = 0; k < numCiudades; k++) {
            for (int i = 0; i < numCiudades; i++) {
                for (int j = 0; j < numCiudades; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        caminos[i][j] = caminos[i][k];
                    }
                }
            }
        }
    }

    public String rutaMasCorta(Grafo grafo, String origen, String destino) {
        Map<String, Integer> ciudades = grafo.getCiudades();
        String[] indiceCiudades = grafo.getIndiceCiudades();

        if (!ciudades.containsKey(origen) || !ciudades.containsKey(destino)) {
            return "Una de las ciudades no existe en el grafo.";
        }

        int desde = ciudades.get(origen);
        int hacia = ciudades.get(destino);

        if (distancias[desde][hacia] == Integer.MAX_VALUE / 2) {
            return "No hay ruta disponible entre " + origen + " y " + destino;
        }

        List<String> ruta = new ArrayList<>();
        ruta.add(origen);
        while (!origen.equals(destino)) {
            origen = caminos[ciudades.get(origen)][ciudades.get(destino)];
            ruta.add(origen);
        }

        return "La ruta más corta desde " + indiceCiudades[desde] + " a " + indiceCiudades[hacia] + " es " + distancias[desde][hacia] + " KM, pasando por: " + String.join(" -> ", ruta);
    }
}
