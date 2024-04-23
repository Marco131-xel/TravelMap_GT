package archivo;

import java.io.*;
import javax.swing.table.DefaultTableModel;

public class GestionArchivos {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    
    public GestionArchivos(){
        
    }
    
    // Metodo para los archivos
    public String Abrir(File archivo){
        String contenido = "";
        try{
            entrada = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(entrada, "UTF-8"); // Especificar la codificación UTF-8
            BufferedReader br = new BufferedReader(isr);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return contenido;
    }



    // Metodo para guardar el archivo 
    public String Guardar(File archivo, String contenido){
        String respuesta = null;
        try{
            salida = new FileOutputStream(archivo);
            byte [] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se guardo con exito el archivo";
        }catch (Exception e){
            
        }
        return respuesta;
    }
    
}
