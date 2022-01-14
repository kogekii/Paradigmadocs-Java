package Proyectolab3;
import java.util.ArrayList;

public class Docs {
    public String namedoc;
    private int ID;
    private String textdoc;
    //Fecha creacion;
    //Fecha modificacion;
    private String creador;
    private ArrayList<Permisos> permisos;
    //ArrayList<Integer> oldverions;

    public Docs(int ID, String docname, String contentdoc, User creator){
        this.ID = ID;
        this.namedoc = docname;
        this.textdoc = contentdoc;
        //this.creacion = datedoc;
        //this.modificacion = datedoc;
        this.creador = creator.GetUser();
        permisos = new ArrayList<>();
        agregarpermiso(this.creador, "W");
        agregarpermiso(this.creador, "R");
        agregarpermiso(this.creador, "C");

    }


    public int GetID(){
        return ID;
    }
    public  String Getdocname(){return namedoc;}
    public String Gettextdoc(){
        return textdoc;
    }

    public void Settextdoc(String text){
        this.textdoc = text;
    }

    public ArrayList<Permisos> Getpermisos(){
        return permisos;
    }

    public void Setpermisos(Permisos p){
        this.permisos.add(p);
    }

    public boolean userwithacces (String u){
        for (int i = 0; i < permisos.size(); i++){
            if (u.equals(permisos.get(i).Getuser())){
                return true;
            }
        }
        return false;
    }
    public boolean fullacces (String u) {
        for (int i = 0; i < this.permisos.size(); i++) {
            if (u.equals(permisos.get(i).Getuser())){
                if (permisos.get(i).sizearray() == 3){
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    public boolean agregarpermiso (String u, String acces){
        if (this.userwithacces(u)){
            if (!fullacces(u)) {
                for (int i = 0; i < permisos.size(); i++) {
                    if (u.equals(permisos.get(i).Getuser())) {
                        if (!permisos.get(i).Getacces().contains(acces)){
                            permisos.get(i).actualizar_permisos(acces);
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            }
            return false;
        }else{
            Permisos newacces = new Permisos(u);
            this.permisos.add(newacces);
            agregarpermiso(u,acces);
            return true;
        }
    }
}
