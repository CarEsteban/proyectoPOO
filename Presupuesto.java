import java.util.ArrayList;
import java.util.Date;

public class Presupuesto {
    private String nombrePresupuesto;
    private int diaCreacion;
    private int mesCreacion;
    private int yearCreacion;
    ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
    private String consejo;
    private int balance;

    //metodos


    public Presupuesto(String nombrePresupuesto, int diaCreacion, int mesCreacion, int yearCreacion, ArrayList<Movimiento> movimientos) {
        this.nombrePresupuesto = nombrePresupuesto;
        this.diaCreacion = diaCreacion;
        this.mesCreacion = mesCreacion;
        this.yearCreacion = yearCreacion;
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

    // public void consultarSaldosPorFechas(Date fechaInicio, Date fechaFin) {
    //     int ingresos = 0;
    //     int egresos = 0;

    //     for (Movimiento movimiento : movimientos) {
    //         if (movimiento.getFecha().after(fechaInicio) && movimiento.getFecha().before(fechaFin)) {
    //             if (movimiento.getTipo() == 1) { // Ingresos
    //                 ingresos += movimiento.getMonto();
    //             } else if (movimiento.getTipo() == 2) { // Egresos
    //                 egresos += movimiento.getMonto();
    //             }
    //         }
    //     }

    //     System.out.println("Ingresos en el rango de fechas: " + ingresos);
    //     System.out.println("Egresos en el rango de fechas: " + egresos);
    // }

}
