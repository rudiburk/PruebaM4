package servicio;

import model.Cliente;
import utilidad.Utilidad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class ExportadorCsv extends Exportador {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        if (listaClientes == null) {
            Utilidad.showMessage("La lista está vacía...");
        } else {
            Utilidad.showMessage("Exportar Datos");
            Utilidad.showMessage("Ingresa la ruta a donde desea exportar el archivo clientes.csv: ");
            String cvsSeparatorValue = ",";
            String filePath = scanner.nextLine();
            String file = filePath + "/" + fileName;
            File fl = new File(file);
            if (fl.exists()) {
                fl.delete();
            }

            try {
                PrintWriter pWriter = new PrintWriter(new FileWriter(file));

                listaClientes.forEach(cliente -> {
                    pWriter.append(cliente.getRunCliente()).append(cvsSeparatorValue).append(cliente.getNombreCliente())
                            .append(cvsSeparatorValue).append(cliente.getApellidoCliente()).append(cvsSeparatorValue)
                            .append(cliente.getAniosCliente()).append(cvsSeparatorValue)
                            .append(cliente.getNombreCategoria().name()).append(System.lineSeparator());
                });
                pWriter.close();
                Utilidad.showMessagePredefined();
                Utilidad.showMessage("Datos de clientes exportados correctamente en formato .csv");
            } catch (IOException error) {
                Utilidad.showMessage("El archivo no pudo ser creado en formato .csv");
            } finally {
                Utilidad.showMessagePredefined();
            }

        }

    }
}

