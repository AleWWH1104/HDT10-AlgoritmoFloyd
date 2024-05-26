package src.main.java.com.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileManager {
    
    public static Grafo leerArchivo(String archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        Set<String> conjuntoCiudades = new HashSet<>();

        List<String[]> datos = new ArrayList<>();
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(" ");
            conjuntoCiudades.add(partes[0]);
            conjuntoCiudades.add(partes[1]);
            datos.add(partes);
        }
        br.close();

        return new Grafo(conjuntoCiudades, datos);
    }
}



