import java.time.LocalDate;

public class Evento {
    private int id;
    private int idPais;
    private String artista;
    private int hora;
    private LocalDate fecha;
    private int duracion;
    private int asistentes;

    public Evento(int id, int idPais, String artista, int hora, LocalDate fecha, int duracion, int asistentes) {
        this.id = id;
        this.idPais = idPais;
        this.artista = artista;
        this.hora = hora;
        this.fecha = fecha;
        this.duracion = duracion;
        this.asistentes = asistentes;
    }

    public int getId() {
        return id;
    }

    public int getIdPais() {
        return idPais;
    }

    public String getArtista() {
        return artista;
    }

    public int getHora() {
        return hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getAsistentes() {
        return asistentes;
    }
}
