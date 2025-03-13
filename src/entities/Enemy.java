package entities;

public class Enemy extends GameObject {
    public Enemy(int pos_x, int pos_y, int speed, int width, int height) {
        super(pos_x, pos_y, speed, width, height);
    }

    public void move(int ball_pos_y, int screenHeight) {
        int direction = (int) Math.signum(ball_pos_y - (getPos_y() + (float) getHeight() / 2));
        int newY = getPos_y() + direction * getSpeed();

        if (newY > 0 && newY + getHeight() < screenHeight) {
            setPos_y(newY);
        }
    }

}
