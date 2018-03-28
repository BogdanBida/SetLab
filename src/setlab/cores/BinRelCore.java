package setlab.cores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.cores.SetCore.SetObj;

public class BinRelCore {

    private static class BinEl {

        String x, y;

        public String getX() {
            return this.x;
        }

        public String getY() {
            return this.y;
        }

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

        public BinRel(String name, BinRel a) {
            this.name = name;
            this.addAll(a);
        }

        public BinRel(String name, String line) {
            this.name = name;
            getElementFromLine(line).forEach((s) -> {
                this.add(new BinEl(s));
            });
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

        public void changeName(String newName) {
            this.name = newName;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder(this.name + " = {");
            Iterator t = this.iterator();
            if (!t.hasNext()) {
                return "{}";
            }
            for (;;) {
                res.append(t.next());
                if (!t.hasNext()) {
                    return res.append("}").toString();
                }
                res.append(",").append(" ");
            }
        }

    }

    public static SetObj D(BinRel r) {
        SetObj res = new SetObj("D(" + r.name + ")");
        r.forEach((b) -> {
            res.add(b.x);
        });
        return res;
    }

    public static SetObj E(BinRel r) {
        SetObj res = new SetObj("E(" + r.name + ")");
        r.forEach((b) -> {
            res.add(b.y);
        });

        return res;
    }

    public static SetObj O(BinRel r) {
        SetObj Res = SetCore.Union(D(r), E(r));
        Res.changeName("O(" + r.name + ")");
        return Res;
    }

    public static BinRel Ident(SetObj a) {
        BinRel res = new BinRel("Ia");
        a.forEach((xy) -> {
            res.add(new BinEl(xy, xy));
        });
        return res;
    }

    public static BinRel Reverse(BinRel r) {
        BinRel res = new BinRel(r.name + "^(-1)");
        r.forEach((b) -> {
            res.add(new BinEl(b.getY(), b.getX()));
        });
        return res;
    }

    public static BinRel Composer(BinRel r) {
        return new BinRel(r.name + "o" + r.name);
    }

    // ------------------------------------------------- INTERNALS ---
    public static boolean Refelex(BinRel r) {
        return isSubBinRel(Ident(O(r)), r);
    }

    public static boolean AntiRefelex(BinRel r) {
        return Intersect(Ident(O(r)), r).isEmpty();
    }

    public static boolean Simetry(BinRel r) {
        return r.equals(Reverse(r));

    }

    public static boolean AntiSimetry(BinRel r) {
        return isSubBinRel(Intersect(r, Reverse(r)), r);
    }

    public static boolean Asimetry(BinRel r) {
        return Intersect(r, Reverse(r)).isEmpty();
    }

    public static boolean Transity(BinRel r) {

        return false;
    }

    // ------------------------------------------------ OPERATIONS ---
    public static BinRel Union(BinRel a, BinRel b) {
        BinRel Res = new BinRel("Ans", a);
        Res.addAll(b);
        return Res;
    }

    public static BinRel Intersect(BinRel a, BinRel b) {
        BinRel Res = new BinRel("Ans", a);
        Res.retainAll(b);
        return Res;
    }

    public static BinRel Diff(BinRel a, BinRel b) {
        BinRel Res = new BinRel("Ans", a);
        Res.removeAll(b);
        return Res;
    }

    public static BinRel SymmetricDiff(BinRel a, BinRel b) {
        return Union(Diff(a, b), Diff(b, a));
    }

    // ---------------------------------------------- OPERATION EQUALS ---
    public static boolean isSubBinRel(BinRel a, BinRel b) {
        return a.containsAll(b);
    }
}
