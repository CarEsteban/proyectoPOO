import java.time.LocalDate;
//tipo 1=ingreso ; 2=egreso
public class Movimiento {
    private int monto;
    private String categoria;
    private String descripcion;
    private int tipo;
    private LocalDate fecha;

    public Movimiento(int monto, String categoria, String descripcion, LocalDate fecha, String tipo) {
        this.monto = monto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        if(tipo.equals("Ingreso")){
            this.tipo = 1;
        }else if (tipo.equals("Egreso")) {
            this.tipo = 2;
        }else{
            this.tipo = 0;
        }
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
    public LocalDate getFecha() {
        return fecha;
    }

}
