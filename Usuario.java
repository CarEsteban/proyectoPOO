import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private String apellido;
    private String fechaNac;
    private String dpi;
    private String correo;
    private String contrasenia;
    private Presupuesto presupuesto;
    private ArrayList<String> categoriasUsuario;

    public Usuario(String nombre, String apellido, String fechaNac, String dpi, String correo,
        String contrasenia, Presupuesto presupuesto, ArrayList<String> categoriasUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.dpi = dpi;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.presupuesto = presupuesto;
        this.categoriasUsuario = categoriasUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public ArrayList<String> getCategoriasUsuario() {
        return categoriasUsuario;
    }

    public void setCategoriasUsuario(ArrayList<String> categoriasUsuario) {
        this.categoriasUsuario = categoriasUsuario;
    }
}
