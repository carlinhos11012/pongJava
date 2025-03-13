package entities;

public abstract class GameObject {
    protected int pos_x, pos_y, width, height, speed;

    public GameObject(int pos_x, int pos_y, int speed, int width, int height) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public int getPos_x() { return pos_x; }
    public int getPos_y() { return pos_y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getSpeed() { return speed; }

    public void setPos_y(int pos_y) { this.pos_y = pos_y; }
}
