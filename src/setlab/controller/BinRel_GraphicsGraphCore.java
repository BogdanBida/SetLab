package setlab.controller;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinEl;
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
        angleImage = (float) (angle * Math.PI / 180);
    }

    public static void changeZoom(float zoom) {
        r += zoom;
        if (r <= 25) {
            r = 25;
            return;
        } else if (r >= 100) {
            r = 100;
            return;
        }
    }

    public static void resetZoom() {
        r = 50;
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
            float x2 = -2 + X0 + x * 1.4f;
            float y2 = 6 + Y0 + y * 1.4f;
            x += X0;
            y += Y0;
            listFigure.add(new Figure(elements[i - 1], x, y, x2, y2));
        }

        HashMap<String, Figure> mapFigure = new HashMap<>();
        for (Figure t : listFigure) {
            context.fillText(t.name, t.x2, t.y2);
            context.strokeOval(t.x, t.y, 6, 6);
            
            mapFigure.put(t.name, t);
        }

        BinEl[] listBinEl = R.toArray(new BinEl[R.size()]);

        for (int i = 0; i < R.size(); i++) {
            BinEl t = listBinEl[i];
            Figure a = mapFigure.get(t.getX());
            Figure b = mapFigure.get(t.getY());
            if (a.equals(b)) {
                context.setFill(Paint.valueOf("#75A6FE"));
                context.strokeOval(a.x - 3, a.y - 3, 12, 12);
                context.setFill(Paint.valueOf("#1E90FF"));
            } else {
                context.strokeLine(a.x + 3, a.y + 3, b.x + 3, b.y + 3);
            }
        }

        context.fillText(Math.round(angleImage * 180 / Math.PI) + "°", 5, c.getHeight() - 5);
        context.fillText("n = " + n, 5, 12);

        return context;
    }

}
