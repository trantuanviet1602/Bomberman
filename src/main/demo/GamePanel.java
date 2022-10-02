package demo;

import demo.entity.Player;
import demo.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, Constant {
    //Screen Settings

    Keyboard keyboard = new Keyboard();
    Player player = new Player(this, keyboard);

    TileManager tileManager = new TileManager(this);

    //Game clock.
    Thread gameThread;

    int playerX = 64;
    int playerY = 64;
    int playerSpeed = 3;


    //FPS

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Đặt kích cỡ của class JPanel.
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //Tất cả các bản vẽ từ thành phần này sẽ được thực hiện trong một bộ đệm vẽ ngoài màn hình
        this.addKeyListener(keyboard);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInteval =1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;




        //TODO: Tạo game loop
        while(gameThread != null) {
            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInteval;
            lastTime = currentTime;

            if(delta >= 1) {
                //TODO: update
                update();
                //TODO: draw
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);
        player.draw(graphics2D);

        graphics2D.dispose(); //Giảm bộ nhớ.

    }
}
