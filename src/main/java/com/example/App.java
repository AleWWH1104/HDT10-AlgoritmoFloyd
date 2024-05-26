package src.main.java.com.example;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager("guategrafo.txt");
        fileManager.leerArchivo();
        Grafo grafo = new Grafo();
        grafo.setConexiones(fileManager.obtenerGrafo());

        Floyd floyd = new Floyd(grafo.getConexiones());

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Opciones:");
            System.out.println("1. Calcular ruta más corta entre ciudades.");
            System.out.println("2. Encontrar ciudad centro.");
            System.out.println("3. Modificar grafo.");
            System.out.println("4. Salir.");
            System.out.println("Nota:los nombres de ciudades deben ir tal como en el txt.");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingrese ciudad destino: ");
                    String destino = scanner.nextLine();
                    int distancia = floyd.obtenerDistanciaMasCorta(origen, destino);
                    System.out.println("La distancia más corta es: " + distancia + " KM");
                    List<String> ruta = floyd.obtenerRuta(origen,destino);
                    System.out.println("Ruta:" + ruta);
                    System.out.println("Matriz de adyacencia");
                    floyd.mostrarMatrizAdyacencia();
                    break;
                case 2:
                    String ciudadCentro = new CentroGrafo(floyd.distancias, floyd.indiceCiudades).encontrarCiudadCentro();
                    System.out.println("La ciudad centro es: " + ciudadCentro);
                    break;
                case 3:
                    
                case 4:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 4);
        scanner.close();
    }
}
