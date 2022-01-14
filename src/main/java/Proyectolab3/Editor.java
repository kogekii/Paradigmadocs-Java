package Proyectolab3;
import java.util.ArrayList;
import java.util.Scanner;


public class Editor {
    private String name;
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

    public boolean verificar_credencial(String name, String pass){
        for (int i = 0; i < this.countuser ; i++){
            if (name.equals(this.usuarios.get(i).GetUser())){
                if (pass.equals(this.usuarios.get(i).GetPass())){
                    return true;
                }
            }
        }
        return false;
    }

    public User Retornar_usuario(String name, String pass){
        if (verificar_credencial(name, pass)){
            for (int i = 0; i < this.countuser ; i++){
                if (name.equals(this.usuarios.get(i).GetUser())){
                    if (pass.equals(this.usuarios.get(i).GetPass())){
                        return this.usuarios.get(i);
                    }
                }
            }
        }

        return this.activo;
    }


    public boolean Register(String name, String pass){
        if (!buscar_usuario(name)){
            User nuser = new User(this.countuser, name, pass);
            this.usuarios.add (nuser);
            this.countuser = this.countuser + 1;
            return true;
        }
        return false;
    }

    public boolean Login(String name, String pass){
        if (verificar_credencial(name, pass)){
            this.activo = Retornar_usuario(name, pass);
            return true;
        }
        return  false;
    }

    public void create(String namedoc, String contentdocs){
        Docs ndoc = new Docs(this.countdoc, namedoc, contentdocs, this.activo);
        this.documentos.add(ndoc);
    }

    public void share(String )
}