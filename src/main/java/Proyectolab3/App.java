package Proyectolab3;
//import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){

        //Scanner sn = new Scanner(System.in);
        boolean salir = false;
        System.out.println("Introduzca el nombre de la plataforma: ");
        Scanner sc = new Scanner(System.in);
        String platformname = sc.nextLine();
        Editor p = new Editor();
        p.Crear_plataforma(platformname, 7, 1, 2022);
        while(!salir){

            System.out.println("1. Registrar un usuario");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Salir");
            Scanner op = new Scanner(System.in);
            System.out.println("Introduzca la operacion: ");
            int opcion = op.nextInt();

            switch(opcion){
                case 1:
                    System.out.println("Introduzca el nombre de usuario: ");
                    String Username = sc.nextLine();
                    System.out.println("Introduzca la contraseña: ");
                    String Pass = sc.nextLine();
                    //p.Register(Username, Pass);
                    if (p.Register(Username, Pass)){
                        System.out.println("Usuario registrado con exito!!");
                    }else{
                        System.out.println("Nombre de usuario ya registrado con anterioridad");
                    }
                    break;
                case 2:
                    System.out.println("Introduzca el nombre de usuario: ");
                    Username = sc.nextLine();
                    System.out.println("Introduzca la contraseña: ");
                    Pass = sc.nextLine();
                    if (p.Login(Username, Pass)){
                        boolean out = false;
                        while(!out) {
                            int loption;
                            System.out.println("1. Crear documento");
                            System.out.println("2. Compartir documento");
                            System.out.println("3. Agregar contenido a un documento");
                            System.out.println("4. Restaurar version de un documento");
                            System.out.println("5. Revocar acceso a un documento");
                            System.out.println("6. Buscar en los documentos");
                            System.out.println("7. Visualizar docuementos");
                            System.out.println("8. Cerrar sesion");
                            Scanner lo = new Scanner(System.in);
                            System.out.println("Introduzca la operacion: ");
                            loption = lo.nextInt();
                            switch (loption) {
                                case 1:
                                    System.out.println("Crear documento");
                                    break;
                                case 2:
                                    System.out.println("Compartir documento");
                                    break;
                                case 3:
                                    System.out.println("Agregar contenido a un documento");
                                    break;
                                case 4:
                                    System.out.println("Restaurar version de un documento");
                                    break;
                                case 5:
                                    System.out.println("Revocar acceso a un documento");
                                    break;
                                case 6:
                                    System.out.println("Buscar en los documentos");
                                    break;
                                case 7:
                                    System.out.println("Visualizar docuementos");
                                    break;
                                case 8:
                                    System.out.println("Cerrar sesion");
                                    out = true;
                                    break;
                                default:
                                    System.out.println("opcion no valida");

                            }
                        }
                    }
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("operacion no valida");
            }
        }

    }
}
