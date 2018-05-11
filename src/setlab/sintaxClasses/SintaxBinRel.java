package setlab.sintaxClasses;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore;

public class SintaxBinRel {

    public static boolean[] internals = new boolean[6];

    public static String get(String command, BinRel R) {
        StringBuilder res = new StringBuilder("\n\t");
        res.append(R.toString()).append("\n").append("\t");
        res.append(BinRelCore.D(R).toString()).append("\n").append("\t");
        res.append(BinRelCore.E(R).toString()).append("\n").append("\t");
        res.append(BinRelCore.O(R).toString()).append("\n").append("\t");
        res.append(BinRelCore.Ident(R).toString()).append("\n").append("\t");
        res.append(BinRelCore.Reverse(R).toString()).append("\n").append("\t");
        res.append(BinRelCore.Composer(R,R).toString()).append("\n");

        internals[0] = BinRelCore.Refelex(R); 
        internals[1] = BinRelCore.AntiReflex(R);
        internals[2] = BinRelCore.Simetry(R);
        internals[3] = BinRelCore.AntiSimetry(R);
        internals[4] = BinRelCore.Asimetry(R);
        internals[5] = BinRelCore.Transity(R);
        
        res.append("\n");
        return res.toString();
    }
}
