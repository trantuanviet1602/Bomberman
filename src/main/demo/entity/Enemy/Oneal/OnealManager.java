package demo.entity.Enemy.Oneal;

import Game.GamePanel;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.Player.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class OnealManager extends EntityManagement {
    public static ArrayList<Oneal> oneals = new ArrayList<>();

    public OnealManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '2') {
                    oneals.add(new Oneal(i * Constant.tileSize, j * Constant.tileSize, this.gamePanel));
                }
            }
        }
    }

    @Override
    public void update(Player player) {}

    @Override
    public void update(Player player, BombManager bombManager) {
        for (Oneal oneal: oneals) {
            oneal.update(player, bombManager);
        }
        //TODO: Xóa Oneal ra khỏi List khi Oneal đã chết và đã thực hiện xong animation chết.
        oneals.removeIf(oneal -> oneal.death && oneal.deathSpriteNum == 3);
    }

    @Override
    public void update(BombManager bombManager) {}

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Oneal oneal: oneals) {
            oneal.draw(graphics2D);
        }
    }
}
