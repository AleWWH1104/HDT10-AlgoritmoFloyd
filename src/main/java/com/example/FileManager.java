package src.main.java.com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
    private final String rutaArchivo;
    private Map<String, Map<String, Integer>> grafo;

    public FileManager(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.grafo = new HashMap<>();
    }

    public void leerArchivo() {
        try {
            Scanner scanner = new Scanner(new File(rutaArchivo));
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(" ");
                String ciudad1 = datos[0];
                String ciudad2 = datos[1];
                int distancia = Integer.parseInt(datos[2]);
                agregarConexion(ciudad1, ciudad2, distancia);
                agregarConexion(ciudad2, ciudad1, distancia); // Considerando el grafo como no dirigido
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void agregarConexion(String ciudad1, String ciudad2, int distancia) {
        if (!grafo.containsKey(ciudad1)) {
            grafo.put(ciudad1, new HashMap<>());
        }
        grafo.get(ciudad1).put(ciudad2, distancia);
    }

    public Map<String, Map<String, Integer>> obtenerGrafo() {
        return grafo;
    }
}
