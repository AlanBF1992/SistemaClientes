package servicio;

import modelo.Cliente;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ExportadorTXT extends Exportador{

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("File already exists");
            return;
        }
        try {
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);

                for (Cliente cl : listaClientes) {
                    bw.write(cl.getRunCliente() + ",");
                    bw.write(cl.getNombreCliente() + ",");
                    bw.write(cl.getApellidoCliente() + ",");
                    bw.write(cl.getAniosCliente()+ ",");
                    bw.write(cl.getNombreCategoria().toString());
                    bw.newLine();
                }

                bw.close();

            } else {
                System.out.println("File couldn't be created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
