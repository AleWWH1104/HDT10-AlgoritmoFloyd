# HDT10-AlgoritmoFloyd
Autora: Iris Ayala

## Rutas Guatemala - Programa de cortas Distancias en Ciudades

### Descripción
El programa es una herramienta de análisis de distancias entre ciudades basada en grafos dirigidos. Su funcionalidad principal es leer un archivo de texto que representa las conexiones entre ciudades y calcular la distancia más corta entre cualquier par de ciudades utilizando el algoritmo de Floyd. Además, permite realizar modificaciones en el grafo, como eliminaciones de ruta o establecimiento de nuevas conexiones, y recalcula las rutas más cortas y el centro del grafo.

### Funcionamiento
1. **Lectura del Archivo**
   - El programa lee un archivo de texto llamado `guategrafo.txt`, donde cada línea representa una conexión entre dos ciudades y la distancia en kilómetros entre ellas.
   
2. **Construcción del Grafo**
   - Con la información del archivo, se construye un grafo dirigido donde las ciudades son los nodos y las conexiones representan las aristas, con sus respectivas distancias.

3. **Algoritmo de Floyd**
   - Se aplica el algoritmo de Floyd para calcular la distancia más corta entre cualquier par de ciudades en el grafo.

4. **Opciones del Programa**
   - El programa presenta las siguientes opciones:
     1. Calcular la ruta más corta entre dos ciudades.
     2. Identificar la ciudad central del grafo.
     3. Modificar el grafo (interrupción de tráfico o nueva conexión) y recalcular rutas y centro.
     4. Finalizar el programa.

5. **Representación del Grafo**
   - Se muestra la matriz de adyacencia que representa el grafo, o cualquier otra representación utilizada en la implementación.

### Ejecución
- Se inicia el programa en el archivo App.java, se presentan las opciones mencionadas anteriormente en un bucle hasta que se elija la opción de finalizar.

