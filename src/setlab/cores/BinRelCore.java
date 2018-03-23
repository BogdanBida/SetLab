package setlab.cores;

import java.util.ArrayList;
import java.util.HashSet;
import setlab.cores.SetCore;
import setlab.cores.SetCore.SetObj;

public class BinRelCore {

    private static class BinEl {

        String x, y;

        public BinEl(String x, String y) {
            this.x = x;
            this.y = y;
        }

        public BinEl(String xy) {
            String[] res;
            res = xy.split(",");
            this.x = res[0];
            this.y = res[1];
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static class BinRel extends HashSet<BinEl> {

        public String name;

        public BinRel(String name) {
            this.name = name;
        }

        private ArrayList<String> getElementFromLine(String line) {
            return new ArrayList<String>();
        }
        
        public BinRel(String name, String line) {
            this.name = name;
            for (String s : getElementFromLine(line)) {
                this.add(new BinEl(s));
            }
        }
    }
    
    public static SetObj D(BinRel r) {
        return new SetObj("D");
    }
    public static SetObj E(BinRel r) {
        return new SetObj("D");
    }
    public static SetObj O(BinRel r) {
        return SetCore.Union(D(r), E(r));
    }
    
    public static BinRel Ident(BinRel r) {
        return new BinRel("Ia");
    }
    
    public static BinRel Reverse(BinRel r) {
        return new BinRel("invR");
    }
    
    public static BinRel Composer(BinRel r) {
        return new BinRel("CombR");
    }
    
    public static boolean Refelex(BinRel r) {
        return true;
    }
    public static boolean AntiRefelex(BinRel r) {
        return true;
    }
    public static boolean Simetry(BinRel r) {
        return true;
    }
    public static boolean AntiSimetry(BinRel r) {
        return true;
    }
    public static boolean Asimetry(BinRel r) {
        return true;
    }
    public static boolean Transity(BinRel r) {
        return true;
    }
}
