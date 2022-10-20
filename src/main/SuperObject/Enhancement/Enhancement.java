package SuperObject.Enhancement;

import Implements.Constant;
import demo.entity.Entity;
import demo.entity.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enhancement extends Entity implements Constant {
    public void update(Player player) {
        if (checkCollision((player.x + tileSize / 2) / tileSize + 1, (player.y + tileSize / 2) / tileSize + 1)) {
            this.death = true;
            System.out.println("yes");
        }
    }
    public BufferedImage bufferedImage;
    public void draw(Graphics2D graphics2D) {
        if(!death) {
            graphics2D.drawImage(bufferedImage, x, y, tileSize, tileSize, null);
        }
    }
}
