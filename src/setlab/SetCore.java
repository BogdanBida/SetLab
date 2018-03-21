package setlab;

import java.util.*;
import setlab.ReversPolish.*;

public class SetCore {

    public static class SetObj extends HashSet<String> {

        public String name;

        SetObj(String name) {
            this.name = name;
        }

        SetObj(String name, String line) {
            this.name = name;
            this.addAll(Arrays.asList(line.split(",")));
        }

        SetObj(String name, String[] sequence) {
            this.name = name;
            this.addAll(Arrays.asList(sequence));
        }

        SetObj(String name, SetObj set) {
            this.name = name;
            this.addAll(set);
        }

        SetObj(SetObj set) {
            this.name = set.name;
            this.addAll(set);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder(this.name + " = [ ");
            Iterator t = this.iterator();
            if (!t.hasNext()) {
                return "[]";
            }
            for (;;) {
                res.append(t.next());
                if (!t.hasNext()) {
                    return res.append("]").toString();
                }
                res.append(",").append(" ");
            }
        }
    }

    public static SetObj Union(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a);
        Res.addAll(b);
        return Res;
    }

    public static SetObj Intersect(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a);
        Res.retainAll(b);
        return Res;
    }

    public static SetObj Diff(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a);
        Res.removeAll(b);
        return Res;
    }

    public static SetObj SymmetricDiff(SetObj a, SetObj b) {
        return Union(Diff(a, b), Diff(b, a));
    }

    public static SetObj createSetOnSeq(String name, String f, String max) {
        SetObj Res = new SetObj(name);
        for (int i = 0; i < Integer.parseInt(max); i++) {
            Res.add(String.valueOf(ReversPolish.get(f.replaceAll("x", String.valueOf(i)))));
        }
        return Res;
    }

}
