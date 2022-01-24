package Proyectolab3;
import java.util.Scanner;

/**
 * clase creada para manejar el menu de la plataforma y sus interacciones
 */
public class Main {
    public static void main(String[] args){
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        Paradigmadocs p = new Paradigmadocs();
        p.Crear_plataforma("PandaDocs");
        //carga los parametros iniciales de la plataforma
        p.Register("user1", "pass1");
        p.Register("user2", "pass2");
        p.Register("user3", "pass3");
        p.Register("user4", "pass4");
        p.Register("user5", "pass5");
        p.Login("user1", "pass1");
        p.create("doc1", "documento 1");
        p.create("doc2", "documento 2");
        p.logout();
        p.Login("user2", "pass2");
        p.create("doc3", "documento 3");
        p.create("doc4", "documento 4");
        p.logout();
        p.Login("user3", "pass3");
        p.create("doc5", "documento 5");
        p.create("doc6", "documento 6");
        p.logout();
        p.Login("user4", "pass4");
        p.create("doc7", "documento 7");
        p.create("doc8", "documento 8");
        p.logout();
        p.Login("user5", "pass5");
        p.create("doc9", "documento 9");
        p.create("doc10", "documento 10");
        p.logout();
       //while utilizado para crear el menu de opciones
        while(!salir){
            System.out.println(p.getname());
            System.out.println("1. Registrar un usuario");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Visualizar documentos");
            System.out.println("4. Salir");
            Scanner op = new Scanner(System.in);
            System.out.println("Introduzca la operacion: ");
            int opcion = op.nextInt();
            //switch utilizado para menejar las opciones del menu
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
                       //while utilizado para manejar el menu del usuario
                        while(!out) {
                            int loption;
                            System.out.println(p.getname());
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
                            //switch utilizado para manejar las opciones del menu
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
                                    System.out.println("ingrese frase a buscar");
                                    contentdoc = sc.nextLine();
                                    p.search(contentdoc,p);
                                    break;
                                case 7:
                                    p.visualize();
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
                    p.visualize();
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
