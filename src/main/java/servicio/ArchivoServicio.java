package servicio;

import modelo.Cliente;

import java.util.List;

public class ArchivoServicio extends Exportador {
    ExportadorCSV exportadorCsv = new ExportadorCSV();
    ExportadorTxt exportadorTxt = new ExportadorTxt();

    public void cargarDatos(String fileName) {
        //Cargar
    }

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        if (fileName.endsWith("csv")) {
            exportadorCsv.exportar(fileName,listaClientes);
        } else if (fileName.endsWith("txt")) {
            exportadorTxt.exportar(fileName,listaClientes);
        }
    }
}
