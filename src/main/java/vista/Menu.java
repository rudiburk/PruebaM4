package vista;

import model.CategoriaEnum;
import model.Cliente;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;
import utilidad.Utilidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    ClienteServicio clienteServicio = new ClienteServicio();
    ArchivoServicio archivoServicio = new ArchivoServicio();
    ExportadorCsv exportarcsv = new ExportadorCsv();
    ExportadorTxt exportartxt = new ExportadorTxt();
    String fileName = "clientes";
    String fileName1 = "DBClientes.csv";
    Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {
        List<String> opcionesMenu = new ArrayList<String>();
        opcionesMenu.add("Listar Cliente");
        opcionesMenu.add("Agregar Cliente");
        opcionesMenu.add("Editar Cliente");
        opcionesMenu.add("Cargar Datos");
        opcionesMenu.add("Exportar Datos");
        opcionesMenu.add("Salir");
        Menu menu = new Menu();
        menu.seleccionOpcion(opcionesMenu);
    }

    protected int construirMenu(List<String> pOpcionesMenu) {
        List<String> opcionesMenu = pOpcionesMenu;
        int largo = opcionesMenu.size();
        for (int i = 0; i < largo; i++) {
            System.out.println(i + 1 + " " + opcionesMenu.get(i));
        }
        int opcion = 0;
        Utilidad.showMessage("Ingrese una opción:");
        try {
            opcion = scanner.nextInt();
        } catch (Exception error) {
            scanner.nextLine();
        }
        if (opcion < 1 || opcion >= largo + 1) {
            Utilidad.showMessage("Selección no permitida");
        }
        return opcion;
    }

    public void seleccionOpcion(List<String> pOpcionesMenu) {
        boolean continuar = false;
        int resultado;

        do {
            resultado = construirMenu(pOpcionesMenu);
            switch (resultado) {
                case 1:
                    listarCliente();
                    break;
                case 2:
                    agregarCliente();
                    break;
                case 3:
                    editarCliente();
                    break;
                case 4:
                    cargarDatos();
                    break;
                case 5:
                    exportarDatos();
                    break;
                case 6:
                    salirSistema();
                default:
                    Utilidad.showMessage("Opción no válida");
            }
        } while (!continuar);
    }

    private void listarCliente() {
        clienteServicio.listarClientes();
        Utilidad.stopAndContinue();
    }

    private void agregarCliente() {
        Utilidad.showMessage("Crear Cliente");
        scanner.nextLine(); // http://zparacha.com/java-scanner-class-not-reading-correctly-after-nextint-or-nextdouble-method-call
        Utilidad.showMessage("Ingresa RUN del Cliente:");
        String runCliente = scanner.nextLine();
        Utilidad.showMessage("Ingresa Nombre del Cliente:");
        String nombreCliente = scanner.nextLine();
        Utilidad.showMessage("Ingresa Apellido del Cliente:");
        String apellidoCliente = scanner.nextLine();
        Utilidad.showMessage("Ingresa años como Cliente:");
        String aniosCliente = scanner.nextLine();
        Utilidad.showMessagePredefined();

        // Por defecto, el nuevo cliente está siempre activo
        clienteServicio.agregarCliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.ACTIVO);
        Utilidad.stopAndContinue();
    }

    private void editarCliente() {
        Utilidad.showMessage("Editar Cliente");
        System.out.print("Seleccione qué desea hacer: ");
        Utilidad.showMessage("1.-Cambiar el estado en la categoria del Cliente");
        Utilidad.showMessage("2.-Editar los datos ingresados del Cliente");
        int opcionEdicion = scanner.nextInt();
        scanner.nextLine();
        Utilidad.showMessage("Ingrese RUN del cliente:");
        String run1 = scanner.nextLine();
        List<Cliente> listaClientes = clienteServicio.getListaClientes();
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(run1)) {
                if (opcionEdicion == 1) {
                    actualizarEstadoCliente(cliente);
                } else if (opcionEdicion == 2) {
                    actualizarDatosClientes(cliente);
                }
            }
        }
    }

    private void actualizarDatosClientes(Cliente cliente) {
        Utilidad.showMessage("1.-El RUN del Cliente es: " + cliente.getRunCliente());
        Utilidad.showMessage("2.-El Nombre del Cliente: " + cliente.getNombreCliente());
        Utilidad.showMessage("3.-El Apellido del Cliente: " + cliente.getApellidoCliente());
        Utilidad.showMessage("4.-Los años como Cliente son: " + cliente.getAniosCliente());
        Utilidad.showMessagePredefined();
        Utilidad.showMessage("Ingrese la opción a editar de los datos del cliente:");
        int opcionCliente = scanner.nextInt();
        scanner.nextLine();
        switch (opcionCliente) {
            case 1:
                Utilidad.showMessage("Ingrese nuevo RUN del Cliente:");
                String runCliente1 = scanner.nextLine();
                cliente.setRunCliente(runCliente1);
                break;
            case 2:
                Utilidad.showMessage("Ingrese nuevo nombre del Cliente:");
                String nombreCliente = scanner.nextLine();
                cliente.setNombreCliente(nombreCliente);
                break;
            case 3:
                Utilidad.showMessage("Ingrese nuevo apellido del Cliente:");
                String apellidoCliente = scanner.nextLine();
                cliente.setApellidoCliente(apellidoCliente);
                break;
            case 4:
                Utilidad.showMessage("Ingrese la cantidad nueva de años:");
                String aniosCliente = scanner.nextLine();
                cliente.setAniosCliente(aniosCliente);
                break;
            default:
                Utilidad.showMessage("Usted marco una opción incorrecta");
        }
        Utilidad.stopAndContinue();
    }

    private void actualizarEstadoCliente(Cliente cliente) {
        Utilidad.showMessage("El estado actual es: " + cliente.getNombreCategoria());
        Utilidad.showMessagePredefined();
        Utilidad.showMessage("1.-Si desea cambiar el estado");
        Utilidad.showMessage("2.-Si desea mantener el estado");
        Utilidad.showMessage("Ingrese opción:");
        Utilidad.showMessagePredefined();
        int opcionActividad = scanner.nextInt();
        if (opcionActividad == 1) {
            cliente.setNombreCategoria(CategoriaEnum.INACTIVO);
        } else if (opcionActividad == 2) {
            Utilidad.stopAndContinue();
        } else {
            Utilidad.showMessage("Opción no válida");
        }
    }

    private void cargarDatos() {
        Utilidad.showMessage("Cargar Datos");
        Utilidad.showMessage("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv:");

        List<Cliente> listaClientes = archivoServicio.cargarDatos(fileName1);
        if (listaClientes != null && !listaClientes.isEmpty()) {
            clienteServicio.setListaClientes(listaClientes);

            Utilidad.showMessage("Datos cargados correctamente en la lista");
            Utilidad.showMessagePredefined();
            Utilidad.stopAndContinue();
        } else {
            Utilidad.showMessage("No se pudo cargar los datos del archivo " + fileName1);
        }
    }

    private void exportarDatos() {
        Utilidad.showMessage("Exportar Datos");
        Utilidad.showMessage("Seleccione el formato a exportar:");
        Utilidad.showMessage("1.-Formato '.csv'");
        Utilidad.showMessage("2.- Formato '.txt'");
        Utilidad.showMessage("Ingrese una opción para exportar:");
        Utilidad.showMessagePredefined();

        List<Cliente> listaClientes = clienteServicio.getListaClientes();
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                exportarcsv.exportar(fileName + ".csv", listaClientes);
                Utilidad.stopAndContinue();
                break;
            case 2:
                exportartxt.exportar(fileName + ".txt", listaClientes);
                Utilidad.stopAndContinue();
                break;
            case 3:
                Utilidad.showMessage("Número de opción ingresado incorrectamente.");
        }
    }

    private void salirSistema() {
        Utilidad.showMessage("Abandonando el sistema de clientes...");
        Utilidad.timeToWait();
        Utilidad.showMessage("Acaba de salir del sistema");
        Utilidad.stopAndContinue();
        System.exit(0);
    }

}

