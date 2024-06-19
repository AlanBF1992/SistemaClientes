import modelo.CategoriaEnum;
import org.junit.*;
import servicio.ClienteServicio;

import java.util.logging.Logger;

public class ClienteServicioTest {
    private static final Logger logger = Logger.getLogger("SistemaClientes");

    ClienteServicio cs = ClienteServicio.getClienteServicio();

    @Test
    public void agregarClienteTest(){
        logger.info("Test AgregarClientes");
        cs.agregarCliente("11.111.111-1","Lucho", "Luchales", "36", CategoriaEnum.Activo);
    }

    @Test
    public void agregarClienteNullTest(){
        logger.info("Test AgregarClientesNull");
        cs.agregarCliente(null);
    }
}
