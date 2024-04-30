package grafos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Grafos {

    public void generarGrafo(String contenido) {
        try {
            // Generar un archivo dot con los datos 
            FileWriter writer = new FileWriter("grafo.dot");
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("digraph G {\n");
            buffer.write("\tgraph [ratio=0.5];\n");
            buffer.write("\tnode [shape=circle, style=filled, fillcolor=\"#009900\", fontcolor=\"#FFFFCC\", fixedsize=false];\n");
            buffer.write("\tedge [color=\"#8B4513\"];\n");

            //Agrega las conexiones entre los nodos al archivo dot
            String[] lineas = contenido.split("\n");
            for (String linea : lineas) {
                String[] partes = linea.split("\\|");
                buffer.write("  \"" + partes[0] + "\" -> \"" + partes[1] + "\" [label=\"" + partes[6] + "\"];\n");
            }

            buffer.write("}\n");
            buffer.close();

            // Llama a Graphviz para generar la imagen del grafico 
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o", "grafo.png", "grafo.dot");
            Process p = pb.start();
            p.waitFor();
            System.out.println("Grafo generado correctamente");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selecGrafos(String oriSelect, String destiSelect) {
        try {
            // Abrir el archivo dot
            File dotFile = new File("grafo.dot");
            BufferedReader reader = new BufferedReader(new FileReader(dotFile));
            StringBuilder strinBu = new StringBuilder();
            String line;

            // Leer el archivo línea por línea
            while ((line = reader.readLine()) != null) {
                // Modificar las líneas que definen los nodos para cambiar los colores
                if (line.contains("node [shape=circle")) {
                    if (line.contains(oriSelect)) {
                        line = line.replace("fillcolor=\"#009900\"", "fillcolor=\"#FF0000\""); // Cambiar a rojo
                    } else if (line.contains(destiSelect)) {
                        line = line.replace("fillcolor=\"#009900\"", "fillcolor=\"#0000FF\""); // Cambiar a azul
                    }
                }
                // Agregar la línea al nuevo contenido
                strinBu.append(line).append("\n");
            }
            reader.close();
            // Escribir el nuevo contenido en el archivo dot
            FileWriter writer = new FileWriter(dotFile);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(strinBu.toString());
            buffer.close();

            // Generar la nueva imagen del grafo
            nuevaImagenDot("grafo.dot", "grafo.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nuevoGrafo(String contenido) {
        // Generar el nuevo archivo dot
        nuevoDot(contenido);
        // Generar el archivo de imagen 
        nuevaImagenDot("grafo.dot", "grafo.png");
    }

    private void nuevoDot(String contenido) {
        try {
            // Escribir el contenido en el archivo dot
            FileWriter writer = new FileWriter("grafo.dot");
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(contenido);
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nuevaImagenDot(String inputDot, String outImage) {
        try {
            // Ejecutar el comando para convetir el archivo dot en una imagen 
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", inputDot, "-o", outImage);
            Process p = pb.start();

            // esperar a que el proceso termine
            p.waitFor();
            // Verificar si ocurrio algun error
            if (p.exitValue() != 0) {
                // Ocurrio un error durante la ejecucion del comando 
                System.out.println("Error al genera nueva imagen");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
