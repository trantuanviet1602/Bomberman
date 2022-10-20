package demo.entity.Enemy.Balloom;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import demo.entity.Enemy.Balloom.Balloom;
import demo.entity.Player.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class BalloomManager extends EntityManagement {
    public ArrayList<Balloom> ballooms = new ArrayList<>();
    public BalloomManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '1') {
                    ballooms.add(new Balloom(i * Constant.tileSize, j * Constant.tileSize, this.gamePanel));
                }
            }
        }
    }

    @Override
    public void update(Player player) {}

    @Override
    public void update(Player player, BombManager bombManager) {
        for (Balloom balloom : ballooms) {
            balloom.update(player, bombManager);
        }
        //TODO: Xóa Balloom ra khỏi List khi Balloom đã chết và đã thực hiện xong animation chết.
        ballooms.removeIf(balloom -> balloom.death && balloom.deathSpriteNum == 3);
    }

    @Override
    public void update(BombManager bombManager) {}

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Balloom balloom: ballooms) {
            balloom.draw(graphics2D);
        }
    }
}
