package setlab.controller;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore.SetObj;

public class BinRel_GraphicsGraphCore {

    private final static int X0 = 105;
    private final static int Y0 = 70;
    private static int r = 50;
    public static float angleImage = 0f;

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
    
    public static void setAngle(float angle) {
        angleImage = (float) (angle*Math.PI / 180);
    }

    public static GraphicsContext getContext(Canvas c, BinRel R) {
        GraphicsContext context = c.getGraphicsContext2D();
        context.clearRect(0, 0, 215, 150);
        context.setFill(Paint.valueOf("#6495ED"));
        context.setStroke(Paint.valueOf("#1E90FF"));

        SetObj set = BinRelCore.O(R);
        String[] elements = set.toArray(new String[set.size()]);
        int n = elements.length;
        final float angle = (float) (2 * Math.PI / n);

        ArrayList<Figure> listFigure = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            float x = (float) (Math.cos(angleImage + angle * i) * r);
            float y = (float) (Math.sin(angleImage + angle * i) * r);
            float x2 = X0 + x * 1.3f;
            float y2 = 5 + Y0 + y * 1.3f;
            x += X0;
            y += Y0;
            listFigure.add(new Figure(elements[i - 1], x, y, x2, y2));
        }

        for (Figure t : listFigure) {
            context.fillText(t.name, t.x2, t.y2);
            context.strokeOval(t.x, t.y, 5, 5);
        }
//        for (BinRelCore.BinEl t : R) {
//            t.getX()
//        }
        return context;
    }

}
