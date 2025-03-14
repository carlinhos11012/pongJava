package core;

import entities.Ball;
import entities.Enemy;
import entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;


    // FPS
    private final int initialFps = 240;
    private int fps = initialFps;
    private static final int MAX_FPS = 1000;

    // Handlers
    private final KeyHandler keyH = new KeyHandler();
    private final MouseHandler mouseH = new MouseHandler();

    // Thread
    private Thread gameThread;

    // Entities
    private final Player player;
    private final Ball ball;
    private final Enemy enemy;



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

        player.move(keyH, mouseH, screenHeight);

        ball.checkCollision(player, enemy, this);

        ball.move();
        enemy.move(ball.getPos_y(), screenHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha o jogador
        Renderer.drawObject(g2d, player, Color.WHITE);

        // Desenha o inimigo
        Renderer.drawObject(g2d, enemy, Color.WHITE);

        // Desenha a bola
        Renderer.drawObject(g2d, ball, Color.RED);
    }


    public void increaseFPS(){
        this.fps = Math.min(fps + 20, MAX_FPS);
    }
    public void resetFPS(){
        this.fps = initialFps;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

}
