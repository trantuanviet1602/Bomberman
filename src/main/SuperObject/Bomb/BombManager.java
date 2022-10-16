package SuperObject.Bomb;

import Implements.Constant;
import SuperObject.Bomb.Bomb;
import demo.GamePanel;
import demo.entity.Entity;
import demo.entity.Player.Player;

import java.awt.*;
import java.util.ArrayList;

public class BombManager {
    public ArrayList<Bomb> bombs = new ArrayList<>();
    public GamePanel gamePanel;
    public BombManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        bombs.add(new Bomb(this.gamePanel));
    }

    public void setBomb(Player player) {
        for (Bomb bomb : bombs) {
            bomb.setBomb(player);
        }
    }

    public void update() {
        for (Bomb bomb : bombs) {
            bomb.update();
        }
    }

    public void killEntity(Entity entity) {
        for (Bomb bomb: bombs) {
            if (bomb.explosion.isExploding() && bomb.explosion.killed(entity)) {
                entity.death = true;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        for (Bomb bomb : bombs) {
            bomb.draw(graphics2D);
        }
    }
}
