package SuperObject.Bomb;

import Game.GamePanel;
import demo.entity.Entity;
import demo.entity.EntityManagement;
import demo.entity.Player.Player;
import demo.tile.TileManager;

import java.awt.*;
import java.util.ArrayList;

public class BombManager extends EntityManagement {
    public ArrayList<Bomb> bombs = new ArrayList<>();

    public GamePanel gamePanel;
    public BombManager(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        bombs.add(new Bomb(this.gamePanel));
    }

    @Override
    public void update(Player player) {}

    @Override
    public void update(Player player, BombManager bombManager) {}

    public void reset() {
        bombs = new ArrayList<>();
        bombs.add(new Bomb(gamePanel));
    }

    @Override
    public void update(BombManager bombManager) {}

    public void setBomb(Player player, TileManager tileManager) {
        for (Bomb bomb : bombs) {
            if (!bomb.set) {
                bomb.setBomb(player, this, tileManager);
                break;
            }
        }
    }

    public void update(TileManager tileManager) {
        for (Bomb bomb : bombs) {
            bomb.update(tileManager, this);
        }
    }

    /**
     * TODO: Kiểm tra xem Entity có bị tiêu diệt hay không, nếu như Bomb đang nổ và Entity đang ở gần.
     */
    public void killEntity(Entity entity) {
        for (Bomb bomb: bombs) {
            if (bomb.explosion.isExploding() && bomb.explosion.killed(entity)) {
                entity.setDeath(true);
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Bomb bomb : bombs) {
            bomb.draw(graphics2D);
        }
    }


    public void addBomb() {

        bombs.add(new Bomb(gamePanel));
        System.out.println(bombs.size());
    }
}
