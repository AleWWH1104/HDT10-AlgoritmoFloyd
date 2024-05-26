package com.example;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ejecutarPrograma();
    }

    public static void ejecutarPrograma() {
        FileManager fileManager = new FileManager("guategrafo.txt");
        fileManager.leerArchivo();
        Grafo grafo = new Grafo();
        grafo.setConexiones(fileManager.obtenerGrafo());

        Floyd floyd = new Floyd(grafo.getConexiones());

        Scanner scanner = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("Opciones:");
            System.out.println("1. Calcular ruta más corta entre ciudades.");
            System.out.println("2. Encontrar ciudad centro.");
            System.out.println("3. Modificar grafo.");
            System.out.println("4. Salir.");
            System.out.println("Nota:los nombres de ciudades deben ir tal como en el txt.");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
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
                case "2":
                    String ciudadCentro = new CentroGrafo(floyd.distancias, floyd.indiceCiudades).encontrarCiudadCentro();
                    System.out.println("La ciudad centro es: " + ciudadCentro);
                    break;
                case "3":
                    App app = new App();
                    System.out.println("1.Agregar\n2.Eliminar");
                    String op2 = scanner.nextLine();
                    if (op2.equals("1")){
                        app.agregarNodo(scanner, grafo, fileManager);
                    }else if(op2.equals("2")){
                        app.eliminarNodo(scanner, grafo, fileManager);
                    }else{ System.out.println("Algo valido please");}
                    // Recargar el programa
                    ejecutarPrograma();
                    return;
                case "4":
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (!opcion.equals("4"));
        scanner.close();
    }

    private void agregarNodo(Scanner scanner, Grafo grafo, FileManager fileManager){
        System.out.println("Agrega nueva ruta");
        System.out.print("Ciudad origen: ");
        String ciud1 = scanner.nextLine();
        System.out.print("Ciudad destino: ");
        String ciud2 = scanner.nextLine();
        System.out.print("Distancia en INT: ");
        int dist = scanner.nextInt();
        scanner.nextLine();
        grafo.agregarConexion(ciud1, ciud2, dist);
        fileManager.guardarConexion();
    }

    private void eliminarNodo(Scanner scanner, Grafo grafo, FileManager fileManager){
        System.out.print("Ciudad origen: ");
        String origenEliminar = scanner.nextLine();
        System.out.print("Ciudad destino: ");
        String destinoEliminar = scanner.nextLine();
        grafo.eliminarConexion(origenEliminar, destinoEliminar);
        fileManager.guardarConexion(); // Guardar el grafo actualizado en el archivo
        System.out.println("Ruta eliminada correctamente.");
    }
}
