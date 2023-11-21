import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Presupuesto {
    private String nombrePresupuesto;
    private LocalDate fechaCreacion;
    ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
    private String consejo;
    private int balance;

    //metodos

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public Presupuesto(String nombrePresupuesto, LocalDate fechaCreacion, ArrayList<Movimiento> movimientos) {
        this.nombrePresupuesto = nombrePresupuesto;
        this.fechaCreacion = fechaCreacion;
        this.movimientos = movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void calcularBalance() {
        int ingresosPlanificados = 0;
        int egresosEjecutados = 0;
    
        for (Movimiento movimiento : movimientos) {
            if (movimiento.getTipo() == 1) { // Ingresos planificados
                ingresosPlanificados += movimiento.getMonto();
            } else if (movimiento.getTipo() == 2) { // Egresos ejecutados
                egresosEjecutados += movimiento.getMonto();
            }
        }
    
        balance = ingresosPlanificados - egresosEjecutados;
    }
    public int getBalance(){
        return balance;
    }

    public void sumarEgresos(){
        int egresos = 0;
        for (int i = 0; i < movimientos.size(); i++) {
            if (movimientos.get(i).getTipo() == 2) {
                egresos += movimientos.get(i).getMonto();
            }
        }
        System.out.println("Los egresos son: " + egresos);
    }

    public void sumarIngresos(){
        int ingresos = 0;
        for (int i = 0; i < movimientos.size(); i++) {
            if (movimientos.get(i).getTipo() == 1) {
                ingresos += movimientos.get(i).getMonto();
            }
        }
        System.out.println("Los ingresos son: " + ingresos);
    }
    public String getConsejo(){
        return consejo;
    }
    public String getNombre() {
        return nombrePresupuesto;
    }
    public String getFechaCreacion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaPresupuesto = fechaCreacion.format(formatter);
        return fechaPresupuesto;
    }

    public int calcularGastoHastaFecha(LocalDate fechaActual) {
        int gastoTotal = 0;

        for (Movimiento movimiento : movimientos) {
            if (movimiento.getTipo() == 2 && movimiento.esAnterior(fechaActual)) {
                gastoTotal += movimiento.getMonto();
            }
        }

        return gastoTotal;
    }

}
