import java.util.ArrayList;

public class Presupuesto {
    private String nombrePresupuesto;
    private int diaCreacion;
    private int mesCreacion;
    private int yearCreacion;
    ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
    private String consejo;
    private int balance;

    //metodos
    public void calcularBalance()
    {
    }
    public int getBalance()
    {
        return balance;
    }

    public void sumarEgresos()
    {
        int egresos = 0;
        for (int i = 0; i < movimientos.size(); i++) {
            if (movimientos.get(i).getTipo() == 2) {
                egresos += movimientos.get(i).getMonto();
            }
        }
        System.out.println("Los egresos son: " + egresos);
    }

    public void sumarIngresos()
    {
        int ingresos = 0;
        for (int i = 0; i < movimientos.size(); i++) {
            if (movimientos.get(i).getTipo() == 1) {
                ingresos += movimientos.get(i).getMonto();
            }
        }
        System.out.println("Los ingresos son: " + ingresos);
    }
    public String getConsejo()
    {
        return consejo;
    }

}
