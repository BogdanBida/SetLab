package setlab.sintaxClasses;

import java.util.ArrayList;

public class Set_SintaxHistory {

    private static final ArrayList<String> listExp = new ArrayList<>();
    private static int idExp = 0;

    public static void addEx(String line) {
        listExp.add(line);
        idExp = listExp.size();
    }

    public static String up() {
        String res = "Not up";
        if (idExp > 0) {
            idExp--;
        }
        res = listExp.get(idExp);
        return res;
    }

    public static boolean isUp() {
        return idExp > 0;
    }

    public static String down() {
        String res = "Not down";
        if (idExp < listExp.size() - 1) {
            idExp++;
        }
        res = listExp.get(idExp);
        return res;
    }

    public static boolean isDown() {
        return idExp < listExp.size() - 1;
    }
}
