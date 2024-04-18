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
            int afk;
            while((afk = entrada.read()) !=1){
                char caracter = (char)afk;
                contenido += caracter;
            }
        }catch (Exception e){
           
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
