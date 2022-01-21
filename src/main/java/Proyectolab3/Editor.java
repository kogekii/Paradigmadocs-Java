package Proyectolab3;
import java.time.LocalDate;
import java.util.ArrayList;



public class Editor{
    private String name;
    private ArrayList<User> usuarios;
    private ArrayList<Docs> documentos;
    private User activo;
    private Fecha creacion;




    public void Crear_plataforma(String name, int day, int month, int year){
        this.name = name;
        this.usuarios = new ArrayList<>();
        this.documentos = new ArrayList<>();
        Fecha date = new Fecha();
        date.Crear_fecha(day, month, year);
        this.creacion = date;
    }

    public User getActivo() {
        return activo;
    }

    public boolean buscar_usuario(String name){
        for (int i = 0; i < this.usuarios.size() ; i++){
            if (name.equals(this.usuarios.get(i).GetUser())){
                return true;
            }
        }
        return false;
    }

    public boolean buscar_documento(int id){
        for (int i = 0; i < this.documentos.size() ; i++){
            if (id == this.documentos.get(i).GetID()){
                return true;
            }
        }
        return false;
    }

    public boolean verificar_credencial(String name, String pass){
        for (int i = 0; i < this.usuarios.size() ; i++){
            if (name.equals(this.usuarios.get(i).GetUser())){
                if (pass.equals(this.usuarios.get(i).GetPass())){
                    return true;
                }
            }
        }
        return false;
    }

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

    public Docs retornar_doc(int iddoc) {
        for (int i = 0; i < this.documentos.size(); i++){
            if (iddoc == this.documentos.get(i).GetID()){
                return this.documentos.get(i);
            }
        }
        return null;
    }

    public boolean Register(String name, String pass){
        if (!buscar_usuario(name)){
            User nuser = new User(this.usuarios.size(), name, pass);
            this.usuarios.add (nuser);
            return true;
        }
        return false;
    }

    public boolean Login(String name, String pass){
        if (verificar_credencial(name, pass)){
            this.activo = Retornar_usuario(name);
            return true;
        }
        return  false;
    }
    public void logout(){
        this.activo = null;
    }

    public void create(String namedoc, String contentdocs){
        Docs ndoc = new Docs(this.documentos.size(), namedoc, contentdocs, this.activo, LocalDate.now());
        this.documentos.add(ndoc);
        this.activo.ndocs(ndoc.GetID());
    }
    public boolean share(int iddoc, String usuario, String acces) {
        if ((this.activo.verificar_permisos("any", iddoc, null)) || (this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador()))){
            if (acces.equals("W") || acces.equals("R") || acces.equals("C")) {
                if (buscar_usuario(usuario)) {
                    if (this.documentos.get(iddoc).agregarpermiso(usuario, acces)){
                        this.Retornar_usuario(usuario).ndocs(this.documentos.get(iddoc).GetID());
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return  false;
    }

    public boolean add (int iddoc, String newtext, Editor p){
        if ((this.activo.verificar_permisos("W", iddoc, p)) || (this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador()))){
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
    public boolean rollback(int iddoc, int idolddoc){
        if (this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador())){
            String old  = this.documentos.get(iddoc).Gettextdoc();
            this.documentos.get(iddoc).Settextdoc(this.documentos.get(iddoc).Getolddocs().get(idolddoc));
            this.documentos.get(iddoc).Getolddocs().remove(idolddoc);
            this.documentos.get(iddoc).Getolddocs().add(old);
            return true;
        }
        return false;
    }

    public boolean revokeAccess (int iddoc){
        if (this.activo.GetUser().equals(this.documentos.get(iddoc).Getcreador())){
            this.documentos.get(iddoc).deleteallpermise();
            return true;
        }
        return false;
    }

    public String visualize(){
        if (this.activo != null){
            String namedocs;
            String content;

            String doc = String.valueOf(this.activo.GetID()) + " " +this.activo.GetUser() + "\n";
            for (int i = 0; i < this.activo.Getdocuments().size(); i++){
                namedocs = "*"+retornar_doc(this.activo.Getdocuments().get(i)).Getdocname();
                content = " "+ retornar_doc(this.activo.Getdocuments().get(i)).Gettextdoc();
                doc = doc + namedocs + content + "\n";
            }
            return doc;
        }else{
            String namedocs;
            String content;
            String doc = new String("");
            for (int i = 0; i < this.documentos.size(); i++){
                namedocs = "*"+this.documentos.get(i).Getdocname();
                content = " "+ this.documentos.get(i).Gettextdoc();
                doc = doc + namedocs + content + "\n";
            }
            return doc;
        }
    }

}