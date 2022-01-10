package Proyectolab3;
import java.util.ArrayList;

public class User {
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

    public String GetUser(){
        return Username;
    }

    public String GetPass(){
        return Pass;
    }

    public Integer GetID(){
        return ID;
    }


}
