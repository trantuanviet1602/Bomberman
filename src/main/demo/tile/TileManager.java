package demo.tile;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.GamePanel;
import Implements.ImagePath;
import demo.tile.brick.BrickManager;
import demo.tile.wall.WallManager;

import java.awt.*;


public class TileManager implements ImagePath, Constant {
    GamePanel gamePanel;
    public static BrickManager brickManager;
    public static WallManager wallManager;
    public GameMap gameMap = new GameMap();




    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        brickManager = new BrickManager(this.gamePanel);
        wallManager = new WallManager();
    }

    public void update(BombManager bombManager) {
        brickManager.update(bombManager);
    }


    public void draw(Graphics2D graphics2D) {
        brickManager.draw(graphics2D);
        wallManager.draw(graphics2D);
    }
}
