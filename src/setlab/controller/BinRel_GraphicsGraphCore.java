package setlab.controller;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore.SetObj;

public class BinRel_GraphicsGraphCore {

    private final static int X0 = 98;
    private final static int Y0 = 75;
    private final static int r = 50;
    private static float angleImage = 30f;

    // --------------- class of Elements ---
    private static class Figure {

        float x, y, r, x2, y2;
        String name;

        public Figure(String name, float x, float y, float x2, float y2) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static GraphicsContext getContext(Canvas c, BinRel R) {
        GraphicsContext context = c.getGraphicsContext2D();
        context.clearRect(0, 0, 300, 300);
        context.setFill(Paint.valueOf("#ff0000"));

        SetObj set = BinRelCore.O(R);
        String[] elements = set.toArray(new String[set.size()]);
        int n = elements.length;

        ArrayList<Figure> listFigure = new ArrayList<>();
        float angle = (float) (2 * Math.PI / n);
        for (int i = 1; i <= n; i++) {
            float x = (float) (Math.cos(angle * i) * r);
            float y = (float) (Math.sin(angle * i) * r);
            float x2 = X0 + x * 1.3f;
            float y2 = 5 + Y0 + y * 1.3f;
            x += X0;
            y += Y0;
            listFigure.add(new Figure(elements[i - 1], x, y, x2, y2));
        }

        for (Figure t : listFigure) {
            context.fillText(t.name, t.x2, t.y2);
            context.fillOval(t.x, t.y, 4, 4);
        }
        return context;
    }

}
