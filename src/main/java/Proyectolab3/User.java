package Proyectolab3;
import java.util.ArrayList;

public class User extends Paradigmadocs {
    //Atributos
    private int ID;
    private String Username;
    private String Pass;
    private ArrayList<Integer> docs;


    public User(int ID, String name, String pass) {
        this.ID = ID;
        this.Username = name;
        this.Pass = pass;
        this.docs = new ArrayList<>();
    }
    public void ndocs(int id){
        this.docs.add(id);
    }

    public String GetUser(){
        return Username;
    }

    public String GetPass(){
        return Pass;
    }

    public Integer GetID(){
        return ID;
    }
    public ArrayList<Integer> Getdocuments(){
        return (docs);
    }

    public boolean verificar_permisos(String permise, int iddoc, Paradigmadocs p){
        if (permise.equals("any")){
            for (int i = 0; i < this.docs.size(); i++){
                if (iddoc == this.docs.get(i)){
                    return true;
                }
            }
            return false;
        }else if(permise.equals("W")){
            for (int i = 0; i < this.docs.size(); i++){
                if (iddoc == this.docs.get(i)) {
                    for (int j = 0; i < p.retornar_doc(iddoc).Getpermisos().size(); j++){
                        if (p.getActivo().GetUser().equals(p.retornar_doc(iddoc).Getpermisos().get(j).Getuser())){
                            if (p.retornar_doc(iddoc).Getpermisos().get(j).Getacces().contains("W")){
                                return true;
                            }
                            return  false;

                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

}
