package setlab;

import java.util.*;

public class SetCore {

    public static class SetObj {

        public HashSet<String> set = new HashSet<>();
        public String name;

        SetObj(String name) {
            this.name = name;
        }

        SetObj(String name, String line) {
            this.name = name;
            ArrayList<String> inner = new ArrayList<>();
            String[] s = line.split(",");
            this.set.addAll(Arrays.asList(s));
        }

        SetObj(String name, HashSet<String> set) {
            this.name = name;
            this.set.addAll(set);
        }

        public int size() {
            return this.set.size();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder(this.name + " = [ ");
            int i = 0;
            for (String s : this.set) {
                res.append(s);
                i++;
                if (i < this.set.size()) {
                    res.append(", ");
                }
            }
            res.append(" ]");
            return res.toString();
        }
    }

    public static SetObj union(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a.set);
        Res.set.addAll(b.set);
        return Res;
    }
    
    public static SetObj intersect(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a.set);
        Res.set.retainAll(b.set);
        return Res;
    }
    
    public static SetObj diff(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a.set);
        Res.set.removeAll(b.set);
        return Res;
    }

}
