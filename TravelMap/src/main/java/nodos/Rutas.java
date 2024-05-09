package nodos;

import datos.Datos;

import java.util.*;

public class Rutas {

    private List<Datos> listaDatos;
    private Set<String> visitados;
    private List<List<Datos>> rutasEncontradas;

    public Rutas(List<Datos> listaDatos) {
        this.listaDatos = listaDatos;
        this.visitados = new HashSet<>();
        this.rutasEncontradas = new ArrayList<>(); // Inicializamos la lista de rutas encontradas
    }

    // Funcion para buscar las rutas posibles 
    public Map<String, List<List<Datos>>> recorreRutas(String origenSeleccionado, String destinoSeleccionado) {
        MiCola<List<Datos>> cola = new MiCola<>();
        Map<String, List<List<Datos>>> todasLasRutas = new HashMap<>(); // Mapa para guardar todas las rutas encontradas

        cola.offer(new ArrayList<>()); // Inicializamos con una lista vacía

        while (!cola.isEmpty()) {
            List<Datos> rutaActual = cola.poll();
            Datos ultimo = rutaActual.isEmpty() ? null : rutaActual.get(rutaActual.size() - 1);
            String lugarActual = (ultimo != null) ? ultimo.getDestino() : origenSeleccionado;

            // Si la última ruta alcanzó el destino, la agregamos a la lista de rutas encontradas
            if (ultimo != null && ultimo.getDestino().equals(destinoSeleccionado)) {
                if (!todasLasRutas.containsKey(destinoSeleccionado)) {
                    todasLasRutas.put(destinoSeleccionado, new ArrayList<>());
                }
                // Almacenar la ruta encontrada
                todasLasRutas.get(destinoSeleccionado).add(rutaActual); // Agregamos la ruta encontrada
            } else {
                // Buscamos todos los destinos posibles desde el último lugar visitado en la ruta actual
                for (Datos dato : listaDatos) {
                    if (dato.getOrigen().equals(lugarActual) && !rutaActual.contains(dato)) {
                        List<Datos> nuevaRuta = new ArrayList<>(rutaActual);
                        nuevaRuta.add(dato);
                        cola.offer(nuevaRuta);
                    }
                }
            }
        }

        return todasLasRutas;
    }

    // Funcion para acumulacion de datos
    public List<Datos> combinarRutaParcial(List<Datos> rutaParcial) {
        String origen = rutaParcial.get(0).getOrigen(); // Origen de la ruta parcial
        String destino = rutaParcial.get(rutaParcial.size() - 1).getDestino(); // Destino de la ruta parcial
        int tiempo_vehiculo_total = 0;
        int tiempo_pie_total = 0;
        int consumo_gas_total = 0;
        int desgaste_persona_total = 0;
        int distancia_total = 0;

        // Sumamos los detalles de cada ruta parcial
        for (Datos dato : rutaParcial) {
            tiempo_vehiculo_total += dato.getTiempo_vehiculo();
            tiempo_pie_total += dato.getTiempo_pie();
            consumo_gas_total += dato.getConsumo_gas();
            desgaste_persona_total += dato.getDesgaste_persona();
            distancia_total += dato.getDistancia();
        }

        // Crear una lista para almacenar el objeto Datos combinado
        List<Datos> rutaCombinada = new ArrayList<>();
        rutaCombinada.add(new Datos(origen, destino, tiempo_vehiculo_total, tiempo_pie_total, consumo_gas_total, desgaste_persona_total, distancia_total));

        return rutaCombinada;
    }

    // Funcion para tener mis rutas
    public List<Datos> obtenerRutas() {
        List<Datos> rutasCompletas = new ArrayList<>();
        for (List<Datos> rutaParcial : rutasEncontradas) {
            for (Datos dato : rutaParcial) {
                rutasCompletas.add(dato);
            }
        }
        return rutasCompletas;
    }

    // Metodo para obtener las rutas encontradas
    public List<List<Datos>> obtenerRutasEncontradas() {
        return this.rutasEncontradas;
    }

    // Método para recorrer rutas y almacenarlas
    public void encontrarRutas(String origenSeleccionado, String destinoSeleccionado) {
        Map<String, List<List<Datos>>> todasLasRutas = recorreRutas(origenSeleccionado, destinoSeleccionado);
        rutasEncontradas = new ArrayList<>(); // Inicializar la lista de rutas encontradas

        for (List<List<Datos>> rutas : todasLasRutas.values()) {
            rutasEncontradas.addAll(rutas);
        }
    }

