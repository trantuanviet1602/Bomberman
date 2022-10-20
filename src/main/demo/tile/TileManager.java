package demo.tile;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import Implements.ImagePath;
import demo.tile.brick.BrickManager;
import demo.tile.wall.Wall;
import demo.tile.wall.WallManager;

import java.awt.*;
import java.util.HashMap;


public class TileManager implements ImagePath, Constant {
    GamePanel gamePanel;
    public static BrickManager brickManager;
    public static WallManager wallManager;
    public GameMap gameMap;

    public HashMap<Integer, Integer> tileHash = new HashMap<>();





    public TileManager(GamePanel gamePanel, GameMap gameMap) {
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        brickManager = new BrickManager(this.gamePanel, this.gameMap);
        wallManager = new WallManager(this.gamePanel, this.gameMap);
        for (Wall wall : wallManager.walls) {
            tileHash.put(wall.x, wall.y);
        }

    }

    public void update(BombManager bombManager) {
        brickManager.update(bombManager);
    }


    public void draw(Graphics2D graphics2D) {
        brickManager.draw(graphics2D);
        wallManager.draw(graphics2D);
    }
}
