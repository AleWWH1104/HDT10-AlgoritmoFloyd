package src.main.java.com.example;
import java.util.*;

public class Grafo {
    private int[][] distancias;
    private String[][] caminos;
    private Map<String, Integer> ciudades;
    private String[] indiceCiudades;
    private int numCiudades;
    private static final int INF = Integer.MAX_VALUE / 2;

    public Grafo(Set<String> conjuntoCiudades, List<String[]> datos) {
        numCiudades = conjuntoCiudades.size();
        ciudades = new HashMap<>();
        indiceCiudades = new String[numCiudades];

        int index = 0;
        for (String ciudad : conjuntoCiudades) {
            ciudades.put(ciudad, index);
            indiceCiudades[index] = ciudad;
            index++;
        }

        distancias = new int[numCiudades][numCiudades];
        caminos = new String[numCiudades][numCiudades];

        for (int i = 0; i < numCiudades; i++) {
            Arrays.fill(distancias[i], INF);
            distancias[i][i] = 0;
        }

        for (String[] datosLinea : datos) {
            int desde = ciudades.get(datosLinea[0]);
            int hacia = ciudades.get(datosLinea[1]);
            int distancia = Integer.parseInt(datosLinea[2]);
            distancias[desde][hacia] = distancia;
            caminos[desde][hacia] = datosLinea[1];
        }
    }

    public int[][] getDistancias() {
        return distancias;
    }

    public String[][] getCaminos() {
        return caminos;
    }

    public String[] getIndiceCiudades() {
        return indiceCiudades;
    }

    public Map<String, Integer> getCiudades() {
        return ciudades;
    }

    public void actualizarGrafo(String ciudad1, String ciudad2, int nuevaDistancia) {
        int desde = ciudades.get(ciudad1);
        int hacia = ciudades.get(ciudad2);

        distancias[desde][hacia] = nuevaDistancia;
        caminos[desde][hacia] = ciudad2;
    }

    public void eliminarArco(String ciudad1, String ciudad2) {
        int desde = ciudades.get(ciudad1);
        int hacia = ciudades.get(ciudad2);

        distancias[desde][hacia] = INF;
        caminos[desde][hacia] = null;
    }

    public void imprimirMatriz() {
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if (distancias[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distancias[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

