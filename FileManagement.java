import java.io.*;
import java.util.ArrayList;
public class FileManagement {
    File usuariosFile = new File("usuariosFile.csv");
    File datosUsuariosFile = new File("datosUsuariosFile.csv");

    public ArrayList<Usuario> leerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        abrirCSV(usuariosFile);

        return usuarios;
    }

    public void abrirCSV(File nombreFile){
        if(nombreFile==usuariosFile){
            try(BufferedReader br = new BufferedReader(new FileReader(usuariosFile))) {

                if (!usuariosFile.exists()) {
                    // Si el archivo no existe, crea uno nuevo
                    usuariosFile.createNewFile();
                    System.out.println("El archivo CSV ha sido creado.");
                }
    
                String linea;
                    
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(",");

                    for (String campo : campos) {
                        System.out.print(campo + " | ");
                    }
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }else if (nombreFile == datosUsuariosFile) {
            try(BufferedReader br = new BufferedReader(new FileReader(datosUsuariosFile))) {
                if (!datosUsuariosFile.exists()) {
                    // Si el archivo no existe, crea uno nuevo
                    datosUsuariosFile.createNewFile();
                    System.out.println("El archivo CSV ha sido creado.");
                }
                String linea;
                    
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(",");

                    for (String campo : campos) {
                        System.out.print(campo + " | ");
                    }
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("Este archivo no existe");
        }
    }

    public void cerrarCSV(){

    }




}