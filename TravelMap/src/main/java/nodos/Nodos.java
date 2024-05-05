package nodos;

import datos.Datos;

import java.util.*;

public class Nodos {
    //Lista para la almacenar realciones directas
    private List<Relacion> relaciones;

    // Constructor
    public Nodos(List<Datos> listaDatos) {
        this.relaciones = new ArrayList<>();

        // Agregar realciones directas a partir de los datos de listaDatos
        for (Datos datos : listaDatos) {
            String origen = datos.getOrigen();
            String destino = datos.getDestino();

            relaciones.add(new Relacion(origen, destino));
        }
    }

    // Funcion para verificar si dos nodos tienen una relacion directa
    public boolean rutasDirecta(String origen, String destino) {
        for (Relacion relacion : relaciones) {
            if (relacion.getRorigen().equals(origen) && relacion.getRdestino().equals(destino)) {
                return true;
            }
        }
        return false;
    }

    // Funcion pra obtener los detalles de una ruta directa
    public Datos detallesRutaDirecta(List<Datos> listaDatos, String origen, String destino) {
        // Si se encuetra la relacion directa buscar detalles
        for (Datos datos : listaDatos) {
            if (datos.getOrigen().equals(origen) && datos.getDestino().equals(destino)) {
                return datos;
            }
        }
        // Si no se encuentra la relacion directa
        return null;
    }

    // Funcion pra encontrar rutas indirectas entre dos nodos
    public List<List<Datos>> rutaIndirecta(List<Datos> listaDatos, String origen, String destino) {
        List<List<Datos>> rutas = new ArrayList<>();
        Set<String> visitados = new HashSet<>();
        List<Datos> rutaActual = new ArrayList<>();
        rutaIndirectaDFS(listaDatos, origen, destino, visitados, rutaActual, rutas);
        return rutas;
    }

    private void rutaIndirectaDFS(List<Datos> listaDatos, String actual, String destino, Set<String> visitados, List<Datos> rutaActual, List<List<Datos>> rutas) {
        visitados.add(actual);
        rutaActual.add(new Datos(actual, actual, 0, 0, 0, 0, 0)); // Añadir nodo actual a la ruta

        if (actual.equals(destino)) {
            // Si hemos llegado al destino, añadir la ruta actual a la lista de rutas
            rutas.add(new ArrayList<>(rutaActual));
        } else {
            // Explorar los nodos adyacentes no visitados
            for (Datos datos : listaDatos) {
                if (datos.getOrigen().equals(actual) && !visitados.contains(datos.getDestino())) {
                    // Calcular los valores acumulados para la ruta actual
                    Datos datosRuta = new Datos(datos.getOrigen(), datos.getDestino(), 0, 0, 0, 0, 0); // Inicializar con valores en 0
                    Datos datosAnteriores = rutaActual.get(rutaActual.size() - 1);

                    // Agregar los valores acumulados de la ruta anterior
                    datosRuta.setTiempo_vehiculo(datos.getTiempo_vehiculo() + datosAnteriores.getTiempo_vehiculo());
                    datosRuta.setTiempo_pie(datos.getTiempo_pie() + datosAnteriores.getTiempo_pie());
                    datosRuta.setConsumo_gas(datos.getConsumo_gas() + datosAnteriores.getConsumo_gas());
                    datosRuta.setDesgaste_persona(datos.getDesgaste_persona() + datosAnteriores.getDesgaste_persona());
                    datosRuta.setDistancia(datos.getDistancia() + datosAnteriores.getDistancia());

                    rutaIndirectaDFS(listaDatos, datos.getDestino(), destino, visitados, new ArrayList<>(rutaActual), rutas); // Pasar una copia de la ruta actual para no modificarla
                }
            }
        }

        // Retroceder: eliminar el nodo actual de la ruta y marcar como no visitado
        visitados.remove(actual);
    }

    // Clase para representar una relacion entre nodos
    private static class Relacion {
        private String rorigen;
        private String rdestino;

        public Relacion(String rorigen, String rdestino) {
            this.rorigen = rorigen;
            this.rdestino = rdestino;
        }

        public String getRorigen() {
            return rorigen;
        }

        public String getRdestino() {
            return rdestino;
        }
    }
}