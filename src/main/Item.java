package main;

public class Item {

    private int ID;

    public Item(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

    public String toString(){
        return ""+ID;
    }
}
