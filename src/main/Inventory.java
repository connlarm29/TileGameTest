package main;

import java.util.ArrayList;

public class Inventory {

    ArrayList[] idlist = new ArrayList[9];

    ArrayList<Item> inventory = new ArrayList<Item>();

    public Inventory() {
        inventory.clear();
    }

    public void add(int ID) {
        inventory.add(new Item(ID));
        System.out.println("added item with id of " + ID);
    }

    public String toString() {

        return inventory.toString();
    }

}
