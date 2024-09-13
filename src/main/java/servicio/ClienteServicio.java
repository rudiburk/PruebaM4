package servicio;

import model.CategoriaEnum;
import model.Cliente;
import utilidad.Utilidad;

import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {

    List<Cliente> listaClientes;

    public ClienteServicio() {
        listaClientes = new ArrayList<>();
    }

    public void listarClientes() {
        if (listaClientes != null) {

            for (Cliente cliente : listaClientes) {
                Utilidad.showMessage("Datos del Cliente:");
                Utilidad.showMessage("RUN: " + cliente.getRunCliente());
                Utilidad.showMessage("Nombre: " + cliente.getNombreCliente());
                Utilidad.showMessage("Apellido: " + cliente.getApellidoCliente());
                Utilidad.showMessage("Años como cliente: " + cliente.getAniosCliente());
                Utilidad.showMessage("Categoría del cliente: " + cliente.getNombreCategoria());
                Utilidad.showMessagePredefined();
            }
        } else {
            Utilidad.showMessage("No se ha podido listar a los clientes, aún no carga datos");
        }
        Utilidad.stopAndContinue();
    }

    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente,
                               CategoriaEnum nombreCategoria) {
        Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.ACTIVO);

        if (listaClientes != null) {
            listaClientes.add(cliente);
        } else {
            Utilidad.showMessage("El cliente al cual usted está agregando viene nulo");
        }
    }

    public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente,
                              CategoriaEnum nombreCategoria, List<Cliente> listaClientes) {

    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
