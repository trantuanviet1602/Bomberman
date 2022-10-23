package Game;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import SuperObject.Enhancement.EnhancementManager;
import UI.UI;
import demo.Sound.Sound;
import demo.entity.Enemy.Balloom.BalloomManager;
import demo.entity.CollisionCheck;
import demo.entity.Enemy.Kondoria.KondoriaManager;
import demo.entity.Enemy.Oneal.Oneal;
import demo.entity.Player.Player;
import demo.tile.GameMap;
import demo.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, Constant {
    //Screen Settings

    Keyboard keyboard = new Keyboard(this);
    public BombManager bombManager = new BombManager(this);
    public Player player = new Player(this, keyboard, bombManager);
    public GameMap gameMap = new GameMap();

    private UI ui = new UI(this);


    public TileManager tileManager = new TileManager(this, gameMap);

    //Game clock.
    Thread gameThread = new Thread(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this, bombManager, tileManager);

    public BalloomManager balloomManager = new BalloomManager(this, gameMap);
    public KondoriaManager kondoriaManager = new KondoriaManager(this, gameMap);

    public EnhancementManager enhancementManager = new EnhancementManager(this, gameMap);



    public int gameState;
    Sound sound = new Sound();





    //FPS

    int FPS = 60;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Đặt kích cỡ của class JPanel.
        this.setBackground(Color.green);
        this.setDoubleBuffered(true); //Tất cả các bản vẽ từ thành phần này sẽ được thực hiện trong một bộ đệm vẽ ngoài màn hình
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        gameState = playState;

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
                update();
                //TODO: draw
                repaint();
                delta--;
                //TODO: Sleep thread để giảm usage cho CPU. Không cần thực hiện quá liên tục.
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.err.println("Cannot sleep Thread");
                }
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update(bombManager);
            enhancementManager.update(player);
            tileManager.update(bombManager);
            balloomManager.update(player, bombManager);
            kondoriaManager.update(player, bombManager);
            bombManager.update();
        }
        if (gameState == pauseState) {

        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent (graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        ui.draw(graphics2D);
        if(this.gameState == playState) {
            this.setBackground(Color.green);
            enhancementManager.draw(graphics2D);
            tileManager.draw(graphics2D);
            balloomManager.draw(graphics2D);
            kondoriaManager.draw(graphics2D);
            bombManager.draw(graphics2D);
            player.draw(graphics2D);
        }

       graphics2D.dispose(); //Giảm bộ nhớ.

    }

    public void playMusic(String s) {
        sound.setFile(s);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(String s) {
        sound.setFile(s);
        sound.play();
    }
}
