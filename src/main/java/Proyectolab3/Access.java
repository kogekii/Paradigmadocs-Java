package Proyectolab3;
import java.util.ArrayList;

/**
 * Clase de representar al permiso que posee cierto usuario dentro de un documento
 */
public class Access {
    private String user;
    private ArrayList<String> acces;

    /**
     * crea un objeto de tipo acceso
     * @param u se refiere al nombre del usuario
     */
    public Access(String u){
        this.user = u;
        this.acces = new ArrayList<>();
    }

    /**
     * se encarga de actualizar el permiso en cuestion añadiendo nuevos al mismo usuario
     * @param permiso es el tipo de permiso a asignar
     */
    public void actualizar_permisos(String permiso){
        this.acces.add(permiso);
    }

    /**
     * Funcion que devuleve el nombre de usuario asociado a estos permisos
     * @return devuleve el nombre de usuario asociado a estos permisos
     */
    public String Getuser(){
        return user;
    }

    /**
     * Funcion que devuelve el largo de la lista de permisos
     * @return devuelve el largo de la lista de permisos
     */
    public Integer sizearray(){
        return acces.size();
    }

    /**
     * Funcion que devuelve la lista de permisos
     * @return devuelve la lista de permisos
     */
    public  ArrayList<String> Getacces(){
        return acces;
    }
}
