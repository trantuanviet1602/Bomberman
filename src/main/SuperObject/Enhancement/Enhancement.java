package SuperObject.Enhancement;

import Implements.Constant;
import demo.Sound.SoundPath;
import demo.entity.Entity;
import demo.entity.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enhancement extends Entity implements Constant {

    //TODO: Class đóng vai trò thể hiện các Item nâng cấp trong game.
    public void update(Player player) {
        if (checkCollision((player.getX() + tileSize / 2) / tileSize + 1,
                (player.getY() + tileSize / 2) / tileSize + 1)) {
            this.death = true;
        }
        if (this.isDeath()) {
            gamePanel.playSE(SoundPath.ITEM_ADD);
        }
    }
    public BufferedImage bufferedImage;
    public void draw(Graphics2D graphics2D) {
        if(!death) {
            graphics2D.drawImage(bufferedImage, x, y, tileSize, tileSize, null);
        }
    }
}