    // Funcion para menor desgaste
    public List<Datos> menorDesgaste() {
        List<Datos> mejorRuta = null;
        int menorDesgaste = Integer.MAX_VALUE;

        for (List<Datos> ruta : rutasEncontradas) {
            List<Datos> rutaCombinada = combinarRutaParcial(ruta);
            int desgaste = rutaCombinada.get(0).getDesgaste_persona();
            if (desgaste < menorDesgaste) {
                menorDesgaste = desgaste;
                mejorRuta = rutaCombinada;
            }
        }

        return mejorRuta;
    }

    // Funcion para menor distancia 
    public List<Datos> menorDistancia() {
        List<Datos> mejorRuta = null;
        int menorDista = Integer.MAX_VALUE;

        for (List<Datos> ruta : rutasEncontradas) {
            List<Datos> rutaCombinada = combinarRutaParcial(ruta);
            int distancia = rutaCombinada.get(0).getDistancia();
            if (distancia < menorDista) {
                menorDista = distancia;
                mejorRuta = rutaCombinada;
            }
        }
        return mejorRuta;
    }

    // Funcion para menor combustible
    public List<Datos> menorCombustible() {
        List<Datos> mejorRuta = null;
        int menorCombustible = Integer.MAX_VALUE;

        for (List<Datos> ruta : rutasEncontradas) {
            List<Datos> rutaCombinada = combinarRutaParcial(ruta);
            int combustible = rutaCombinada.get(0).getConsumo_gas();
            if (combustible < menorCombustible) {
                menorCombustible = combustible;
                mejorRuta = rutaCombinada;
            }
        }
        return mejorRuta;
    }

    // Funcion para menor distancia y degaste
    public List<Datos> menorDistanciaYDesgaste() {
        List<Datos> mejorRuta = null;
        int menorDesgaste = Integer.MAX_VALUE;
        int menorDistancia = Integer.MAX_VALUE;

        for (List<Datos> ruta : rutasEncontradas) {
            List<Datos> rutaCombinada = combinarRutaParcial(ruta);
            int desgaste = rutaCombinada.get(0).getDesgaste_persona();
            int distancia = rutaCombinada.get(0).getDistancia();
            if (desgaste < menorDesgaste || (desgaste == menorDesgaste && distancia < menorDistancia)) {
                menorDesgaste = desgaste;
                menorDistancia = distancia;
                mejorRuta = rutaCombinada;
            }
        }

        return mejorRuta;
    }

    public List<Datos> menorDistanciaYCombustible() {
        List<Datos> mejorRuta = null;
        int menorCombustible = Integer.MAX_VALUE; 
        int menorDistancia = Integer.MAX_VALUE; 

        for (List<Datos> ruta : rutasEncontradas) {
            List<Datos> rutaCombinada = combinarRutaParcial(ruta);
            int combustible = rutaCombinada.get(0).getConsumo_gas();
            int distancia = rutaCombinada.get(0).getDistancia();
            if (combustible < menorCombustible || (combustible == menorCombustible && distancia < menorDistancia)) {
                menorCombustible = combustible;
                menorDistancia = distancia;
                mejorRuta = rutaCombinada;
            }
        }

        return mejorRuta;
    }

    // Funcion Cola
    public class MiCola<T> {

        private class Nodo {

            T dato;
            Nodo siguiente;

            Nodo(T dato) {
                this.dato = dato;
                this.siguiente = null;
            }
        }

        private Nodo frente;
        private Nodo fin;

        public MiCola() {
            this.frente = null;
            this.fin = null;
        }

        public void offer(T dato) {
            Nodo nuevoNodo = new Nodo(dato);
            if (isEmpty()) {
                frente = nuevoNodo;
                fin = nuevoNodo;
            } else {
                fin.siguiente = nuevoNodo;
                fin = nuevoNodo;
            }
        }

        public T poll() {
            if (isEmpty()) {
                return null;
            }
            T dato = frente.dato;
            frente = frente.siguiente;
            if (frente == null) {
                fin = null;
            }
            return dato;
        }

        public boolean isEmpty() {
            return frente == null;
        }
    }

}
