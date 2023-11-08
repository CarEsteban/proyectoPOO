import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileManagement {
    static File usuariosFile = new File("usuariosFile.csv");
    static File datosUsuariosFile = new File("datosUsuariosFile.csv");

/* 
    public ArrayList<Usuario> leerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        abrirCSV(usuariosFile);

        return usuarios;
    }
*/
    public void abrirCSV(File nombreFile){
        if(nombreFile == usuariosFile){
            String[] encabezado = {"Nombre", "Apellido", "Fecha Nacimiento","DPI","Correo","Contraseña"};
            crearCSV(usuariosFile, encabezado);

        }else if (nombreFile == datosUsuariosFile){
            String[] encabezado = {"Nombre", "Categoria", "Monto"};
            crearCSV(datosUsuariosFile, encabezado);
        }
    }
/*
    public void cerrarCSV(){

    }
*/
    public void ingresarNuevoUsuario(String[] datosUsuario,File nombreFile){
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(nombreFile,true))){

            StringBuilder linea = new StringBuilder();

            for (int i = 0; i < datosUsuario.length; i++) {

                linea.append(datosUsuario[i]);

                if (i < datosUsuario.length - 1) {
                    linea.append(",");
                }
            }
            linea.append("\n");

            wr.append(linea.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    public void crearCSV(File nombreFile, String[] encabezado) {
        try {
            if (!nombreFile.exists()) {
                FileWriter writer = new FileWriter(nombreFile);
    
                for (int i = 0; i < encabezado.length; i++) {
                    writer.append(encabezado[i]);
                    if (i < encabezado.length - 1) {
                        writer.append(",");
                    }
                }
    
                writer.append("\n");
                writer.close();
            } else {
                System.out.println("Archivo abierto correctamente");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}