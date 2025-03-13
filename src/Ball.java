public class Ball {
    private int pos_x;
    private int pos_y;
    private final int speed;
    private final int init_pos_x, init_pos_y;
    private final int width, height;
    private boolean direction_x, direction_y;

    public Ball(int pos_x, int pos_y, int speed, int width, int height) {
        this.pos_x = pos_x;
        this.init_pos_x = pos_x;
        this.pos_y = pos_y;
        this.init_pos_y = pos_y;
        this.speed = speed;
        this.width = width;
        this.height = height;

        direction_x = false;
        direction_y = false;
    }

    public void move() {
        if (direction_x) {
            pos_x += speed;
        }else {
            pos_x -= speed;
        }

        if (direction_y) {
            pos_y += speed;
        }else {
            pos_y -= speed;
        }
    }

    public void restart(){
        pos_x = init_pos_x;
        pos_y = init_pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection_x(boolean direction_x) {
        this.direction_x = direction_x;
    }

    public void setDirection_y(boolean direction_y) {
        this.direction_y = direction_y;
    }
}
