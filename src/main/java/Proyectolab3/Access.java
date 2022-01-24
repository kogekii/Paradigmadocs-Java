package Proyectolab3;

/**
 * Clase de representar al permiso que posee cierto usuario dentro de un documento
 */
public class Access {
    private String user;
    private String acces;

    /**
     * crea un objeto de tipo acceso
     * @param u se refiere al nombre del usuario
     */
    public Access(String u){
        this.user = u;
    }

    /**
     * se encarga de actualizar el permiso a el usuario
     * @param permiso es el tipo de permiso a asignar
     */
    public void actualizar_permisos(String permiso){
        this.acces = permiso;
    }

    /**
     * Metodo que devuleve el nombre de usuario asociado a este permiso
     * @return devuleve el nombre de usuario asociado a este permiso
     */
    public String Getuser(){
        return user;
    }

    /**
     * Metodo que resulve si el permiso un acceso asignado
     * @return true or false
     */
    public boolean access_define(){
        if (this.acces == null){
            return true;
        }
        return false;
    }

    /**
     * Metodo que devuelve al acceso del permiso
     * @return devuelve al acceso del permiso
     */
    public String Getacces(){
        return acces;
    }
}
