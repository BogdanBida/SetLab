package setlab.cores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.cores.SetCore.SetObj;

public class BinRelCore {

    public static class BinEl {

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

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + Objects.hashCode(this.x);
            hash = 67 * hash + Objects.hashCode(this.y);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final BinEl other = (BinEl) obj;
            if (!Objects.equals(this.x, other.x)) {
                return false;
            }
            return Objects.equals(this.y, other.y);
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

        public String getInner() {
            StringBuilder res = new StringBuilder("{");
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

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder(this.name + " = {");
            Iterator t = this.iterator();
            if (this.isEmpty()) {
                return this.name + " = Ø";
            }
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

        @Override
        public boolean equals(Object obj) {
            HashSet<BinEl> a = (HashSet) this;
            HashSet<BinEl> b = (HashSet) obj;
            Iterator<BinEl> it = a.iterator();
            if (a.size() != b.size()) {
                return false;
            }
            while (it.hasNext()) {
                if (!b.contains(it.next())) {
                    return false;
                }
            }
            return true;
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

    public static BinRel Ident(BinRel r) {
        SetObj a = O(r);
        BinRel res = new BinRel("I_a");
        a.forEach((xy) -> {
            res.add(new BinEl(xy, xy));
        });
        return res;
    }

    public static BinRel Reverse(BinRel r) {
        BinRel res = new BinRel(r.name + "^−1"); // add teg of stage
        r.forEach((b) -> {
            res.add(new BinEl(b.getY(), b.getX()));
        });
        return res;
    }

    public static BinRel Composer(BinRel a, BinRel b) {
        BinRel res = new BinRel(a.name + "∘" + b.name);
        Iterator<BinEl> ia, ib;
        ia = a.iterator();
        while (ia.hasNext()) {
            ib = b.iterator();
            BinEl elA = ia.next();
            while (ib.hasNext()) {
                BinEl elB = ib.next();
                if (elA.y.equals(elB.x)) {
                    res.add(new BinEl(elA.x, elB.y));
                }
            }
        }
        return res;
    }

    // ------------------------------------------------- INTERNALS ---
    public static boolean Refelex(BinRel r) {
        return isSubBinRel(Ident(r), r);
    }

    public static boolean AntiReflex(BinRel r) {
        return Intersect(Ident(r), r).isEmpty();
    }

    public static boolean Simetry(BinRel r) {
        BinRel b = Reverse(r);
        return r.equals(b);

    }

    public static boolean AntiSimetry(BinRel r) {
        return isSubBinRel(Intersect(r, Reverse(r)), r);
    }

    public static boolean Asimetry(BinRel r) {
        return Intersect(r, Reverse(r)).isEmpty();
    }

    public static boolean Transity(BinRel r) {
        BinRel composer = Composer(r, r);
        if (isSubBinRel(composer, r)) {
            return true;
        }
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
        if (a.isEmpty()) {
            return false;
        }
        return b.containsAll(a);
    }
}
