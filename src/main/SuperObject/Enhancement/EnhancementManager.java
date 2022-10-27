package SuperObject.Enhancement;

import Game.GamePanel;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class EnhancementManager  extends EntityManagement {
    public ArrayList<Enhancement> enhancements = new ArrayList<>();

    public EnhancementManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        for (int i = 0 ; i < gameMap.rows; i++) {
            for (int j = 0; j < gameMap.cols; j++) {
                if (gameMap.mapTile[i].charAt(j) == 'b') {
                    enhancements.add(new ObjectBombs(j * Constant.tileSize, i * Constant.tileSize, gamePanel));
                } else if (gameMap.mapTile[i].charAt(j) == 's') {
                    enhancements.add(new ObjectSpeed(j * Constant.tileSize, i * Constant.tileSize, gamePanel));
                } else if (gameMap.mapTile[i].charAt(j) == 'f') {
                    enhancements.add(new ObjectFlames(j * Constant.tileSize, i * Constant.tileSize, gamePanel));
                }
            }
        }
    }

    @Override
    public void update(Player player) {
        for (Enhancement enhancement: enhancements) {
            enhancement.update(player);
        }
        enhancements.removeIf(enhancement -> enhancement.isDeath());
    }

    @Override
    public void update(Player player, BombManager bombManager) {}

    @Override
    public void update(BombManager bombManager) {}

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Enhancement enhancement : enhancements) {
            enhancement.draw(graphics2D);
        }
    }
}
