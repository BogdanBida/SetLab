package setlab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore;

public class SintaxBinRel {

    public static ObservableList<Boolean> anLine = FXCollections.observableArrayList();

    public static String get(String command) {
        StringBuilder res = new StringBuilder(" >> ");
        String[] line = command.replaceAll(" ", "").split("=");
        BinRel R = new BinRel(line[0], line[1]);
        SetCore.SetObj O = BinRelCore.O(R);
        res.append(R.toString()).append("\n").append(" >> ");
        res.append(BinRelCore.D(R).toString()).append("\n").append(" >> ");
        res.append(BinRelCore.E(R).toString()).append("\n").append(" >> ");
        res.append(O.toString()).append("\n").append(" >> ");
        res.append(BinRelCore.Ident(O).toString()).append("\n").append(" >> ");
        res.append(BinRelCore.Reverse(R).toString()).append("\n").append(" >> ");
        res.append(BinRelCore.Composer(R).toString()).append("\n");

        anLine.add(BinRelCore.Refelex(R)); 
        anLine.add(BinRelCore.AntiRefelex(R));
        anLine.add(BinRelCore.Simetry(R));
        anLine.add(BinRelCore.AntiSimetry(R));
        anLine.add(BinRelCore.Asimetry(R));
        anLine.add(BinRelCore.Transity(R));
        return res.toString();
    }
}
