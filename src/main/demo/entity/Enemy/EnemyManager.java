package demo.entity.Enemy;

import Game.GamePanel;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.Enemy.Balloom.Ballom;
import demo.entity.Enemy.Kondoria.Kondoria;
import demo.entity.Enemy.Oneal.Oneal;
import demo.entity.Player.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class EnemyManager extends EntityManagement implements Constant {

    // TODO: Class này thực hiện quản lý tất cả các Enemy có trong trò chơi.
    private final ArrayList <Enemy> enemies = new ArrayList<>();

    public EnemyManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '1') {
                    enemies.add(new Ballom(i * tileSize, j * tileSize, gamePanel));
                } else if (gameMap.mapTile[j].charAt(i) == '2') {
                    enemies.add(new Oneal(i * tileSize, j * tileSize, gamePanel));
                } else if (gameMap.mapTile[j].charAt(i) == '3') {
                    enemies.add(new Kondoria(i * tileSize, j * tileSize, gamePanel));
                }
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void update(Player player) {}

    @Override
    public void update(Player player, BombManager bombManager) {
        for (Enemy enemy : enemies) {
            enemy.update(player, bombManager);
        }
        enemies.removeIf(enemy -> enemy.isDeath() && enemy.getDeathSpriteNum() == 3);
    }

    @Override
    public void update(BombManager bombManager) {}

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Enemy enemy : enemies) {
            enemy.draw(graphics2D);
        }
    }
}
