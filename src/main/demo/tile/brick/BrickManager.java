package demo.tile.brick;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import demo.entity.Player.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BrickManager extends EntityManagement {
    public ArrayList<Brick> bricks = new ArrayList<>();


    public BrickManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) != '1'
                        && gameMap.mapTile[j].charAt(i) != '#' && gameMap.mapTile[j].charAt(i) != '2'
                        && gameMap.mapTile[j].charAt(i) != ' ' && gameMap.mapTile[j].charAt(i) != 'p') {
                    bricks.add(new Brick(i * Constant.tileSize, j * Constant.tileSize));
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

    public void update(BombManager bombManager, boolean[][] mapCollision) {
        for (Brick value : bricks) {
            value.update(bombManager);
            if (value.death) {
                mapCollision[value.y / Constant.tileSize][value.x / Constant.tileSize] = false;
            }
        }
        bricks.removeIf(brick -> brick.death && brick.deathSpriteNum == 3);
    }


    @Override
    public void draw(Graphics2D graphics2D) {
        for (Brick brick: bricks) {
            brick.draw(graphics2D);
        }
    }

}
