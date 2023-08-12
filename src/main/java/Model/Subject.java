package Model;

public class Subject {
    private String name;

    private int id;

    public Subject(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }
}