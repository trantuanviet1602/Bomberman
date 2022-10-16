package demo.tile.brick;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.GamePanel;
import demo.tile.GameMap;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BrickManager {

    GamePanel gamePanel;
    public ArrayList<Brick> bricks = new ArrayList<>();
    public HashMap<Pair<Integer, Integer>, Brick> brickHashMap = new HashMap<>();
    GameMap gameMap = new GameMap();

    public BrickManager(GamePanel gamePanel) {
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '*') {
                    bricks.add(new Brick(i * Constant.tileSize, j * Constant.tileSize));
                    brickHashMap.put(new Pair<Integer, Integer> (i , j),
                            new Brick(i * Constant.tileSize, j * Constant.tileSize));
                }
            }
        }
        this.gamePanel = gamePanel;
    }

    public void update(BombManager bombManager) {
        for (Brick brick : bricks) {
            brick.update(bombManager);
        }
        bricks.removeIf(brick -> brick.death);
    }

    public void draw(Graphics2D graphics2D) {
        for (Brick brick: bricks) {
            brick.draw(graphics2D);
        }
    }

    public Brick getBrick(int x, int y) {
        for (Brick brick: bricks) {
            if (brick.x / Constant.tileSize == x / Constant.tileSize
                    && brick.y / Constant.tileSize == y / Constant.tileSize) {
                return brick;
            }
        }
        return null;
        //return brickHashMap.get(new Pair<>(x / Constant.tileSize, y/Constant.tileSize));
    }
}
