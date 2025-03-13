import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // scale ratio for the tile size

    final int tileSize = originalTileSize * scale; // 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int fps = 240;

    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    Thread gameThread;

    // Entities
    Player player;
    Ball ball;
    Enemy enemy;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImg = toolkit.createImage(new byte[0]);
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImg, new Point(0, 0), "invisibleCursor");

        this.setCursor(invisibleCursor);

        this.player = new Player((-tileSize / 4), (screenHeight / 2), 1,tileSize / 2, tileSize * 3);
        this.enemy = new Enemy((screenWidth - tileSize / 4), (screenHeight / 2), 1, tileSize / 2, tileSize * 3);
        this.ball = new Ball((screenWidth / 2), (screenHeight / 2), 1, tileSize / 4, tileSize / 4);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            double drawInterval = (double) 1000000000 / fps; // 1 sec / fps  or 0.01666

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                // 1 UPDATE: update information such as character positions
                update();

                // 2 DRAW: draw the screen updated information
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        movePlayer();

        ballHitCheck();

        ball.move();
        enemy.move(ball.getPos_y(), screenHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);

        g2d.fillRect(player.getPos_x(), player.getPos_y(), player.getWidth(), player.getHeight());
        g2d.fillRect(enemy.getPos_x(), enemy.getPos_y(), enemy.getWidth(), enemy.getHeight());

        g2d.setColor(Color.RED);
        g2d.fillRect(ball.getPos_x(), ball.getPos_y(), ball.getWidth(), ball.getHeight());

        g2d.dispose();
    }

    private void movePlayer() {
        // Player's movement
        if (keyH.upPressed) {
            if (player.getPos_y() >= 0) {
                player.move((byte) 1);
            }
        } else if (keyH.downPressed) {
            if (player.getPos_y() + player.getHeight() <= screenHeight) {
                player.move((byte) -1);
            }
        }else{
            if(mouseH.mouseY > 0 && mouseH.mouseY < screenHeight - player.getHeight()) {
                player.setPos_y(mouseH.mouseY);
            }
        }
    }

    private void ballHitCheck(){
        // Ball hits ceiling or floor
        if (ball.getPos_y() <= 0) {
            ball.setDirection_y(true);
        } else if (ball.getPos_y() >= screenHeight - tileSize / 4) {
            ball.setDirection_y(false);
        }

        // Ball hits player | Ball hits enemy | Ball hits "goal"
        if ((ball.getPos_x() > player.getPos_x() && ball.getPos_x() < player.getPos_x() + player.getWidth()) && (ball.getPos_y() > player.getPos_y() && ball.getPos_y() < player.getPos_y() + player.getHeight())) {
            ball.setDirection_x(true);
            fps += 20;
        }else if (ball.getPos_x() + ball.getWidth() >= enemy.getPos_x() && ball.getPos_y() >= enemy.getPos_y() && ball.getPos_y() <= enemy.getPos_y() + enemy.getHeight()) {
            ball.setDirection_x(false);
            fps += 20;
        }else if (ball.getPos_x() < 0 || ball.getPos_x() >= screenWidth){
            ball.restart();
            fps = 240;
        }
    }
}
