package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class ArchivoServicio extends Exportador {
    ExportadorCSV exportadorCsv = new ExportadorCSV();
    ExportadorTXT exportadorTxt = new ExportadorTXT();

    public void cargarDatos(String fileName) {
        File file = new File(fileName);
        if (!file.isFile()){
            System.out.println("Archivo no existe");
            return;
        }
        if (!file.canRead()) {
            System.out.println("El archivo no se puede leer");
            return;
        }

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            for(String s: br.lines().toList()) {
                String[] data = Arrays.stream((s.split(","))).map(String::trim).toArray(String[]::new);
                Cliente cl = new Cliente(data[0],data[1], data[2], data[3], CategoriaEnum.Activo);
                ClienteServicio.getClienteServicio().agregarCliente(cl);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
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
