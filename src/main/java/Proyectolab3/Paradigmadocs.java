package Proyectolab3;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Clase encargada de manejar toda la plataforma de documentos
 */
public class Paradigmadocs {
    private String name;
    private ArrayList<User> usuarios;
    private ArrayList<Docs> documentos;
    private User activo;
    private LocalDate creacion;


    /**
     * Metodo encargado de crear una plataforma de documentos
     * @param name nombre de la plataforma
     */
    public void Crear_plataforma(String name){
        this.name = name;
        this.usuarios = new ArrayList<>();
        this.documentos = new ArrayList<>();
        this.creacion = LocalDate.now();
    }

    /**
     * Metodo que devuelve a el usuario activo
     * @return devuelve a el usuario activo
     */
    public User getActivo() {
        return activo;
    }

    /**
     * Metodo encargado de devolver el nombre de la plataforma
     * @return devolver el nombre de la plataforma
     */
    public String getname() {return name;}
    /**
     * Metodo que verifica la existencia de un usuario dentro de la plataforma
     * @param name nombre del usuario
     * @return true or false
     */
    public boolean buscar_usuario(String name){
        for (int i = 0; i < this.usuarios.size() ; i++){
            if (name.equals(this.usuarios.get(i).GetUser())){
                return true;
            }
        }
        return false;
    }

    /**
     * metodo encargado de validar la existencia de un documento
     * @param id id del documento
     * @return true or false
     */
    public boolean Validar_documento(int id){
        if (this.documentos.size() < id){
            return false;
        }
        return true;
    }

