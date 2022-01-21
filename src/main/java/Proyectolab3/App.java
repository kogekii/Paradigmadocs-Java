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
        p.Register("andy", "hola123");
        p.Register("naya", "hola123");
        while(!salir){

            System.out.println("1. Registrar un usuario");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Visualizar documentos");
            System.out.println("4. Salir");
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
                            System.out.println("Sesion iniciada usuario: "+ Username);
                            System.out.println("1. Crear documento");
                            System.out.println("2. Compartir documento");
                            System.out.println("3. Agregar contenido a un documento");
                            System.out.println("4. Restaurar version de un documento");
                            System.out.println("5. Revocar acceso a un documento");
                            System.out.println("6. Buscar en los documentos");
                            System.out.println("7. Visualizar documentos");
                            System.out.println("8. Cerrar sesion");
                            Scanner lo = new Scanner(System.in);
                            System.out.println("Introduzca la operacion: ");
                            loption = lo.nextInt();
                            switch (loption) {
                                case 1:
                                    System.out.println("Ingrese el nombre del documento: ");
                                    String namedoc = sc.nextLine();
                                    System.out.println("Ingrese el contenido del documento: ");
                                    String contentdoc = sc.nextLine();
                                    p.create(namedoc, contentdoc);
                                    System.out.println("Documento creado con exito!");
                                    break;
                                case 2:
                                    System.out.println("indique el documento: ");
                                    int id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("a quien quieres compartir este documento?: ");
                                    String shareuser = sc.nextLine();
                                    System.out.println("asigne el permiso (W,R,C): ");
                                    String acces = sc.nextLine();
                                    if (p.share(id, shareuser, acces)){
                                        System.out.println("permiso asignado con exito");
                                    }else{
                                        System.out.println("permiso no asignado");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Ingrese el ID del documento");
                                    id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Ingrese el contenido nuevo");
                                    contentdoc = sc.nextLine();
                                    if (p.add(id, contentdoc, p)){
                                        System.out.println("Contenido agregado con exito");
                                    }else{
                                        System.out.println("Error al agregar el contenido");
                                    }
                                    break;
                                case 4:
                                    System.out.println("ingrese el id del documento");
                                    id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("ingrese version del documento a recuperar");
                                    int idold = sc.nextInt();
                                    sc.nextLine();
                                    if (p.rollback(id, idold)){
                                        System.out.println("Contenido restaurado con exito");
                                    }else{
                                        System.out.println("Contenido no restaurado");
                                    }
                                    break;
                                case 5:
                                    System.out.println("ingrese el id del documento");
                                    id = sc.nextInt();
                                    sc.nextLine();
                                    if (p.revokeAccess(id)){
                                        System.out.println("permisos revocados con exito");
                                    }else{
                                        System.out.println("permisos no revocados");
                                    }
                                    break;
                                case 6:
                                    System.out.println("Buscar en los documentos");
                                    break;
                                case 7:
                                    System.out.println(p.visualize());
                                    break;
                                case 8:
                                    System.out.println("Cerrar sesion");
                                    out = true;
                                    p.logout();
                                    break;
                                default:
                                    System.out.println("opcion no valida");

                            }
                        }
                    }else{
                        System.out.println("Usuario o contraseña incorrectas");
                    }
                    break;
                case 3:
                    System.out.println(p.visualize());
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("operacion no valida");
            }
        }

    }
}
