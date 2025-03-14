package core;

import entities.GameObject;

import java.awt.*;

public class Renderer {

    public static void drawObject(Graphics2D g2d, GameObject obj, Color color) {
        g2d.setColor(color);
        g2d.fillRect(obj.getPos_x(), obj.getPos_y(), obj.getWidth(), obj.getHeight());
    }
}
