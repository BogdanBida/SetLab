package Cores;

import java.util.ArrayList;
import java.util.HashSet;

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
            for (String i : getElementFromLine(line)) {
                this.add(new BinEl(i));
            }
        }
    }

}
