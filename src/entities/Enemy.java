package entities;

public class Enemy extends GameObject {
    public Enemy(int pos_x, int pos_y, int speed, int width, int height) {
        super(pos_x, pos_y, speed, width, height);
    }

    public void move(int ball_pos_y, int screenHeight) {
        int direction = (int) Math.signum(ball_pos_y - (pos_y + (float) height / 2));
        int newY = pos_y + direction * speed;

        if (newY > 0 && newY + height < screenHeight) {
            this.pos_y = newY;
        }
    }

}
