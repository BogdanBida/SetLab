package setlab.cores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            ArrayList<String> list = new ArrayList<>();

            String pattern = "[\\050](\\p{Alnum}{1,}[\\054]\\p{Alnum}{1,})[\\051]";
            Pattern r = Pattern.compile(pattern);

            boolean fl = true;
            while (fl) {
                Matcher m = r.matcher(line);
                if (m.find()) {
                    list.add(m.group(1));
                    if (!line.isEmpty()) {
                        line = line.substring(m.end());
                    }
                } else {
                    fl = false;
                }
            }

            return list;
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

    public static BinRel Reverse(BinRel r) {
        return new BinRel("invR");
    }

    public static BinRel Composer(BinRel r) {
        return new BinRel("CombR");
    }

}
