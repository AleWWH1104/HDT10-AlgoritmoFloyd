package src.main.java.com.example;

public class alg_CentroGrafo {
    public static String calcularCentro(Grafo grafo) {
        int[][] distancias = grafo.getDistancias();
        String[] indiceCiudades = grafo.getIndiceCiudades();
        int numCiudades = distancias.length;

        int[] excen = new int[numCiudades];
        for (int i = 0; i < numCiudades; i++) {
            excen[i] = 0;
            for (int j = 0; j < numCiudades; j++) {
                if (i != j) {
                    excen[i] = Math.max(excen[i], distancias[i][j]);
                }
            }
        }

        int minExcen = Integer.MAX_VALUE / 2;
        int centro = -1;
        for (int i = 0; i < numCiudades; i++) {
            if (excen[i] < minExcen) {
                minExcen = excen[i];
                centro = i;
            }
        }

        return "El centro del grafo es " + indiceCiudades[centro];
    }
}