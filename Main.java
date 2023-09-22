import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        manageEventos manageEventos = new manageEventos();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Agregar País");
            System.out.println("2. Agregar Recinto");
            System.out.println("3. Consultar Recinto por ID");
            System.out.println("4. Agregar Evento");
            System.out.println("5. Consultar Todos los Eventos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    manageEventos.agregarPais();
                    break;
                case 2:
                    manageEventos.agregarRecinto();
                    break;
                case 3:
                    manageEventos.verRecintoPorId();
                    break;
                case 4:
                    manageEventos.agregarEvento();
                    break;
                case 5:
                    manageEventos.verTodosLosEventos();
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }
}


