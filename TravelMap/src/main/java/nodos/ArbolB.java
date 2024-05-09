package nodos;

public class ArbolB {

    public Nodo raiz;
    private int grado; // Grado del arbol 

    public ArbolB(int grado) {
        this.grado = grado;
        raiz = new Nodo(grado, true);
    }

    // Insertar una llave al arbol B
    public void insertar(int llave, String Noditos) {
        if (raiz == null) {
            raiz = new Nodo(grado, true);
            raiz.llaves.add(llave);
            raiz.Noditos.add(Noditos);
        } else {
            if (raiz.llaves.size() == 2 * grado - 2) {
                Nodo nuevoRaiz = new Nodo(grado, false);
                nuevoRaiz.hijos.add(raiz);
                dividirNodo(nuevoRaiz, 0, raiz);
                raiz = nuevoRaiz;
            }
            insertarNodo(raiz, llave, Noditos);
        }
    }

    // Funcion auxiliar para insertar nodos
    private void insertarNodo(Nodo nodo, int llaves, String Noditos) {
        int i = nodo.llaves.size() - 1;
        if (nodo.hojas) {
            while (i >= 0 && llaves < nodo.llaves.get(i)) {
                i--;
            }
            nodo.llaves.add(i + 1, llaves);
            nodo.Noditos.add(i + 1, Noditos);
        } else {
            while (i >= 0 && llaves < nodo.llaves.get(i)) {
                i--;
            }
            i++;
            Nodo hijo = nodo.hijos.get(i);
            if (hijo.llaves.size() == 2 * grado - 2) {
                dividirNodo(nodo, i, hijo);
                if (llaves > nodo.llaves.get(i)) {
                    i++;
                }
            }
            insertarNodo(nodo.hijos.get(i), llaves, Noditos);
        }
    }
    
    // Funcion para dividir nodos 
    private void dividirNodo(Nodo padre, int indice, Nodo hijo){
        Nodo nuevoNodo = new Nodo(grado,hijo.hojas);
        for (int i = 0; i < grado - 2; i++) {
            nuevoNodo.llaves.add(hijo.llaves.remove(grado-3));
            nuevoNodo.Noditos.add(hijo.Noditos.remove(grado));
        }
        if(!hijo.hojas){
            for (int i = 0; i < grado; i++) {
                nuevoNodo.hijos.add(hijo.hijos.remove(grado));
            }
        }
        padre.llaves.add(indice, hijo.llaves.remove(grado-3));
        padre.Noditos.add(indice,hijo.Noditos.remove(grado-1));
        padre.hijos.add(indice,nuevoNodo);
    }

    // Funcion para imprimir el arbol b
    public void imprimir() {
        imprimirRecur(raiz, "");
    }

    private void imprimirRecur(Nodo nodo, String prefijo) {
        if (nodo != null) {
            System.out.println(prefijo);
            for (int i = 0; i < nodo.llaves.size(); i++) {
                System.out.println("(" + nodo.Noditos.get(i) + nodo.llaves.get(i) + ")");
            }
            System.out.println();
            if (!nodo.hojas) {
                for (Nodo hijo : nodo.hijos) {
                    imprimirRecur(nodo, prefijo + " ");
                }
            }
        }
    }
}
