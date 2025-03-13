package entities;

import core.GamePanel;

public class Ball extends GameObject {
    private final int init_pos_x;
    private final int init_pos_y;

    private boolean direction_x;
    private boolean direction_y;

    public Ball(int pos_x, int pos_y, int speed, int width, int height) {
        super(pos_x, pos_y, speed, width, height);

        this.init_pos_x = pos_x;
        this.init_pos_y = pos_y;

        direction_x = false;
        direction_y = false;
    }

    public void move() {
        if (direction_x) {
            pos_x += speed;
        } else {
            pos_x -= speed;
        }

        if (direction_y) {
            pos_y += speed;
        } else {
            pos_y -= speed;
        }
    }

    private boolean checkPaddleCollision(GameObject paddle) {
        return pos_x + width >= paddle.getPos_x() && pos_x <= paddle.getPos_x() + paddle.getWidth() &&
                pos_y + height >= paddle.getPos_y() && pos_y <= paddle.getPos_y() + paddle.getHeight();
    }

    public void checkCollision(Player player, Enemy enemy, GamePanel gamePanel) {
        if (pos_y <= 0) {
            direction_y = true;
        } else if (pos_y >= gamePanel.getScreenHeight() - height) {
            direction_y = false;
        }

        if (checkPaddleCollision(player)) {
            direction_x = true;
            gamePanel.increaseFPS();
        } else if (checkPaddleCollision(enemy)) {
            direction_x = false;
            gamePanel.increaseFPS();
        } else if (pos_x < 0 || pos_x >= gamePanel.getScreenWidth()) {
            restart();
            gamePanel.resetFPS();
        }
    }


    public void restart() {
        pos_x = init_pos_x;
        pos_y = init_pos_y;
    }

    public void setDirection_x(boolean direction_x) {
        this.direction_x = direction_x;
    }

    public void setDirection_y(boolean direction_y) {
        this.direction_y = direction_y;
    }
}
