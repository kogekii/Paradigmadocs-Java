package Proyectolab3;
import java.util.ArrayList;


public class Editor {
    String name;
    ArrayList<User> usuarios;
    int countuser = 0;
    ArrayList<Docs> documentos;
    int countdoc = 0;
    User activo;
    Fecha creacion;
    
    
    
    public void Crear_plataforma(String name, int day, int month, int year){
        this.name = name;
        this.usuarios = new ArrayList<>();
        this.documentos = new ArrayList<>();
        Fecha date = new Fecha();
        date.Crear_fecha(day, month, year);
        this.creacion = date;
    }
    
    public boolean buscar_usuario(String name){
        for (int i = 0; i < this.countuser ; i++){
            if (name.equals(this.usuarios.get(i).GetUser())){
                return true;
            }
        }
        return false;
    }
    
    public void Register(String name, String pass){
        if (!buscar_usuario(name)){
            User nuser = new User(this.countuser, name, pass);
            this.usuarios.add (nuser); 
            this.countuser = this.countuser + 1;
        }      
    }         
}
