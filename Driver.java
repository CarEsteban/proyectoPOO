import java.util.Scanner;
import java.lang.Character.Subset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Driver {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0,opc=0,monto;
        String usuario, contrasenia ;
        Usuario user = null; 
        Movimiento ingresos = new Movimiento();
        Movimiento egresos = new Movimiento();
        ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        String contraPrueba = generarHashMD5("angel123");
        usuarios.add(new Usuario("Pepito", "Perez", "10/10/2023", "651646516540101",
        "a@gmail.com", contraPrueba, null, null));

        boolean loggedIn = false;
        boolean logIn = true;
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
                    while (logIn) {
                        System.out.println("");
                        System.out.println("Ingrese su usuario:");
                        usuario = scanner.nextLine();
                        System.out.println("");
                        System.out.println("Ingrese su contraseña:");
                        contrasenia = scanner.nextLine();
                        System.out.println(usuarios.get(0).getContrasenia());
                        System.out.println(generarHashMD5(contrasenia));

                        if (autenticarUsuario(usuarios, usuario, contrasenia) == true) {
                            loggedIn = true;
                        }

                        else {
                            System.out.println("");
                            System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
                        }
                    }
                    break;
                case 2: // Opción para crear un nuevo usuario
                    break;
                case 3: // Opción para salir
                    logIn = false;
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
            loggedIn = false;
        }

        
        /*while (salir) {
            System.out.println("");
            System.out.println("Ingrese su usuario:");
            usuario = scanner.nextLine();
            System.out.println("");
            System.out.println("Ingrese su contraseña:");
            contrasenia = scanner.nextLine();
            System.out.println(usuarios.get(0).getContrasenia());
            System.out.println(generarHashMD5(contrasenia));

            if (autenticarUsuario(usuarios, usuario, contrasenia) == true) {
                loggedIn = true;
            }

            else {
                System.out.println("");
                System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
            }*/

            /*if (!loggedIn) {
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
        }*/


        /*while (loggedIn != 0) {
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
                    System.out.println("BIENVENIDO AL INGRESO DE EGRESOS E INGRESOS " + user.getNombre());
                    opc=1;
                    while(opc != 0) {
                        System.out.println("Desea agregar un ingreso (1) o un egreso (2)?");
                        opc = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (opc) {
                            case 1:
                                System.out.println("Ingrese el monto del ingreso");
                                monto = scanner.nextInt();
                                scanner.nextLine();
                                ingresos.setMonto(monto);
                                break;
            
                            case 2:
                                System.out.println("Ingrese el monto del egreso");
                                monto = scanner.nextInt();
                                scanner.nextLine();
                                egresos.setMonto(monto);
                                break;
            
                            default:
                                System.out.println("Opción no válida. Intente nuevamente.");
                                break;
                        }
            
                        System.out.println("Desea agregar otro movimiento? (1) Sí / (2) No");
                        opc = scanner.nextInt();
                        scanner.nextLine();
                        if(opc==1){
                            opc=1;
                        }else{
                            opc=0;
                        }
                    } 


                    break;
                case 2:
                    System.out.println("BIENVENIDO AL BALANCE");
                    int diferencia = ingresos.getMonto() - egresos.getMonto();
                    System.out.println("Este es el balance del mes: " + diferencia);
                    if(diferencia<0){
                        System.out.println("Consejo: Debes invertir en BTC");
                    }else{
                        System.out.println("Consejo: Vas muy bien Elon Musk");
                    }
                    break;
                case 3:
                    System.out.println("// Lógica para la opción 3");
                    break;
                case 4:
                    System.out.println("// Lógica para la opción 4");
                    break;
                case 5:
                    salir = 0;
                    System.out.println("// Salir del bucle");
                    break;
                default:
                    System.out.println("");
                    System.out.println("Número inválido. Intente nuevamente.");
                    break;
            }

            opcion = 0;
        }*/
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
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("                Menú");
        System.out.println("*************************************");
        System.out.println("Ingrese la opción que desee:");
        System.out.println("1: Ingreso presupuesto planificado");
        System.out.println("2: Ingreso post-mes");
        System.out.println("3: Balance");
        System.out.println("3: Consulta de saldo por fechas");
        System.out.println("4: Consejos extras");
        System.out.println("5: Salir");
        System.out.println("");
        System.out.println("");
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
}
