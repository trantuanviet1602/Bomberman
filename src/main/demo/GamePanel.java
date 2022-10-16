package demo;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import SuperObject.SuperObject;
import demo.entity.BalloomManager;
import demo.entity.CollisionCheck;
import demo.entity.Player.Player;
import demo.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, Constant {
    //Screen Settings

    Keyboard keyboard = new Keyboard();
    public Player player = new Player(this, keyboard);
    public BombManager bombManager = new BombManager(this);

    public TileManager tileManager = new TileManager(this);

    //Game clock.
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this);

    public SuperObject[] superObjects = new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);

    public BalloomManager balloomManager = new BalloomManager(this);

    public void setupGame() {
        assetSetter.setObject();
    }




    //FPS

    int FPS = 60;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Đặt kích cỡ của class JPanel.
        this.setBackground(Color.green);
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
        double drawInteval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;


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
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {
        player.update(bombManager);
        tileManager.update(bombManager);
        balloomManager.update(player, bombManager);
        bombManager.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent (graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;


        tileManager.draw(graphics2D);

        for(int i = 0 ; i < 10 ; i ++ ) {
            if(superObjects[i] != null) {
                superObjects[i].draw(graphics2D);
            }
        }
        balloomManager.draw(graphics2D);
        bombManager.draw(graphics2D);
        player.draw(graphics2D);


       graphics2D.dispose(); //Giảm bộ nhớ.

    }
}
