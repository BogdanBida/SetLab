package setlab.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore.SetObj;

public class BinRel_GraphicsGraphCore {

    private final static int X0 = 98;
    private final static int Y0 = 80;
    private final static int r = 50;

    public static GraphicsContext getContext(Canvas c, BinRel R) {
        GraphicsContext context = c.getGraphicsContext2D();
        context.clearRect(0, 0, 300, 300);
        context.setFill(Paint.valueOf("red"));

        SetObj set = BinRelCore.O(R);
        String[] elements = set.toArray(new String[set.size()]);
        int n = elements.length;

        float angle = (float) (2 * Math.PI / n);
        for (int i = 1; i <= n; i++) {
            float x = (float) (X0 + Math.cos(angle * i) * r);
            float y = (float) (Y0 + Math.sin(angle * i) * r);
            context.fillOval(x, y, 4, 4);
            context.fillText(elements[i - 1], x - 8, y - 8);
        }

        return context;
    }

}
