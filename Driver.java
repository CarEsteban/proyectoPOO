import java.util.Scanner;
import java.io.File;
import java.lang.Character.Subset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Driver {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0, opcion1 = 0, opcion2 = 0,monto;
        String usuario, contrasenia ;
        Usuario user = null; 
        Movimiento ingresos = new Movimiento();
        Movimiento egresos = new Movimiento();
        ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        boolean loggedIn = false;
        boolean principal = true;

        while (principal) {
            iniciarOCrear();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            switch (opcion) {
                case 1: // Opción para iniciar sesión
                    System.out.println("");
                    System.out.println("Ingrese su usuario:");
                    usuario = scanner.nextLine();
                    System.out.println("");
                    System.out.println("Ingrese su contraseña:");
                    contrasenia = scanner.nextLine();

                    if (autenticarUsuario(usuarios, usuario, contrasenia) == true) {
                        loggedIn = true;
                    }

                    else {
                        System.out.println("");
                        System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
                    }

                    if (loggedIn == true) { // Si se inició sesión
                        while (loggedIn) {
                            System.out.println("");
                            System.out.println("Bienvenido " + usuario);

                            printMenu();

                            try {
                                opcion1 = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("");
                                System.out.println("Ingrese un número.");
                                scanner.nextLine();
                            }
                            switch (opcion1) {
                                case 1: // Ingreso presupuesto planificado
                                    System.out.println("Ingreso del presupuesto planificado");

                                    loggedIn = volverAlMenu(scanner, " a ingresar otra opción? ");
                                    break;
                                case 2: // Ingreso post-mes
                                    break;
                                case 3: // Balance
                                    user.getPresupuesto().calcularBalance();
                                    System.out.println("Balance actual: " + user.getPresupuesto().getBalance());
                                    break;
                                case 4: // Consulta de saldo por fechas
                                    System.out.println("Ingrese la fecha de inicio (en formato YYYY-MM-DD):");
                                    String fechaInicioStr = scanner.nextLine();
                                    Date fechaInicio = parsearFecha(fechaInicioStr);
                                    System.out.println("Ingrese la fecha de fin (en formato YYYY-MM-DD):");
                                    String fechaFinStr = scanner.nextLine();
                                    Date fechaFin = parsearFecha(fechaFinStr);
                                    user.getPresupuesto().consultarSaldosPorFechas(fechaInicio, fechaFin);
                                    break;
                                case 5: // Consejos extras
                                    break;
                                case 6: // Salir
                                    loggedIn = false;
                                    break;
                                case 0:
                                    continue;
                                default:
                                    System.out.println("");
                                    System.out.println("Número inválido. Intente nuevamente.");
                                    break;
                            }
                        }
                    }
                    break;
                case 2: // Opción para crear un nuevo usuario
                    String[] datosusuario = ingresarNuevoUsuario();
                    usuarios.add(new Usuario(datosusuario[0], datosusuario[1], datosusuario[2], datosusuario[3], datosusuario[4], generarHashMD5(datosusuario[5]), null, null));
                    break;
                case 3: // Opción para salir
                    principal = false;
                    System.out.println("Hasta pronto :)");
                    break;
                case 0:
                    continue;
                default:
                    System.out.println("");
                    System.out.println("Número inválido. Intente nuevamente.");
                    break;
            }

            opcion = 0;
            opcion1 = 0;
            loggedIn = false;
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

    public static boolean autenticarUsuario(ArrayList<Usuario> usuarios, String usuario, String contrasenia) {
        boolean encontrado = false;

        for (Usuario usuario1 : usuarios) {
            if (usuario1.getNombre().equals(usuario) && usuario1.getContrasenia().equals(generarHashMD5(contrasenia))) {
                encontrado = true;
                break;
            }

            else {
                continue;
            }
        }

        return encontrado;
    }

    public static void printMenu() {
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("            Menú Principal");
        System.out.println("*************************************");
        System.out.println("Ingrese la opción que desee:");
        System.out.println("1: Ingreso presupuesto planificado");
        System.out.println("2: Ingreso presupuesto ejecutado");
        System.out.println("3: Balance");
        System.out.println("4: Consulta de saldo por fechas");
        System.out.println("5: Consejos extras");
        System.out.println("6: Salir");
        System.out.println("");
    }

    public static void iniciarOCrear() {
        System.out.println("");
        System.out.println("Ingrese la opción que desee:");
        System.out.println("1: Iniciar sesión");
        System.out.println("2: Crear usuario");
        System.out.println("3: Salir");
        System.out.println("");
    }

    public static String generarHashMD5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(texto.getBytes());
            byte[] hash = md.digest();

            // Convertir el hash de bytes a representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Date parsearFecha(String fechaStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = sdf.parse(fechaStr);
            return fecha;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;}
    }
    
    private static boolean volverAlMenu(Scanner scanner, String eleccion) {
        System.out.println("¿Desea volver" + eleccion + " (1: Sí, 0: No): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) {
            if (eleccion.equals(" al menú? ")) {
                System.out.println("Saliendo del programa.");

                return false;
            } else {
                System.out.println("Saliendo de la opción.");
                return false;
            }
        } else {
            return true;
        }
    }
}
