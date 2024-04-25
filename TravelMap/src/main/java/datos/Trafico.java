package datos;

public class Trafico {
    
    private String torigen;
    private String tdestino;
    private int hinicio;
    private int hllegada;
    private int probabilidad;

    public Trafico(String torigen, String tdestino, int hinicio, int hllegada, int probabilidad) {
        this.torigen = torigen;
        this.tdestino = tdestino;
        this.hinicio = hinicio;
        this.hllegada = hllegada;
        this.probabilidad = probabilidad;
    }

    public String getTorigen() {
        return torigen;
    }

    public void setTorigen(String torigen) {
        this.torigen = torigen;
    }

    public String getTdestino() {
        return tdestino;
    }

    public void setTdestino(String tdestino) {
        this.tdestino = tdestino;
    }

    public int getHinicio() {
        return hinicio;
    }

    public void setHinicio(int hinicio) {
        this.hinicio = hinicio;
    }

    public int getHllegada() {
        return hllegada;
    }

    public void setHllegada(int hllegada) {
        this.hllegada = hllegada;
    }

    public int getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
    }

    @Override
    public String toString() {
        return "Trafico{" + "torigen=" + torigen + ", tdestino=" + tdestino + ", hinicio=" + hinicio + ", hllegada=" + hllegada + ", probabilidad=" + probabilidad + '}';
    }
    
    
    
    
}
