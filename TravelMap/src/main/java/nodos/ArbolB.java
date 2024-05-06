package nodos;
import java.util.ArrayList;
import java.util.List;

public class ArbolB {
    private NodoB raiz;
    private int gradoMaximo;

    // Constructor
    public ArbolB(int gradoMaximo) {
        this.raiz = null;
        this.gradoMaximo = gradoMaximo;
    }

    // Método para agregar una lista de nodos al árbol
    public void agregarListaNodos(List<Rutas> listaNodos) {
        if (listaNodos.size() > gradoMaximo - 1) {
            // Si la lista es demasiado grande para el grado máximo, dividirla en sub-listas
            List<List<Rutas>> subListas = dividirLista(listaNodos);

            // Crear nodos intermedios y agregar sub-listas como hijos
            NodoB nodoIntermedio = new NodoB();
            nodoIntermedio.setHijos(new ArrayList<>());
            for (List<Rutas> subLista : subListas) {
                NodoB hijo = new NodoB();
                hijo.setListaNodos(subLista);
                nodoIntermedio.getHijos().add(hijo);
            }

            // Actualizar la raiz si es necesario
            if (raiz == null) {
                raiz = nodoIntermedio;
            } else {

            }
        } else {
            if (raiz == null) {
                raiz = new NodoB();
            }
            raiz.setListaNodos(listaNodos);
        }
    }
    private List<List<Rutas>> dividirLista(List<Rutas> lista) {
        List<List<Rutas>> subListas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i += gradoMaximo - 1) {
            subListas.add(lista.subList(i, Math.min(i + gradoMaximo - 1, lista.size())));
        }
        return subListas;
    }

    private static class NodoB {
        private List<NodoB> hijos; // Lista de nodos hijos (nodos intermedios)
        private List<Rutas> listaNodos; // Lista de nodos (nodos hoja)

        // Getters y setters
        public List<NodoB> getHijos() {
            return hijos;
        }

        public void setHijos(List<NodoB> hijos) {
            this.hijos = hijos;
        }

        public List<Rutas> getListaNodos() {
            return listaNodos;
        }

        public void setListaNodos(List<Rutas> listaNodos) {
            this.listaNodos = listaNodos;
        }
    }
}
