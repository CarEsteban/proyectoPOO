public class Movimiento {
    private int monto;
    private String categoria;
    private String descripcion;
    private int tipo; 

    //metodos
    public void setMonto(int monto){
        this.monto += monto;
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
}
