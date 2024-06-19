package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteServicio {
    private static ClienteServicio instance;
    private List<Cliente> listaClientes;

    private ClienteServicio() {
        listaClientes = new ArrayList<>();
    }

    public static ClienteServicio getClienteServicio() {
        if (instance == null) {
            instance = new ClienteServicio();
        }
        return instance;
    }

    public void listarClientes(){
        listaClientes.forEach(x -> {
            System.out.println("-------------Datos del Cliente-------------");
            System.out.println();
            System.out.printf("RUN del Cliente: %s\n", x.getRunCliente());
            System.out.printf("Nombre del Cliente: %s\n", x.getNombreCliente());
            System.out.printf("Apellido del Cliente: %s\n", x.getApellidoCliente());
            System.out.printf("Años del Cliente: %s\n", x.getAniosCliente());
            System.out.printf("Categoría del Cliente: %s\n", x.getNombreCategoria());
            System.out.println();
            System.out.println("-------------------------------------------");
        });
    }

    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, CategoriaEnum nombreCategoria){
        Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
        instance.listaClientes.add(cliente);
    }

    public void agregarCliente(Cliente cliente){
        instance.listaClientes.add(cliente);
    }

    public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, CategoriaEnum nombreCategoria){
        Cliente cl = getCliente(runCliente);
        if (cl == null)
            return;

        int index = instance.listaClientes.indexOf(cl);

        Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
        instance.listaClientes.set(index, cliente);
    }

    public void editarCliente(Cliente cliente) {
        Cliente cl = getCliente(cliente.getRunCliente());
        if (cl == null)
            return;

        int index = instance.listaClientes.indexOf(cl);

        instance.listaClientes.set(index, cliente);
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public Cliente getCliente(String RUN) {
        Optional<Cliente> ocl = instance.listaClientes.stream().filter(x -> x.getRunCliente().equals(RUN)).findFirst();
        if (ocl.isEmpty())
            System.out.println("Cliente no está en la base de datos");
        return ocl.orElse(null);
    }
}
