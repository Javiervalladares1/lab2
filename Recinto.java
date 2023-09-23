public class Recinto {
    private int idPais;
    private String ubicacion;
    private int id;
    private int capacidad;

    public Recinto(int idPais, String ubicacion, int id, int capacidad) {
        this.idPais = idPais;
        this.ubicacion = ubicacion;
        this.id = id;
        this.capacidad = capacidad;
    }

    public int getIdPais() {
        return idPais;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }
}

