import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.text.*;
import java.time.*;

public class Driver {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0, opcion1 = 0, opcion2 = 0,monto,categoriaMonto,montoIngresos;
        boolean categoriasCheck=true;
        File usuariosFile = new File("usuariosFile.csv"), datosUsuariosFile = new File("datosUsuariosFile.csv"), presupuestoUsuarios = new File("presupuestoUsuarios.csv");
        String usuario, contrasenia ,categoriaNombre,fechaMonto,categoriaIngreso,descripcionIngreso,descripcionCategoria,fecha;
        LocalDate fechaCreacion,fechaIngreso,fechaCategoria;
        Usuario user = null;
        FileManagement fileManagement = new FileManagement();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
        ArrayList<String> categoriasUsuario = new ArrayList<String>();

        boolean loggedIn = false;
        boolean principal = true;


        //Crear archivos si no estan creados en la computadora
        String[] encabezadoUsuario = {"Nombre", "Apellido", "Fecha Nacimiento","DPI","Correo","Password"};
        fileManagement.crearCSV(usuariosFile, encabezadoUsuario);
        String[] encabezadoDatos = {"Nombre", "Categoria", "Monto"};
        fileManagement.crearCSV(datosUsuariosFile, encabezadoDatos);
        String[] encabezadoPresupuesto = {"Usuario", "Nombre Presupuesto", "Fecha de Creacion", "Categorias"};
        fileManagement.crearCSV(presupuestoUsuarios, encabezadoPresupuesto);



        while (principal) {
            iniciarOCrear();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
            }