    /**
     * Metodo encargado de verificar la credencial de un usuario
     * @param name nombre del usuario
     * @param pass clave del usuario
     * @return true or false
     */
    public boolean authentication(String name, String pass){
        for (int i = 0; i < this.usuarios.size() ; i++){
            if (name.equals(this.usuarios.get(i).GetUser())){
                if (pass.equals(this.usuarios.get(i).GetPass())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo encargado de verificar si el nombre del usuario ya esta ocupado
     * @param name nombre del usuario
     * @return true or false
     */
    public boolean authentication(String name){
        for (int i = 0; i < this.usuarios.size() ; i++){
            if (name.equals(this.usuarios.get(i).GetUser())){
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que devuelve a un usuario dentro de el arraylist usuario
     * @param name nombre del usuario
     * @return devuelve a un usuario dentro de el arraylist usuario
     */
    public User Retornar_usuario(String name){
        if (buscar_usuario(name)){
            for (int i = 0; i < this.usuarios.size() ; i++){
                if (name.equals(this.usuarios.get(i).GetUser())){
                    return this.usuarios.get(i);
                }
            }
        }
        return this.activo;
    }

    /**
     * Metodo que devuelve a un documento dentro de el arraylist documentos
     * @param iddoc id del documento
     * @return devuelve a un documento dentro de el arraylist documentos
     */
    public Docs retornar_doc(int iddoc) {
        for (int i = 0; i < this.documentos.size(); i++){
            if (iddoc == this.documentos.get(i).GetID()){
                return this.documentos.get(i);
            }
        }
        return null;
    }

    /**
     * Metodo que registra a un usuario dentro de la plataforma
     * @param name nombre del usuario
     * @param pass contraseña del usuario
     * @return true or false
     */
    public boolean Register(String name, String pass){
        if (authentication(name)){
            User nuser = new User(this.usuarios.size(), name, pass);
            this.usuarios.add (nuser);
            return true;
        }
        return false;
    }

    /**
     * Metodo que permite el inicio de sesion de un usuario ya registrado
     * @param name nombre del usuario
     * @param pass contraseña del usuario
     * @return true or false
     */
    public boolean Login(String name, String pass){
        if (authentication(name, pass)){
            this.activo = Retornar_usuario(name);
            return true;
        }
        return  false;
    }

    /**
     * metodo que cierra la sesion del usuario
     */
    public void logout(){
        this.activo = null;
    }

    /**
     * Metod oencargado de crear un nuevo documento en la plataforma
     * @param namedoc nombre del documento
     * @param contentdocs contenido del documento
     */
    public void create(String namedoc, String contentdocs){
        Docs ndoc = new Docs(this.documentos.size(), namedoc, contentdocs, this.activo, LocalDate.now());
        this.documentos.add(ndoc);
        this.activo.ndocs(ndoc.GetID());
    }

    /**
     * Metodo encargado de compartir un documento con otro usuario
     * @param iddoc id del documento
     * @param usuario usuario a compartir
     * @param acces tipo de acceso a asignar
     * @return
     */
    public boolean share(int iddoc, String usuario, String acces) {
        if (this.Validar_documento(iddoc)) {
            if ((this.activo.verificar_permisos("any", iddoc, null)) || (this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador()))) { //comprueba que el usuario que va a compartir tenga algun tipo de acceso
                if (acces.equals("W") || acces.equals("R") || acces.equals("C")) {//comprueba que el tipo de permiso a asignar se W,R O C
                    if (buscar_usuario(usuario)) {
                        if (this.documentos.get(iddoc).agregarpermiso(usuario, acces)) {
                            this.Retornar_usuario(usuario).ndocs(this.documentos.get(iddoc).GetID());
                            return true;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /**
     * Metodo encargado de modificar el contenido de un documento
     * @param iddoc id del documento
     * @param newtext contenido a agregar
     * @param p Plataforma
     * @return true or false
     */
    public boolean add (int iddoc, String newtext, Paradigmadocs p) {
        if (this.Validar_documento(iddoc)) {
            if ((this.activo.verificar_permisos("W", iddoc, p)) || (this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador()))) {//valida que el usuario tenga permisos de escritura
                String olddoc = this.documentos.get(iddoc).Gettextdoc();
                this.documentos.get(iddoc).Getolddocs().add(olddoc);
                String oldtext = this.documentos.get(iddoc).Gettextdoc();
                String New = oldtext + " " + newtext;
                this.documentos.get(iddoc).Settextdoc(New);
                this.documentos.get(iddoc).Setlocatime(LocalDate.now());
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Metodo encargado de restaurar una version del documento
     * @param iddoc id del documento
     * @param idolddoc id de la version del documento
     * @return true or false
     */
    public boolean rollback(int iddoc, int idolddoc) {
        if ((this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador())) && (this.Validar_documento(iddoc)) && (this.documentos.get(iddoc).validar_olddoc(idolddoc))) { //comprueba si el usuario es el creador del documento
            String old = this.documentos.get(iddoc).Gettextdoc();
            this.documentos.get(iddoc).Settextdoc(this.documentos.get(iddoc).Getolddocs().get(idolddoc));
            this.documentos.get(iddoc).Getolddocs().remove(idolddoc);
            this.documentos.get(iddoc).Getolddocs().add(old);
            this.documentos.get(iddoc).Setlocatime(LocalDate.now());
            return true;
        }
        return false;
    }

    /**
     * Metodo encargado de quitar todos los permisos de un documento
     * @param iddoc id del documento
     * @return true or false
     */
    public boolean revokeAccess (int iddoc){
        if ((this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador())) && (this.Validar_documento(iddoc))){// comprueba de que el usuario sea el creador del documento
            this.documentos.get(iddoc).deleteallpermise();
            return true;
        }
        return false;
    }

    /**
     * Metodo encargado de buscar una palabra dentro de todos los documentos que le usuario tenga acceso
     * @param frase palabra a buscar
     * @param p plataforma de documento
     */
    public void search (String frase, Paradigmadocs p){
        for (int i = 0; i < this.documentos.size(); i++){
            if ((this.activo.GetUser().equals(this.documentos.get(i).Getcreador())) || (this.activo.verificar_permisos("any", i, p))){ //comprueba que el usuario tenga cualquier tipo de acceso
                if (this.documentos.get(i).Gettextdoc().contains(frase)){
                    System.out.print(this.documentos.get(i).GetID() );
                    System.out.print(" ");
                    System.out.print(this.documentos.get(i).Getdocname());
                    System.out.print(" ");
                    System.out.println(this.documentos.get(i).Gettextdoc());
                }
            }
        }
    }

    /**
     * Metodo que convierte lo contenido en el paradigmadocs en un string
     * @return un string del paradigmadocs
     */
    public String edittostring(){
        if (this.activo != null){ //en el caso de que haya un usuario logeado
            String doc = String.valueOf(this.activo.GetID()) + " " +this.activo.GetUser() + "\n";
            for (int i = 0; i < this.activo.Getdocuments().size(); i++){
                doc = doc + "ID: " + retornar_doc(this.activo.Getdocuments().get(i)).GetID() + "\n";
                doc  = doc + "Nombre del doc: " + retornar_doc(this.activo.Getdocuments().get(i)).Getdocname() + "\n";
                doc  = doc + "contenido: " + retornar_doc(this.activo.Getdocuments().get(i)).Gettextdoc() + "\n";
                doc = doc + "Creador: " + retornar_doc(this.activo.Getdocuments().get(i)).Getcreador() + "\n";
                doc = doc + "cantidad de versiones: " + retornar_doc(this.activo.Getdocuments().get(i)).Getolddocs().size() + "\n";
                doc = doc + "usuarios con acceso: " + retornar_doc(this.activo.Getdocuments().get(i)).accesstostring() + "\n";
                doc = doc + "\n" + "---------------" + "\n";
            }
            return doc;
        }else{ // en el caso de que no haya ningun usuario logeado
            String doc = new String("");
            for (int i = 0; i < this.documentos.size(); i++){
                doc = doc + "ID: " + this.documentos.get(i).GetID() + "\n";
                doc = doc + "nombre del doc: "+this.documentos.get(i).Getdocname() + "\n";
                doc = doc + "contenido: "+ this.documentos.get(i).Gettextdoc() + "\n";
                doc = doc + "Creador: " + this.documentos.get(i).Getcreador() + "\n";
                doc = doc + "cantidad de versiones: " + this.documentos.get(i).Getolddocs().size() + "\n";
                doc = doc + "\n" + "---------------" + "\n";
            }
            return doc;
        }
    }

    /**
     * Metodo encargado de imprimir un string
     * @param x string a imprimir
     */
    public void printtostring(String x){
        System.out.println(x);
    }

    /**
     * Metodo encargado de imprimir lo contenido en una plataforma de documentos
     */
    public void visualize(){
        printtostring(edittostring());
    }

}