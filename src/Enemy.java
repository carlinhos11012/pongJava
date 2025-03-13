public class Enemy extends Player {
    public Enemy(int pos_x, int pos_y, int speed, int width, int height) {
        super(pos_x, pos_y, speed, width, height);
    }

    public void move(int ball_pos_y, int screenHeight) {
        if (getPos_y() + getHeight() / 2 < ball_pos_y) {
            if (getPos_y() + getHeight() < screenHeight) {
                setPos_y(getPos_y() + getSpeed());
            }
        }else if(getPos_y() + getHeight() / 2 > ball_pos_y) {
            if(getPos_y() > 0){
                setPos_y(getPos_y() - getSpeed());
            }
        }
    }
}
