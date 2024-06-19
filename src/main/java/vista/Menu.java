package vista;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ExportadorCSV;
import servicio.ExportadorTXT;
import utilidades.Utilidad;

import java.util.Scanner;

public class Menu {
    private ClienteServicio clienteServicio = ClienteServicio.getClienteServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();
    private final ExportadorCSV exportadorCsv = new ExportadorCSV();
    private final ExportadorTXT exportadorTxt = new ExportadorTXT();
    private String fileName = "Clientes";
    private String fileName1 = "DbClientes.csv";
    private Scanner sc = new Scanner(System.in);

    public void iniciarMenu(){
        String opcion = "";

        while(!opcion.equals("6")) {
            mainMenu();
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    clienteServicio.listarClientes();
                    break;
                case "2":
                    menuAgregarCliente();
                    break;
                case "3":
                    menuEditarCliente();
                    break;
                case "4":
                    menuCargarDatos();
                    break;
                case "5":
                    menuExportarDatos();
                    break;
            }
        }
    }

    public void mainMenu() {
        Utilidad.clearScreen();
        System.out.println("1. Listar Clientes");
        System.out.println("2. Agregar Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Cargar Datos");
        System.out.println("5. Exportar Datos");
        System.out.println("6. Salir");
        System.out.println("Ingrese una opción:");
    }

    private void menuAgregarCliente() {
        Utilidad.clearScreen();
        System.out.println("-------------Crear Cliente-------------");
        System.out.println();
        System.out.println("        Ingresa RUN del Cliente:");
        String RUN = sc.nextLine();
        System.out.println("Ingresa Nombre del Cliente:");
        String nombre = sc.nextLine();
        System.out.println("Ingresa Apellido del Cliente:");
        String apellido = sc.nextLine();
        System.out.println("Ingresa años como Cliente:");
        String anios = sc.nextLine();
        System.out.println("---------------------------------------");

        clienteServicio.agregarCliente(RUN,nombre,apellido,anios, CategoriaEnum.Activo);
        Utilidad.sleep(1000);
    }


    private void menuEditarCliente() {
        System.out.println("-------------Editar Cliente-------------");
        System.out.println("Seleccione qué desea hacer:");
        System.out.println("1.-Cambiar el estado del Cliente");
        System.out.println("2.-Editar los datos ingresados del Cliente");
        System.out.println("Ingrese opción:");
        String opcion = sc.nextLine();
        System.out.println("----------------------------------------");

        System.out.println("Ingrese RUN del Cliente a editar:");
        String RUN = sc.nextLine();
        if (opcion.equals("1")) {
            menuActualizarEstadoCliente(RUN);
        } else if (opcion.equals("2")) {
            menuEditarDatosCliente(RUN);
        }
    }

    private void menuActualizarEstadoCliente(String run) {
        Cliente cl = clienteServicio.getCliente(run);
        if (cl == null) return;

        CategoriaEnum actual = cl.getNombreCategoria();
        CategoriaEnum contrario = CategoriaEnum.Activo;
        if (actual.equals(CategoriaEnum.Activo)) contrario = CategoriaEnum.Inactivo;

        System.out.println("-----Actualizando estado del Cliente----");
        System.out.printf("El estado actual es: %s\n", actual);
        System.out.printf("1.-Si desea cambiar el estado del Cliente a %s\n", contrario);
        System.out.printf("2.-Si desea mantener el estado del cliente %s\n", actual);
        System.out.println();
        System.out.println("Ingrese opción:");
        String opcion = sc.nextLine();
        System.out.println("----------------------------------------");
        Utilidad.sleep(1000);
        if (opcion.equals("1")) {
            cl.setNombreCategoria(contrario);
            clienteServicio.editarCliente(cl);
        }
    }

    private void menuEditarDatosCliente(String run) {
        System.out.println("IMPLEMENTAME POR EL AMOR DE DIOS");
    }

    private void menuCargarDatos() {
        System.out.println("---------Cargar Datos en Windows---------------");
        System.out.println();
        System.out.println("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv:");
        System.out.println();
        String ruta = sc.nextLine();
        System.out.println();
        System.out.println("-----------------------------------------------");

        archivoServicio.cargarDatos(ruta + "\\" + fileName1);

        System.out.println("Datos cargados correctamente en la lista");
    }

    private void menuExportarDatos() {
        System.out.println("---------Exportar Datos-----------");
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1.-Formato csv");
        System.out.println("2.-Formato txt");
        System.out.println();
        System.out.println("Ingrese una opción para exportar:");
        String opcion = sc.nextLine();
        System.out.println("----------------------------------");

        System.out.println("---------Exportar Datos en Windows---------------");
        System.out.printf("Ingresa la ruta en donde desea exportar el archivo clientes.%s:\n", opcion.equals("1")? "csv": "txt");
        String ruta = sc.nextLine();
        System.out.println("-----------------------------------------------");

        String archivo = ruta + "\\" + fileName + "." + (opcion.equals("1")? "csv": "txt");

        archivoServicio.exportar(archivo,clienteServicio.getListaClientes());
    }
}
