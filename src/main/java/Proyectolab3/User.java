package Proyectolab3;
import java.util.ArrayList;

/**
 * Clase que representa a los usuarios de la plataforma
 */
public class User extends Paradigmadocs {
    //Atributos
    private int ID;
    private String Username;
    private String Pass;
    private ArrayList<Integer> docs;

    /**\
     * Metodo que crea un usuario
     * @param ID es ID unico que le pertenece a cada usuario
     * @param name es el nombre de usuario
     * @param pass es la clave asociada a e usuario
     */
    public User(int ID, String name, String pass) {
        this.ID = ID;
        this.Username = name;
        this.Pass = pass;
        this.docs = new ArrayList<>();
    }

    /**
     * este metodo se encarga de añadir un nuevo ID de un documento a el usuario
     * @param id del documento
     */
    public void ndocs(int id){
        this.docs.add(id);
    }

    /**
     * Metodo que devuelve el nombre del usuario
     * @return devuelve el nombre del usuario
     */
    public String GetUser(){
        return Username;
    }

    /**
     * Metodo que devuelve la contraseña del usuario
     * @return devuelve la contraseña del usuario
     */
    public String GetPass(){
        return Pass;
    }

    /**
     * Metodo que devuelve el ID del usuario
     * @return devuelve el ID del usuario
     */
    public Integer GetID(){
        return ID;
    }

    /**
     * Metodo que devuelve la lista de documentos del usuario
     * @return devuelve la lista de documentos del usuario (IDs)
     */
    public ArrayList<Integer> Getdocuments(){
        return (docs);
    }

    /**
     * Metodo que verfica que tipo de permiso tiene un usuario sobre un documento
     * @param permise tipo de permiso a verifica
     * @param iddoc ID del documento a verificar
     * @param p Plataforma de documentos
     * @return True or false
     */
    public boolean verificar_permisos(String permise, int iddoc, Paradigmadocs p){
        if (permise.equals("any")){ //se ejecuta cuando no se necesita ningun permiso especifico
            for (int i = 0; i < this.docs.size(); i++){
                if (iddoc == this.docs.get(i)){
                    return true;
                }
            }
            return false;
        }else if(permise.equals("W")){// se ejecuta cuando se necesita poder escribir en el doc
            for (int i = 0; i < this.docs.size(); i++){//ciclo for que recorre la lista de documentos del usuario
                if (iddoc == this.docs.get(i)) {
                    for (int j = 0; j < p.retornar_doc(iddoc).Getpermisos().size(); j++){ //ciclo for que recorre la lista de permisos del documento
                        if (p.getActivo().GetUser().equals(p.retornar_doc(iddoc).Getpermisos().get(j).Getuser())){//compara si el permiso pertenece al usuario
                            if (p.retornar_doc(iddoc).Getpermisos().get(j).Getacces().equals(permise)){//compara si el acceso es de escritura
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
