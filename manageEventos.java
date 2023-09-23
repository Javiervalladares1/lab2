import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class manageEventos {
    private List<Pais> paises;
    private List<Recinto> recintos;
    private List<Evento> eventos;

    public manageEventos() {
        paises = new ArrayList<>();
        recintos = new ArrayList<>();
        eventos = new ArrayList<>();
        paises = cargarPaisesDesdeCSV("Pais.csv");
    }

    public void agregarPais() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del país:");
        String nombrePais = scanner.nextLine();
        int nuevoIdPais = obtenerNuevoIdPais();
        Pais nuevoPais = new Pais(nuevoIdPais, nombrePais);
        paises.add(nuevoPais);
        escribirPaisEnCSV(nuevoPais);
        System.out.println("País agregado con éxito.");
    }

    public void agregarRecinto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista de Países:");
        for (Pais pais : paises) {
            System.out.println(pais.getId() + ": " + pais.getNombre());
        }
        System.out.print("Seleccione el ID del País: ");
        int idPais = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la ubicación del recinto: ");
        String ubicacion = scanner.nextLine();
        System.out.print("Ingrese la capacidad del recinto: ");
        int capacidad = scanner.nextInt();
        int nuevoIdRecinto = obtenerNuevoIdRecinto();
        Recinto nuevoRecinto = new Recinto(idPais, ubicacion, nuevoIdRecinto, capacidad);
        recintos.add(nuevoRecinto);
        escribirRecintoEnCSV(nuevoRecinto);
        System.out.println("Recinto agregado con éxito.");
    }

    public void verRecintoPorId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del recinto que desea consultar: ");
        int idRecinto = scanner.nextInt();
        scanner.nextLine();
        Recinto recinto = obtenerRecintoPorId(idRecinto);
        if (recinto != null) {
            System.out.println("Recinto encontrado:");
            System.out.println("ID: " + recinto.getId());
            System.out.println("Ubicación: " + recinto.getUbicacion());
            System.out.println("Capacidad: " + recinto.getCapacidad());
            System.out.println("País: " + obtenerNombrePaisPorId(recinto.getIdPais()));
        } else {
            System.out.println("No se encontró ningún recinto con el ID especificado.");
        }
    }

    public void agregarEvento() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del país: ");
        int idPais = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del artista: ");
        String artista = scanner.nextLine();
        int hora; // Variable para almacenar la hora
        do {
            System.out.print("Ingrese el horario (de 6 a 22): ");
            hora = scanner.nextInt();
        } while (hora < 6 || hora > 22);
        scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese la fecha (en formato AAAA-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr);
        int duracion;
        do {
            System.out.print("Ingrese la duración (de 1 a 12 horas): ");
            duracion = scanner.nextInt();
        } while (duracion < 1 || duracion > 12);
        scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese la cantidad de asistentes (de 10 a 150000): ");
        int asistentes = scanner.nextInt();
        scanner.nextLine();
        int nuevoIdEvento = obtenerNuevoIdEvento();
        Evento nuevoEvento = new Evento(nuevoIdEvento, idPais, artista, hora, fecha, duracion, asistentes);
        eventos.add(nuevoEvento);
        escribirEventoEnCSV(nuevoEvento);
        System.out.println("Evento agregado con éxito.");
    }

    public void verTodosLosEventos() {
        for (Evento evento : eventos) {
            mostrarEvento(evento);
        }
    }

    private int obtenerNuevoIdPais() {
        int ultimoIdPais = 0;
        for (Pais pais : paises) {
            if (pais.getId() > ultimoIdPais) {
                ultimoIdPais = pais.getId();
            }
        }
        return ultimoIdPais + 1;
    }

    private int obtenerNuevoIdRecinto() {
        int ultimoIdRecinto = 0;
        for (Recinto recinto : recintos) {
            if (recinto.getId() > ultimoIdRecinto) {
                ultimoIdRecinto = recinto.getId();
            }
        }
        return ultimoIdRecinto + 1;
    }

    private int obtenerNuevoIdEvento() {
        int ultimoIdEvento = 0;
        for (Evento evento : eventos) {
            if (evento.getId() > ultimoIdEvento) {
                ultimoIdEvento = evento.getId();
            }
        }
        return ultimoIdEvento + 1;
    }

    private void escribirPaisEnCSV(Pais pais) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Pais.csv", true))) {
            String linea = pais.getId() + "," + pais.getNombre();
            escritor.write(linea);
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escribirRecintoEnCSV(Recinto recinto) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Recinto.csv", true))) {
            String linea = recinto.getIdPais() + "," + recinto.getUbicacion() + "," + recinto.getId() + "," + recinto.getCapacidad();
            escritor.write(linea);
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escribirEventoEnCSV(Evento evento) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Eventos.csv", true))) {
            String linea = evento.getId() + "," + evento.getIdPais() + "," + evento.getArtista() + ","
                    + evento.getHora() + "," + evento.getFecha() + "," + evento.getDuracion() + ","
                    + evento.getAsistentes();
            escritor.write(linea);
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Pais> cargarPaisesDesdeCSV(String archivoCSV) {
        List<Pais> listaPaises = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                Pais pais = new Pais(id, nombre);
                listaPaises.add(pais);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaPaises;
    }

    private Recinto obtenerRecintoPorId(int id) {
        for (Recinto recinto : recintos) {
            if (recinto.getId() == id) {
                return recinto;
            }
        }
        return null;
    }

    private String obtenerNombrePaisPorId(int id) {
        for (Pais pais : paises) {
            if (pais.getId() == id) {
                return pais.getNombre();
            }
        }
        return "";
    }

    private void mostrarEvento(Evento evento) {
        System.out.println("ID Evento: " + evento.getId());
        System.out.println("ID País: " + evento.getIdPais());
        System.out.println("Artista: " + evento.getArtista());
        System.out.println("Hora: " + evento.getHora());
        System.out.println("Fecha: " + evento.getFecha());
        System.out.println("Duración: " + evento.getDuracion() + " horas");
        System.out.println("Asistentes: " + evento.getAsistentes());
        System.out.println();
    }
}
