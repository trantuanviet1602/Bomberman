package demo.tile.wall;

import Game.GamePanel;
import Implements.Constant;

import java.awt.*;
import java.util.ArrayList;

import SuperObject.Bomb.BombManager;
import demo.entity.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.GameMap;

public class WallManager extends EntityManagement {
    public ArrayList<Wall> walls = new ArrayList<>();

    public WallManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '#') {
                    walls.add(new Wall(i * Constant.tileSize, j * Constant.tileSize));
                }
            }
        }
    }

    @Override
    public void update(Player player) {}

    @Override
    public void update(Player player, BombManager bombManager) {}

    @Override
    public void update(BombManager bombManager) {}

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Wall wall : walls) {
            wall.draw(graphics2D);
        }
    }
}
