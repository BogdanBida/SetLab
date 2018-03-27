package setlab.controller;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;


public class SintaxBinRel {
    public static String get(String command) {
        StringBuilder res = new StringBuilder(">> ");
        String[] line = command.replaceAll(" ", "") .split("=");
        BinRel R = new BinRel(line[0],line[1]);
        res.append(R.toString());
        
        return res.toString();
    }
}