            switch (opcion) {
                case 1: // Opción para iniciar sesión
                    System.out.println("");
                    System.out.println("Ingrese su nombre de usuario:");
                    usuario = scanner.nextLine();
                    System.out.println("");
                    System.out.println("Ingrese su contraseña:");
                    contrasenia = scanner.nextLine();
                    String[] datosUsuario = autenticarUsuario(usuario, contrasenia,usuariosFile);
                    if (datosUsuario!=null) {
                        loggedIn = true;
                        
                        String nombreUsuario = datosUsuario[0];
                        String apellidoUsuario = datosUsuario[1];
                        String fechaNacimientoUsuario = datosUsuario[2];
                        String dpiUsuario = datosUsuario[3];
                        String correoUsuario = datosUsuario[4];
                        String contraseniaUsuario = datosUsuario[5];
                        
                        // Crear una instancia de Usuario
                        user = new Usuario(nombreUsuario, apellidoUsuario, fechaNacimientoUsuario, dpiUsuario, correoUsuario, contraseniaUsuario, null, null);
                        
                    }else {
                        System.out.println("");
                        System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
                    }

                    if (loggedIn == true) { // Si se inició sesión
                        while (loggedIn) {
                            System.out.println("");
                            System.out.println("Bienvenido " + user.getNombre()+" " +user.getApellido());

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


                                    System.out.print("Ingresa el nombre del mes para el presupuesto: ");
                                    String nombrePresupuesto = scanner.nextLine();

                                    boolean validacionPresupuesto = fileManagement.verificarNombrePresupuesto(presupuestoUsuarios, user, nombrePresupuesto);
                                    

                                    if(!validacionPresupuesto){
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("Lo siento, ya existe un presupuesto del mismo usuario con el mismo nombre");
                                        break;
                                    }

                                    fechaCreacion = LocalDate.now();

                                    //creacion del presupuesto
                                    Presupuesto presupuesto = new Presupuesto(nombrePresupuesto, fechaCreacion, null);

                                    //Datos para el nuevo ingreso de dinero
                                    System.out.println("Ingrese el monto total de ingresos que tendrá");
                                    montoIngresos = scanner.nextInt();scanner.nextLine();

                                    System.out.println("Ingrese la categoria del ingreso");
                                    categoriaIngreso = scanner.nextLine();

                                    System.out.println("Ingrese una pequeña descripción del ingreso, a que será destinado o de donde proviene");
                                    descripcionIngreso = scanner.nextLine();

                                    System.out.println("Ingrese la fecha del monto que ingresará en formato YYYY-MM-DD");
                                    fechaMonto = scanner.nextLine();

                                    fechaIngreso = LocalDate.parse(fechaMonto);



                                    movimientos.add(new Movimiento(montoIngresos, categoriaIngreso, descripcionIngreso, fechaIngreso,"Ingreso"));

                                    presupuesto.setMovimientos(movimientos);

                                    user.setPresupuesto(presupuesto);

                                    System.out.println("Desea ingresar sus categorias a planificar? 1-Si ; 2-No");
                                    opcion2 = scanner.nextInt();scanner.nextLine();

                                    switch (opcion2) {
                                        case 1:
                                            while (categoriasCheck) {
                                            
                                                System.out.println("Ingrese el nombre de la categoria");
                                                categoriaNombre = scanner.nextLine();
                                                categoriasUsuario.add(categoriaNombre);

                                                System.out.println("Ingrese el monto");
                                                categoriaMonto = scanner.nextInt();scanner.nextLine();


                                                System.out.println("Ingrese una pequeña descripcion");
                                                descripcionCategoria = scanner.nextLine();

                                                System.out.println("Ingrese la fecha planificada de este gasto en formato YYYY-MM-DD");
                                                fecha = scanner.nextLine();

                                                fechaCategoria = LocalDate.parse(fecha);


                                                movimientos.add(new Movimiento(categoriaMonto, categoriaNombre, descripcionCategoria, fechaCategoria, "Egreso"));
                                                presupuesto.setMovimientos(movimientos);
                                                user.setPresupuesto(presupuesto);


                                                categoriasCheck = volverAlMenu(scanner, " a ingresar otra categoria?");

                                            }
                                            user.setCategoriasUsuario(categoriasUsuario);
                                            
                                            StringJoiner joiner = new StringJoiner("/");
                                            for (String categoria : user.getCategoriasUsuario()) {
                                                joiner.add(categoria);
                                            }
                                            String categoriasPresupuesto = joiner.toString();

                                            String[] datosPresupuesto = {user.getNombre(), presupuesto.getNombre(), presupuesto.getFechaCreacion(), categoriasPresupuesto};

                                            fileManagement.ingresarNuevoPresupuestoUsuario(datosPresupuesto,presupuestoUsuarios);







                                            //meter aqui toda la info a un nuevo csv del presupuesto del usuario
                                            break;
                                        case 2:
                                            loggedIn = false;
                                            break;
                                    
                                        default:
                                            break;
                                    }


                                    if(!loggedIn){
                                        break;
                                    }
                                    loggedIn = volverAlMenu(scanner, " a ingresar otra opción? ");
                                    break;
                                    
                                case 2: // Ingreso post-mes
                                    if (loggedIn) {
                                        System.out.println("Ingreso del presupuesto ejecutado");
                                
                                        // Recorre las categorías del usuario y permite al usuario seleccionar una
                                        if (user.getCategoriasUsuario()== null || user.getCategoriasUsuario().isEmpty()) {
                                            System.out.println("No tiene categorías planificadas. Por favor, planifique categorías primero.");
                                        } else {
                                            
                                            System.out.println("Seleccione una categoría para el ingreso ejecutado:");
                                            for (int i = 0; i < user.getCategoriasUsuario().size(); i++) {
                                                System.out.println((i + 1) + ": " + user.getCategoriasUsuario().get(i));
                                            }
                                
                                            int categoriaSeleccionada = scanner.nextInt();
                                            scanner.nextLine();
                                
                                            if (categoriaSeleccionada >= 1 && categoriaSeleccionada <= user.getCategoriasUsuario().size()) {
                                                System.out.print("Ingresa el monto ejecutado: ");
                                                int montoEjecutado = scanner.nextInt();scanner.nextLine();

                                                System.out.print("Ingresa una pequeña descripcion del egreso: ");
                                                String descripcionEgreso = scanner.nextLine();
                                
                                                System.out.print("Ingresa la fecha del monto ejecutado (en formato YYYY-MM-DD): ");
                                                String fechaMontoEjecutado = scanner.nextLine();
                                                LocalDate fechaMontoEgreso = LocalDate.parse(fechaMontoEjecutado);
                                                

                                                Movimiento movimientoEjecutado;
                                

                                                movimientoEjecutado = new Movimiento(montoEjecutado, user.getCategoriasUsuario().get(categoriaSeleccionada - 1), descripcionEgreso, fechaMontoEgreso, "Egreso");
                                
                                                user.getPresupuesto().getMovimientos().add(movimientoEjecutado);
                                
                                                System.out.println("Monto ejecutado ingresado con éxito en la categoría " + user.getCategoriasUsuario().get(categoriaSeleccionada - 1));
                                            } else {
                                                System.out.println("Opción inválida. Seleccione una categoría válida.");
                                            }
                                        }
                                    } else {
                                        System.out.println("Debe iniciar sesión para ingresar presupuesto ejecutado.");
                                    }
                                    if (user.getCategoriasUsuario() != null && !user.getCategoriasUsuario().isEmpty()) {
                                        loggedIn = volverAlMenu(scanner, " a ingresar otra opción? ");
                                    } else {
                                        loggedIn = true; // Regresar al menú principal
                                    }
                                
                                    break;
/* 
                                case 3: // Balance
                                    user.getPresupuesto().calcularBalance();
                                    System.out.println("Balance actual: " + user.getPresupuesto().getBalance());
                                
                                    loggedIn = volverAlMenu(scanner, " a ingresar otra opción? ");
                                    break;
                                case 4: // Consulta de saldo por fechas
                                    System.out.println("Ingrese la fecha de inicio (en formato YYYY-MM-DD):");
                                    String fechaInicioStr = scanner.nextLine();
                                    Date fechaInicio = user.getPresupuesto().parsearFecha(fechaInicioStr);
                                
                                    System.out.println("Ingrese la fecha de fin (en formato YYYY-MM-DD):");
                                    String fechaFinStr = scanner.nextLine();
                                    Date fechaFin = user.getPresupuesto().parsearFecha(fechaFinStr);
                                
                                    user.getPresupuesto().consultarSaldosPorFechas(fechaInicio, fechaFin);
                                
                                    loggedIn = volverAlMenu(scanner, " a ingresar otra opción? ");
                                    break;
                                */
                                case 5: // Consejos extras
                                
                                    loggedIn = volverAlMenu(scanner, " a ingresar otra opción? ");
                                    break;
                                
                                case 6: // Salir
                                    loggedIn = false;
                                    break;
                                case 0:
                                    loggedIn = false;
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

                    String[] datosusuario = ingresarNuevoUsuario(scanner,fileManagement,usuariosFile);
                    usuarios.add(new Usuario(datosusuario[0], datosusuario[1], datosusuario[2], datosusuario[3], datosusuario[4], datosusuario[5], null, null));
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

    public static String[] ingresarNuevoUsuario(Scanner scanner,FileManagement fileManagement,File usuariosFile){
        String nombre, apellido, fechaNac, dpi, correo, contrasenia,contraseniaEscript;


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

        contraseniaEscript = generarHashMD5(contrasenia);
        String[] datosUsuario = {nombre, apellido, fechaNac, dpi, correo, contraseniaEscript};
        fileManagement.ingresarNuevoUsuario(datosUsuario, usuariosFile);
        System.out.println("Su nombre de usuario es: "+nombre);
        return datosUsuario;
    }

    public static String[] autenticarUsuario(String usuario, String contrasenia, File nombreFile) {
        String contraseniaEncrip = generarHashMD5(contrasenia);
        

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datosUsuario = line.split(",");

                if (datosUsuario.length > 5) {
                    String nombreEnArchivo = datosUsuario[0];
                    String contraseniaEnArchivo = datosUsuario[5];
                    if (nombreEnArchivo.equals(usuario) && contraseniaEnArchivo.equals(contraseniaEncrip)) {
                        return datosUsuario;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void printMenu() {
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("            Menú Principal");
        System.out.println("*************************************");
        System.out.println("Ingrese la opción que desee:");
        System.out.println("1: Ingresar presupuesto planificado");
        System.out.println("2: Ingresar presupuesto ejecutado");
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
        System.out.print("Opcion: ");
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
