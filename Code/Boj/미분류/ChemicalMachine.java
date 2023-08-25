package Boj.미분류;

import java.util.ArrayList;
import java.util.List;

public class ChemicalMachine {
    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<String> result = new ArrayList<>();

    public void add(String potion) {
        list.add(potion);
    }

    public void applyHeat() {
        result.clear();
        if (list.size() == 2 && list.get(0).equals("GREEN") && list.get(1).equals("GREEN")) {
            list.clear();
            result.add("ORANGE");
        } else if (list.size() == 1 && list.get(0).equals("ORANGE")) {
            list.clear();
            list.add("RED");
            result.add("RED");
        } else if (list.size() == 2 && list.contains("GREEN") && list.contains("YELLOW")) {
            list.clear();
            result.add("BROWN");
        } else if (list.size() == 1 && list.get(0).equals("BROWN")) {
            list.clear();
            result.add("MAGENTA");
        } else {
            list.clear();
            result.add("UNKNOWN");
        }
    }

    public ArrayList<String> emptyMachine() {
        ArrayList<String> resultList = new ArrayList<>();
        resultList.addAll(list);
        list.clear();
        return resultList;
    }

    public static void main(String[] args) {
        ChemicalMachine machine = new ChemicalMachine();

        machine.add("GREEN");
        machine.add("YELLOW");
        machine.applyHeat();
        System.out.println(String.join(",", machine.emptyMachine())); // should print BROWN

        machine.add("RED");
        machine.add("YELLOW");
        machine.applyHeat();
        System.out.println(String.join(",", machine.emptyMachine())); // should print UNKNOWN
    }
}