package SuperObject.Bomb;

import Game.GamePanel;
import demo.entity.Entity;
import demo.entity.Player.EntityManagement;
import demo.entity.Player.Player;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

public class BombManager extends EntityManagement {
    public ArrayList<Bomb> bombs = new ArrayList<>();

    public GamePanel gamePanel;
    public BombManager(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        bombs.add(new Bomb(this.gamePanel));;
    }

    @Override
    public void update(Player player) {}

    @Override
    public void update(Player player, BombManager bombManager) {}

    @Override
    public void update(BombManager bombManager) {}

    public void setBomb(Player player) {
        for (Bomb bomb : bombs) {
            if (!bomb.set) {
                bomb.setBomb(player, this);
                break;
            }
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

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Bomb bomb : bombs) {
            bomb.draw(graphics2D);
        }
    }

    public void increaseBombFlames() {
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).explosion.increaseBombLength();
            System.out.println(bombs.get(i).explosion.bombLength);
        }
    }

    public void addBomb() {
        bombs.add(new Bomb(gamePanel));
    }
}
