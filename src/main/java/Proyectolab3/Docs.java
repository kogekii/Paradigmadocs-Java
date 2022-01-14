package Proyectolab3;
import java.util.ArrayList;

public class Docs {
    public String namedoc;
    private int ID;
    private String textdoc;
    //Fecha creacion;
    //Fecha modificacion;
    String creador;
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
        Permisos npR = new Permisos(creator.GetUser(), "R");
        Permisos npW = new Permisos(creator.GetUser(), "W");
        Permisos npC = new Permisos(creator.GetUser(), "C");
        permisos.add(npR);
        permisos.add(npC);
        permisos.add(npW);

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
}
