package demo.entity.Enemy;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.Entity;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;

public abstract class Enemy extends Entity {
    public void update(Player player, BombManager bombManager) {};

    public void draw(Graphics2D graphics2D) {};

    public void killPlayer(Player player) {
        if ((player.getX() / Constant.tileSize == this.x / Constant.tileSize
                && player.getY() / Constant.tileSize == this.y / Constant.tileSize) && ! this.death) {
            player.setDeath(true);
        }
    }


}
