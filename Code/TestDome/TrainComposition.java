package TestDome;

import java.util.*;

public class TrainComposition {
    Deque<Integer> comp = new LinkedList<>();
  
    public void attachWagonFromLeft(int wagonId) {
        comp.addFirst(wagonId);
    }

    public void attachWagonFromRight(int wagonId) {
        comp.addLast(wagonId);
    }

    public int detachWagonFromLeft() {
        return comp.pollFirst();
    }

    public int detachWagonFromRight() {
        return comp.pollLast();
    }

    public static void main(String[] args) {
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(7);
        train.attachWagonFromLeft(13);
        System.out.println(train.detachWagonFromRight()); // 7 
        System.out.println(train.detachWagonFromLeft()); // 13
    }
}