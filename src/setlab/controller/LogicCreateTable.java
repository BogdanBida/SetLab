package setlab.controller;

import java.util.ArrayList;
import setlab.cores.LogicCore.TableTruth;

public class LogicCreateTable {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("x");
        list.add("y");
        list.add("z");
        TableTruth t1 = new TableTruth(list);
        t1.printTable();
    }
    
    public static void printCol(int i, int h) {
        if (h == 2) {
            System.out.println(i % 2);
            return;
        }
        int t = h / 2;
        System.out.print((i / t) % 2 + " ");
        printCol(i,t);
    }
}
