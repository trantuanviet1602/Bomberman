package demo.entity.Enemy.Kondoria;

import Game.GamePanel;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.Player.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class KondoriaManager extends EntityManagement {
    public static ArrayList<Kondoria> kondorias = new ArrayList<>();

    public KondoriaManager(GamePanel gamePanel, GameMap gameMap) {
        super(gamePanel, gameMap);
        for (int i = 0; i < gameMap.cols; i++) {
            for (int j = 0; j < gameMap.rows; j++) {
                if (gameMap.mapTile[j].charAt(i) == '2') {
                    kondorias.add(new Kondoria(i * Constant.tileSize, j * Constant.tileSize, this.gamePanel));
                }
            }
        }
    }

    @Override
    public void update(Player player) {}

    @Override
    public void update(Player player, BombManager bombManager) {
        for (Kondoria kondoria : kondorias) {
            kondoria.update(player, bombManager);
        }
        //TODO: Xóa Oneal ra khỏi List khi Oneal đã chết và đã thực hiện xong animation chết.
        kondorias.removeIf(kondoria -> kondoria.death && kondoria.deathSpriteNum == 3);
    }

    @Override
    public void update(BombManager bombManager) {}

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Kondoria kondoria : kondorias) {
            kondoria.draw(graphics2D);
        }
    }
}
