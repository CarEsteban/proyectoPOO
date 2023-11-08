import java.util.Date;

public class Movimiento {
    private int monto;
    private String categoria;
    private String descripcion;
    private int tipo;
    private String fecha;

    //metodos
    public void setMonto(int monto){
        this.monto = monto;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getMonto() {
        return monto;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getTipo() {
        return tipo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
