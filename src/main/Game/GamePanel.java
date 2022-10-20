package Game;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import SuperObject.Enhancement.EnhancementManager;
import demo.entity.Enemy.Balloom.BalloomManager;
import demo.entity.CollisionCheck;
import demo.entity.Enemy.Oneal.OnealManager;
import demo.entity.Player.Player;
import demo.tile.GameMap;
import demo.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements Runnable, Constant {
    //Screen Settings

    Keyboard keyboard = new Keyboard();
    public BombManager bombManager = new BombManager(this);
    public Player player = new Player(this, keyboard, bombManager);
    public GameMap gameMap = new GameMap();


    public TileManager tileManager = new TileManager(this, gameMap);

    //Game clock.
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this, bombManager, tileManager);

    public BalloomManager balloomManager = new BalloomManager(this, gameMap);
    public OnealManager onealManager = new OnealManager(this, gameMap);

    public EnhancementManager enhancementManager = new EnhancementManager(this, gameMap);






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
        enhancementManager.update(player);
        tileManager.update(bombManager);
        balloomManager.update(player, bombManager);
        onealManager.update(player, bombManager);
        bombManager.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent (graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        enhancementManager.draw(graphics2D);
        tileManager.draw(graphics2D);
        balloomManager.draw(graphics2D);
        onealManager.draw(graphics2D);
        bombManager.draw(graphics2D);
        player.draw(graphics2D);


       graphics2D.dispose(); //Giảm bộ nhớ.

    }
}
