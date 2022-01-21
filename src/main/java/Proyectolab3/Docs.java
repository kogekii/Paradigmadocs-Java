package Proyectolab3;
import java.time.LocalDate;
import java.util.ArrayList;

public class Docs{
    public String namedoc;
    private int ID;
    private String textdoc;
    private LocalDate creacion;
    private LocalDate modificacion;
    private String creador;
    private ArrayList<Permisos> permisos;
    private ArrayList<String> oldversions;



    public Docs(int ID, String docname, String contentdoc, User creator, LocalDate now){
        this.ID = ID;
        this.namedoc = docname;
        this.textdoc = contentdoc;
        this.creacion = now;
        this.creador = creator.GetUser();
        permisos = new ArrayList<>();
        this.oldversions = new ArrayList<>();
    }


    public int GetID(){
        return ID;
    }
    public  String Getdocname(){return namedoc;}
    public String Gettextdoc(){
        return textdoc;
    }
    public String Getcreador() {return creador;}
    public void Settextdoc(String text){
        this.textdoc = text;
    }
    public void Setlocatime(LocalDate time){this.modificacion = time;}
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
    public void deleteallpermise(){
        this.permisos.clear();
    }

    public ArrayList<String> Getolddocs(){
        return oldversions;
    }


}
