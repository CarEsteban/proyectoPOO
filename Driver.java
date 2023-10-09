import java.util.Scanner;
import java.util.InputMismatchException;

public class Driver {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int salir = 1, opcion = 0,opc;
        String usuario, contrasenia ;
        Usuario user = null; 

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Ingrese su usuario: \n");
            usuario = scanner.nextLine();
            System.out.print("Ingrese su contraseña: \n");
            contrasenia = scanner.nextLine();

            if (user != null) {
                loggedIn = autenticarUsuario(user);
            }

            if (!loggedIn) {
                System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
                System.out.print("Desea crear un usuario? 1)Si 2)No\n" );
                opc = scanner.nextInt();
                scanner.nextLine();
                switch (opc) {
                    case 1: 
                        String[] datosUsuario = ingresarNuevoUsuario();
                        user = new Usuario(datosUsuario[0], datosUsuario[1], datosUsuario[2], datosUsuario[3], datosUsuario[4], datosUsuario[5], null, null);
                        break;
                    case 2:
                        loggedIn = false;
                        break;
                }
            }
        }


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

    public static String[] ingresarNuevoUsuario(){
        String nombre, apellido, fechaNac, dpi, correo, contrasenia;
        Scanner scanner = new Scanner(System.in);


        System.out.print("Ingrese su nombre: ");
        nombre = scanner.nextLine();
        
        System.out.print("Ingrese su apellido: ");
        apellido = scanner.nextLine();
        
        System.out.print("Ingrese su fecha de nacimiento: ");
        fechaNac = scanner.nextLine();
        
        System.out.print("Ingrese su número de DPI: ");
        dpi = scanner.nextLine();
        
        System.out.print("Ingrese su correo electrónico: ");
        correo = scanner.nextLine();
        
        System.out.print("Ingrese su contraseña: ");
        contrasenia = scanner.nextLine();

        
        String[] datosUsuario = {nombre, apellido, fechaNac, dpi, correo, contrasenia};
        return datosUsuario;
    }

    public static boolean autenticarUsuario(Usuario user) {

        return user.getNombre().equals(user.getNombre()) && user.getContrasenia().equals(user.getContrasenia());
    }

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
        System.out.println("5: Consejos extras");
        System.out.println("6: Consulta de saldo por fechas");
        System.out.println("7: Salir");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
