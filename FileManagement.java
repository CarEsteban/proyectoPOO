import java.io.*;
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

    public void ingresarNuevoPresupuestoUsuario(String[] datosPrepuesto,File nombreFile){

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(nombreFile,true))){

            StringBuilder linea = new StringBuilder();

            for (int i = 0; i < datosPrepuesto.length; i++) {

                linea.append(datosPrepuesto[i]);

                if (i < datosPrepuesto.length - 1) {
                    linea.append(",");
                }
            }
            linea.append("\n");

            wr.append(linea.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verificarNombrePresupuesto(File nombreFile,Usuario user,String nombrePresupuesto){
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFile))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {

                String[] campos = linea.split(",");
                
                // Verificar si el nombre del usuario y el mes coinciden
                if (campos.length <= 4 && campos[0].equals(user.getNombre()) && campos[1].equals(nombrePresupuesto)) {
                    return false; // Los datos coinciden, retorna false
                }
            }

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
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
                System.out.println("Archivo CREADO");

                writer.close();
            } else {
                System.out.println("Archivo abierto correctamente");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}