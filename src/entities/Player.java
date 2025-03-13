package entities;

import core.KeyHandler;
import core.MouseHandler;

public class Player extends GameObject {
    public Player(int pos_x, int pos_y, int speed, int width, int height) {
        super(pos_x, pos_y, speed, width, height);
    }

    public void move(KeyHandler keyH, MouseHandler mouseH, int screenHeight) {
        if (keyH.upPressed && pos_y >= 0) {
            pos_y -= speed;
        } else if (keyH.downPressed && pos_y + height <= screenHeight) {
            pos_y += speed;
        } else if (mouseH.mouseY > 0 && mouseH.mouseY < screenHeight - height) {
            pos_y = mouseH.mouseY;
        }
    }

}
