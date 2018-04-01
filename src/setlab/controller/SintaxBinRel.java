package setlab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore;

public class SintaxBinRel {

    public static ObservableList<Boolean> anLine = FXCollections.observableArrayList();

    public static String get(String command, BinRel R) {
        StringBuilder res = new StringBuilder("\t");
        SetCore.SetObj O = BinRelCore.O(R);
        res.append(R.toString()).append("\n").append("\t");
        res.append(BinRelCore.D(R).toString()).append("\n").append("\t");
        res.append(BinRelCore.E(R).toString()).append("\n").append("\t");
        res.append(O.toString()).append("\n").append("\t");
        res.append(BinRelCore.Ident(O).toString()).append("\n").append("\t");
        res.append(BinRelCore.Reverse(R).toString()).append("\n").append("\t");
        res.append(BinRelCore.Composer(R).toString()).append("\n");

        anLine.add(BinRelCore.Refelex(R)); 
        anLine.add(BinRelCore.AntiRefelex(R));
        anLine.add(BinRelCore.Simetry(R));
        anLine.add(BinRelCore.AntiSimetry(R));
        anLine.add(BinRelCore.Asimetry(R));
        anLine.add(BinRelCore.Transity(R));
        
        res.append("\n");
        return res.toString();
    }
}
