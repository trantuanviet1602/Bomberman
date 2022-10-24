package demo.tile.brick;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import demo.entity.Player.EntityManagement;
import demo.entity.Player.Player;
import demo.entity.Portal;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class BrickManager extends EntityManagement {
    public ArrayList<Brick> bricks = new ArrayList<>();
    private Portal portal;


    public BrickManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        portal = new Portal(-16, -16, gamePanel);
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) != '1'
                        && gameMap.mapTile[j].charAt(i) != '#' && gameMap.mapTile[j].charAt(i) != '2'
                        && gameMap.mapTile[j].charAt(i) != ' ' && gameMap.mapTile[j].charAt(i) != 'p') {
                    bricks.add(new Brick(i * Constant.tileSize, j * Constant.tileSize));
                }
                if (gameMap.mapTile[j].charAt(i) == 'x') {
                    portal = new Portal(i * Constant.tileSize, j * Constant.tileSize, gamePanel);
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
            if (value.isDeath()) {
                mapCollision[value.getY() / Constant.tileSize][value.getX() / Constant.tileSize] = false;

            }
        }
        bricks.removeIf(brick -> brick.isDeath() && brick.getDeathSpriteNum() == 3);
    }


    @Override
    public void draw(Graphics2D graphics2D) {
        portal.draw(graphics2D);
        for (Brick brick: bricks) {
            brick.draw(graphics2D);
        }
    }

}
