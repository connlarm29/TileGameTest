package main;

public class Item {

    private int ID;
    private String name = "";

    public Item(int id){
        ID = id;

        if(ID == 0){
            name = "Cactus";
        }else if(ID == 1){
            name = "Rock";
        }else if(ID == 2){
            name = "Pine Tree";
        }else if(ID == 3){
            name = "Tree";
        }else if(ID == 4){
            name = "Spike Trap";
        }else if(ID == 5){
            name = "Table";
        }else if(ID == 6){
            name = "Red Bricks";
        }else if(ID == 7){
            name = "Candle";
        }else if(ID == 8){
            name = "Blue Bricks";
        }
    }

    public int getID(){
        return ID;
    }

    public String toString(){
        return ""+ name;
    }
}
