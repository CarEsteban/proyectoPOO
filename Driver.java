import java.util.Scanner;
import java.util.InputMismatchException;

public class Driver {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int salir = 1;
        int opcion = 0;
        // String usuario = "";
        // String contrasenia = "";
        
        // boolean loggedIn = false;
        // while (!loggedIn) {
        //     System.out.print("Ingrese su usuario: ");
        //     usuario = scanner.nextLine();
        //     System.out.print("Ingrese su contraseña: ");
        //     contrasenia = scanner.nextLine();
        //     loggedIn = autenticarUsuario(usuario, contrasenia);
            
        //     if (!loggedIn) {
        //         System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
        //     }
        // }

        while (salir != 0) {
            printMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }
            
            switch (opcion) {
                case 1:
                    System.out.println("// Lógica para la opción 1");
                    break;
                case 2:
                    System.out.println("// Lógica para la opción 2");
                    break;
                case 3:
                    System.out.println("// Lógica para la opción 3");
                    break;
                case 4:
                    System.out.println("// Lógica para la opción 4");
                    break;
                case 5:
                    System.out.println("// Lógica para la opción 5");
                    break;
                case 6:
                    System.out.println("// Lógica para la opción 6");
                    break;
                case 7:
                    salir = 0;
                    System.out.println("// Salir del bucle");
                    break;
                default:
                    System.out.println("");
                    System.out.println("Número inválido. Intente nuevamente.");
                    break;
            }

            opcion = 0;
        }
    }

        // public static boolean autenticarUsuario(String usuario, String contrasenia) {
    //     String usuarioValido = "usuario_valido";
    //     String contraseniaValida = "contrasenia_valida";
    //
    //     return usuario.equals("usuario_valido") && contrasenia.equals("contrasenia_valida");
    // }

    public static void printMenu() {
        System.out.println("");
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("                Menú");
        System.out.println("*************************************");
        System.out.println("Ingrese la opción que desee:");
        System.out.println("1: Presupuesto");
        System.out.println("2: Gastos");
        System.out.println("3: Ingresos");
        System.out.println("4: Balance");
        System.out.println("5: Consejos");
        System.out.println("6: Consulta de saldo por fechas");
        System.out.println("7: Salir");
        System.out.println("");
    }
}
