package datos;

public class Datos {

    private String origen;
    private String destino;
    private int tiempo_vehiculo;
    private int tiempo_pie;
    private int consumo_gas;
    private int desgaste_persona;
    private int distancia;

    // Constructor vacio
    public Datos() {

    }

    // Constructor
    public Datos(String origen, String destino, int tiempo_vehiculo,
            int tiempo_pie, int consumo_gas, int desgaste_persona, int distancia) {
        this.origen = origen;
        this.destino = destino;
        this.tiempo_vehiculo = tiempo_vehiculo;
        this.tiempo_pie = tiempo_pie;
        this.consumo_gas = consumo_gas;
        this.desgaste_persona = desgaste_persona;
        this.distancia = distancia;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTiempo_vehiculo() {
        return tiempo_vehiculo;
    }

    public void setTiempo_vehiculo(int tiempo_vehiculo) {
        this.tiempo_vehiculo = tiempo_vehiculo;
    }

    public int getTiempo_pie() {
        return tiempo_pie;
    }

    public void setTiempo_pie(int tiempo_pie) {
        this.tiempo_pie = tiempo_pie;
    }

    public int getConsumo_gas() {
        return consumo_gas;
    }

    public void setConsumo_gas(int consumo_gas) {
        this.consumo_gas = consumo_gas;
    }

    public int getDesgaste_persona() {
        return desgaste_persona;
    }

    public void setDesgaste_persona(int desgaste_persona) {
        this.desgaste_persona = desgaste_persona;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Datos{"
                + "origen='" + origen + '\''
                + ", destino='" + destino + '\''
                + ", tiempo_vehiculo=" + tiempo_vehiculo
                + ", tiempo_pie=" + tiempo_pie
                + ", consumo_gas=" + consumo_gas
                + ", desgaste_persona=" + desgaste_persona
                + ", distancia=" + distancia
                + '}';
    }
}
