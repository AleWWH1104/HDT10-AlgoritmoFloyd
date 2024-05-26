package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                String linea = scanner.nextLine();
                String[] datos = linea.split(" ");
                if (datos.length == 3) {
                    String ciudad1 = datos[0];
                    String ciudad2 = datos[1];
                    int distancia = Integer.parseInt(datos[2]);
                    agregarConexion(ciudad1, ciudad2, distancia);
                    agregarConexion(ciudad2, ciudad1, distancia); // Considerando el grafo como no dirigido
                } else {
                    System.err.println("Formato incorrecto en línea: " + linea);
                }
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

    public void guardarConexion() {
        try {
            FileWriter writer = new FileWriter(rutaArchivo);
            for (Map.Entry<String, Map<String, Integer>> entry : grafo.entrySet()) {
                String ciudadOrigen = entry.getKey();
                for (Map.Entry<String, Integer> conexion : entry.getValue().entrySet()) {
                    String ciudadDestino = conexion.getKey();
                    int distancia = conexion.getValue();
                    writer.write(ciudadOrigen + " " + ciudadDestino + " " + distancia + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
