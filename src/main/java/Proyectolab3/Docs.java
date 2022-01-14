package Proyectolab3;
import java.util.ArrayList;

public class Docs {
    int ID;
    String namedoc;
    String textdoc;
    Fecha creacion;
    Fecha modificacion;
    String creador;
    ArrayList<Permisos> permisos;
    //ArrayList<Integer> oldverions;

    public Docs(int ID, String docname, String contentdoc, User creator){
        this.ID = ID;
        this.namedoc = docname;
        this.textdoc = contentdoc;
        //this.creacion = datedoc;
        //this.modificacion = datedoc;
        this.creador = creator.GetUser();
        permisos = new ArrayList<>();
        Permisos npR = new Permisos(creator, "R");
        Permisos npW = new Permisos(creator, "W");
        Permisos npC = new Permisos(creator, "C");
        permisos.add(npR);
        permisos.add(npC);
        permisos.add(npW);

    }
}
