public class Player {
    private final int pos_x, speed;
    private final int width, height;
    private int pos_y;

    public Player(int pos_x, int pos_y, int speed, int width, int height) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public void move(byte direction) {
        if (direction == -1) {
            pos_y += speed;
        }else if (direction == 1) {
            pos_y -= speed;
        }
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
