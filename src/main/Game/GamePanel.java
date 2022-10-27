package Game;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import SuperObject.Enhancement.EnhancementManager;
import UI.UI;
import demo.Sound.Sound;
import demo.Sound.SoundPath;
import demo.tile.CollisionCheck;
import demo.entity.Enemy.EnemyManager;
import demo.entity.Player.Player;
import demo.tile.GameMap;
import demo.tile.Grass;
import demo.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, Constant {
    //Screen Settings

    Keyboard keyboard = new Keyboard(this);
    private BombManager bombManager = new BombManager(this);
    private Player player = new Player(this, keyboard, bombManager);
    private int currentLevel = 1;
    private GameMap gameMap = new GameMap(currentLevel);

    private UI ui = new UI(this);


    public TileManager tileManager = new TileManager(this, gameMap);

    //Game clock.
    private Thread gameThread = new Thread(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this, bombManager, tileManager);

    private EnemyManager enemyManager = new EnemyManager(this, gameMap);

    private EnhancementManager enhancementManager = new EnhancementManager(this, gameMap);

    private boolean nextLevel = false;

    private Grass grass = new Grass(this);

    public boolean isNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int gameState, lastState = -1;

    Sound sound = new Sound();






    //FPS

    int FPS = 60;

    public void setupGame() {
        this.removeAll();
        bombManager.reset();
        player = new Player(this, keyboard, bombManager);
        sound = new Sound();
        gameMap = new GameMap(currentLevel);
        ui = new UI(this);
        tileManager = new TileManager(this, gameMap);

        collisionCheck = new CollisionCheck(this, bombManager, tileManager);
        enemyManager = new EnemyManager(this, gameMap);
        enhancementManager = new EnhancementManager(this, gameMap);

    }

    public GamePanel() {
        setupGame();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Đặt kích cỡ của class JPanel.
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //Tất cả các bản vẽ từ thành phần này sẽ được thực hiện trong một bộ đệm vẽ ngoài màn hình
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        gameState = titleState;

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

    public void updateSound() {
        if (gameState != lastState ) {
             if (gameState == titleState) {
                playMusic(SoundPath.TITLE);
             } else if (gameState == levelState && lastState != playState) {
                 stopMusic();
                 playMusic(SoundPath.STAGE);
             }
            lastState = gameState;
        }
    }

    public void update() {
        updateSound();
        if (gameState == playState) {

            enhancementManager.update(player);
            tileManager.update(bombManager);
            player.update(bombManager, tileManager);
            bombManager.update(tileManager);
            enemyManager.update(player, bombManager);

        }
        if (player.isDeath() && player.getDeathSpriteNum() >= 3) {
            gameState = gameOverState;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent (graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        ui.draw(graphics2D);
        if(this.gameState == playState) {
            grass.draw(graphics2D);
            enhancementManager.draw(graphics2D);
            bombManager.draw(graphics2D);
            tileManager.draw(graphics2D);
            enemyManager.draw(graphics2D);
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

    public GameMap getGameMap() {
        return gameMap;
    }

    public void nextLevel() {
        if (nextLevel) {
            currentLevel ++;

            nextLevel = false;
            setupGame();
        }

    }

    public Player getPlayer() {
        return this.player;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public UI getUi() {
        return ui;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
