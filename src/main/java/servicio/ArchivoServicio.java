package servicio;

import model.CategoriaEnum;
import model.Cliente;
import utilidad.Utilidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArchivoServicio extends Exportador {
    Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("static-access")
    public List<Cliente> cargarDatos(String fileName) {
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        String filePath = scanner.nextLine();
        String file = filePath + File.separator + fileName;

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(new File(file));
            br = new BufferedReader(fr);
            List<Cliente> l = br.lines().map(line -> line.split(","))
                    .map(values -> new Cliente(values[0], values[1], values[2], values[3], CategoriaEnum.ACTIVO))
                    .collect(Collectors.toList());
              Utilidad.showMessage(l.toString());
           // Utilidad.showMessage(l.toString());
            return l;
        } catch (Exception error) {
            Utilidad.showMessage("No se pudo cargar el archivo .csv");
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception error) {
                Utilidad.showMessage("No se pudo crear el archivo");
            }
        }
        return null;
    }

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        Exportador exportador = new ExportadorCsv();
        //Exportador exportador = new ExportadorCsv();
        Exportador exportador2 = new ExportadorTxt();
        exportador.exportar(fileName, listaClientes);
        exportador2.exportar(fileName, listaClientes);
    }
}
