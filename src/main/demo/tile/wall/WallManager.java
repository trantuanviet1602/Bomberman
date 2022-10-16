package demo.tile.wall;

import Implements.Constant;

import java.awt.*;
import java.util.ArrayList;
import demo.tile.GameMap;

public class WallManager {
    public ArrayList<Wall> walls = new ArrayList<>();
    GameMap gameMap = new GameMap();

    public WallManager() {
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '#') {
                    walls.add(new Wall(i * Constant.tileSize, j * Constant.tileSize));
                }
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        for (Wall wall : walls) {
            wall.draw(graphics2D);
        }
    }
}
