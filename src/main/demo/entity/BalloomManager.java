package demo.entity;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.GamePanel;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class BalloomManager {
    public ArrayList<Balloom> ballooms = new ArrayList<>();
    public GamePanel gamePanel;
    private GameMap gameMap = new GameMap();

    public BalloomManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '1') {
                    ballooms.add(new Balloom(i * Constant.tileSize, j * Constant.tileSize, this.gamePanel));
                }
            }
        }
    }

    public void update(Player player, BombManager bombManager) {
        for (Balloom balloom : ballooms) {
            balloom.update(player, bombManager);
        }
        ballooms.removeIf(balloom -> balloom.death);
    }

    public void draw(Graphics2D graphics2D) {
        for (Balloom balloom: ballooms) {
            balloom.draw(graphics2D);
        }
    }
}
