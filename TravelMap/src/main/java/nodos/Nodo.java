package nodos;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    
    public List<Integer> llaves;
    public List<String> Noditos;
    public List<Nodo> hijos;
    public boolean hojas;
    
    public Nodo(int grado, boolean hojas){
        this.llaves = new ArrayList<>(2* grado -1);
        this.Noditos = new ArrayList<>(2* grado-1);
        this.hijos = new ArrayList<>(2*grado);
        this.hojas = hojas;
    }
}
