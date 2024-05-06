package nodos;

import datos.Datos;

import java.util.*;

public class Rutas {
    //Lista para la almacenar realciones directas
    private List<Relacion> relaciones;

    // Constructor
    public Rutas(List<Datos> listaDatos) {
        this.relaciones = new ArrayList<>();

        // Agregar realciones directas a partir de los datos de listaDatos
        for (Datos datos : listaDatos) {
            String origen = datos.getOrigen();
            String destino = datos.getDestino();

            relaciones.add(new Relacion(origen, destino));
        }
    }

    // Funcion pra obtener los detalles de una ruta directa
    public Datos rutaDirecta(List<Datos> listaDatos, String origen, String destino) {
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
    public List<List<Datos>> rutaIndirectas(List<Datos> listaDatos, String origen, String destino) {
        List<List<Datos>> rutasIndirectas = new ArrayList<>(); // Lista de listas de Datos
        Set<String> visitados = new HashSet<>();
        List<Datos> rutaActual = new ArrayList<>();
        rutaIndirectaDFS(listaDatos, origen, destino, visitados, rutaActual, rutasIndirectas);
        return rutasIndirectas;
    }

    private void rutaIndirectaDFS(List<Datos> listaDatos, String actual, String destino, Set<String> visitados, List<Datos> rutaActual, List<List<Datos>> rutasIndirectas) {
        visitados.add(actual);
        rutaActual.add(new Datos(actual, actual, 0, 0, 0, 0, 0)); // Añadir nodo actual a la ruta

        if (actual.equals(destino)) {
            // Crear una nueva lista para almacenar la ruta actual
            List<Datos> nuevaRuta = new ArrayList<>();
            // Agregar una copia de cada objeto Datos de rutaActual a nuevaRuta
            for (Datos dato : rutaActual) {
                nuevaRuta.add(new Datos(dato.getOrigen(), dato.getDestino(), dato.getTiempo_vehiculo(), dato.getTiempo_pie(), dato.getConsumo_gas(), dato.getDesgaste_persona(), dato.getDistancia()));
            }
            // Agregar nuevaRuta a rutasIndirectas
            rutasIndirectas.add(nuevaRuta);
        } else {
            for (Datos datos : listaDatos) {
                if (datos.getOrigen().equals(actual) && !visitados.contains(datos.getDestino())) {
                    Datos datosRuta = new Datos(datos.getOrigen(), datos.getDestino(), datos.getTiempo_vehiculo(), datos.getTiempo_pie(),
                            datos.getConsumo_gas(), datos.getDesgaste_persona(), datos.getDistancia());
                    Datos datosAnteriores = rutaActual.get(rutaActual.size() - 1);
                    datosAnteriores.setTiempo_vehiculo(datos.getTiempo_vehiculo() + datosAnteriores.getTiempo_vehiculo());
                    datosAnteriores.setTiempo_pie(datos.getTiempo_pie() + datosAnteriores.getTiempo_pie());
                    datosAnteriores.setConsumo_gas(datos.getConsumo_gas() + datosAnteriores.getConsumo_gas());
                    datosAnteriores.setDesgaste_persona(datos.getDesgaste_persona() + datosAnteriores.getDesgaste_persona());
                    datosAnteriores.setDistancia(datos.getDistancia() + datosAnteriores.getDistancia());
                    rutaIndirectaDFS(listaDatos, datos.getDestino(), destino, visitados, new ArrayList<>(rutaActual), rutasIndirectas);
                }
            }
        }
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