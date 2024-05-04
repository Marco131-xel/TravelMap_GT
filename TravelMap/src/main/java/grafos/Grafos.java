package grafos;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
            // Leer el archivo dot
            File dotFile = new File("grafo.dot");
            FileReader fileReader = new FileReader(dotFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            StringBuilder dotContent = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                dotContent.append(line).append("\n");
            }
            bufferedReader.close();
            // Obtener el contenido del archivo DOT como un arreglo de líneas
            String[] dotLines = dotContent.toString().split("\\r?\\n");
            StringBuilder newDotContent = new StringBuilder();

            // Recorrer cada línea del archivo DOT
            for (String dotLine : dotLines) {
                // Agregar la línea al nuevo contenido
                newDotContent.append(dotLine).append("\n");

                // Verificar si la línea contiene la relación entre oriSelect y destiSelect
                if (dotLine.contains("\"" + oriSelect + "\" -> \"" + destiSelect + "\"")) {
                    // Agregar las líneas para establecer el color de oriSelect y destiSelect
                    newDotContent.append("\"" + oriSelect + "\" [fillcolor=\"red\"];");
                    newDotContent.append("\"" + destiSelect + "\" [fillcolor=\"red\"];\n");
                }
            }

            // Escribir el contenido modificado en el archivo dot
            FileWriter fileWriter = new FileWriter(dotFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newDotContent.toString());
            bufferedWriter.close();

            // Llamar a Graphviz para regenerar la imagen del grafo
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o", "grafo.png", "grafo.dot");
            Process p = pb.start();
            p.waitFor();
            System.out.println("Grafo actualizado corectamente");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
