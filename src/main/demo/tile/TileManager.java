package demo.tile;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import Implements.ImagePath;
import demo.tile.brick.Brick;
import demo.tile.brick.BrickManager;
import demo.tile.wall.Wall;
import demo.tile.wall.WallManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class TileManager implements ImagePath, Constant {
    GamePanel gamePanel;
    public BrickManager brickManager;
    public WallManager wallManager;
    public GameMap gameMap;

    public boolean[][] mapCollision;




    public TileManager(GamePanel gamePanel, GameMap gameMap) {
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        mapCollision = new boolean[gameMap.rows][gameMap.cols];
        brickManager = new BrickManager(this.gamePanel, this.gameMap);
        wallManager = new WallManager(this.gamePanel, this.gameMap);
        for (Wall wall : wallManager.walls) {
            mapCollision[wall.getY() / tileSize][wall.getX() / tileSize] = true;
        }
        for (Brick brick: brickManager.bricks) {
            mapCollision[brick.getY() / tileSize][brick.getX() / tileSize] = true;
        }

    }

    public void update(BombManager bombManager) {
        brickManager.update(bombManager, mapCollision);
    }


    public void draw(Graphics2D graphics2D) {

        brickManager.draw(graphics2D);
        wallManager.draw(graphics2D);
    }
}
