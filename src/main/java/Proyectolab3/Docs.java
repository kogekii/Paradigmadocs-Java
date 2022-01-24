package Proyectolab3;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Docs va a representar a un documento dentro de la plataforma de documentos
 */
public class Docs{
    private String namedoc;
    private int ID;
    private String textdoc;
    private LocalDate creacion;
    private LocalDate modificacion;
    private String creador;
    private ArrayList<Access> permisos;
    private ArrayList<String> oldversions;


    /**
     * Crea un documento
     * @param ID representa el numero identifcador del documento, este es unico
     * @param docname representa el nombre del documento
     * @param contentdoc representa el contenido del documento
     * @param creator representa a el creador del documento
     * @param now representa la fecha actual en la que se creo el documento
     */
    public Docs(int ID, String docname, String contentdoc, User creator, LocalDate now){
        this.ID = ID;
        this.namedoc = docname;
        this.textdoc = contentdoc;
        this.creacion = now;
        this.creador = creator.GetUser();
        permisos = new ArrayList<>();
        this.oldversions = new ArrayList<>();
    }

    /**
     * Metodo que otorga el ID del documento
     * @return devuelve el ID del documento
     */
    public int GetID(){
        return ID;
    }

    /**
     *Metodo que devulve el nombre del documento
     * @return devuelve el nombre del documento
     */
    public  String Getdocname(){
        return namedoc;
    }

    /**
     * Metodo que devuelve el contenido del documento
     * @return devuelve el contenido del documento
     */
    public String Gettextdoc(){
        return textdoc;
    }

    /**
     * Metodo que devuleve al creador del documento
     * @return devuelve al creador del documento
     */
    public String Getcreador() {
        return creador;
    }

    /**
     * Metodo que devuelve la lista de permisos del documentto
     * @return devuelve la lista de permisos del documento
     */
    public ArrayList<Access> Getpermisos(){
        return permisos;
    }

    /**
     * Metodo que devuleve la lista de IDs de versiones anteriores
     * @return devuleve lista de IDs de versiones anteriores
     */
    public ArrayList<String> Getolddocs(){
        return oldversions;
    }

    /**
     * Metodo que cambia el valor contenido en el atributo textdoc (contenido del documento)
     * @param text nuevo contenido a asignar al documento
     */
    public void Settextdoc(String text){
        this.textdoc = text;
    }

    /**
     * Metodo que reestablece la fecha de la ultima modificacion
     * @param time nueva fecha de modificacion
     */
    public void Setlocatime(LocalDate time){
        this.modificacion = time;
    }


    /**
     * Metodo que determina si un usuario tiene cualquier tipo de acceso a el documento
     * @param u nombre del usuario
     * @return true or false
     */
    public boolean userwithacces (String u){
        for (int i = 0; i < permisos.size(); i++){
            if (u.equals(permisos.get(i).Getuser())){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que determina si el usuario ya tiene asignado permisos sobre el documento
     * @param u nombre del usuario
     * @return true or false
     */
    public boolean fullacces (String u) {
        for (int i = 0; i < this.permisos.size(); i++) {
            if (u.equals(permisos.get(i).Getuser())) {//compara si el nombre de usuario concuerda con el de la i posicion
                if (permisos.get(i).access_define()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }


    /**
     * Metodo que permite agregar permisos al documento
     * @param u nombre del usuario
     * @param access tipo de permiso a asignar
     * @return true or false
     */
    public boolean agregarpermiso (String u, String access){
        if (this.userwithacces(u)){
            if (fullacces(u)) {
                for (int i = 0; i < permisos.size(); i++) {
                    if (u.equals(permisos.get(i).Getuser())) {
                            permisos.get(i).actualizar_permisos(access);
                            return true;
                    }
                }
                return false;
            }
            return false;
        }else{
            Access newacces = new Access(u);
            this.permisos.add(newacces);
            agregarpermiso(u,access);
            return true;
        }
    }

    /**
     * Metodo que borra todos los permisos asignados en el documento
     */
    public void deleteallpermise(){
        this.permisos.clear();
    }

    /**
     * Metodo encargada de verificar si una version del documento existe
     * @param idolddoc ID de la version antigua del documento
     * @return true or false
     */
    public boolean validar_olddoc(int idolddoc){
        if (this.oldversions.size() < idolddoc){
            return false;
        }
        return true;
    }

    /**
     * Metodo que convierte la lista de permisos en un string
     * @return la lista de permisos convertida en un string
     */
    public String accesstostring(){
        String acces = "";
        for (int i = 0; i < this.permisos.size(); i++){
            acces = acces + this.permisos.get(i).Getuser() + ", ";
        }
        return  acces;
    }
}

