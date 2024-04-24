package grafos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Grafos {
    public void generarGrafo(String contenido){
        try{
            // Generar un archivo dot con los datos 
            FileWriter  writer = new FileWriter("grafo.dot");
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("digraph G {\n" + "graph [ratio=0.5];\n");
            
            //Agrega las conexiones entre los nodos al archivo dot
            String[] lineas = contenido.split("\n");
            for(String linea : lineas){
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
        } catch(IOException |InterruptedException e){
            e.printStackTrace();
        }
    }
}