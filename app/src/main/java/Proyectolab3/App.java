
package Proyectolab3;
//import java.util.ArrayList;

//import java.util.ArrayList;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args){
        
        //Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        while(!salir){
            System.out.println("1. Crear una plataforma");
            System.out.println("2. Registrar un usuario");
            System.out.println("3. Iniciar sesion");
            System.out.println("4. Salir");
        
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la operacion a realizar: "));
        
            switch(opcion){
                case 1:
                    Editor p = new Editor();
                    p.Crear_plataforma("panda", 07, 01, 2022);
                    break;
                case 2:
                    System.out.println("operacion 2");
                    break;
                case 3:
                    System.out.println("operacion 3");
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("operacion no valida");
            }
        }
        
        //p.Register("andy", "hola123");
        //p.Register("Ale", "sdasd");
        //System.out.printf("%d" , p.usuarios.get(0).GetID()); 
    } 
}
