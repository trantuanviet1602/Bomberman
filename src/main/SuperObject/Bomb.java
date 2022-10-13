package SuperObject;

import demo.Constant;
import demo.GamePanel;
import demo.ImagePath;
import demo.entity.Entity;
import demo.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Bomb extends Entity implements ImagePath, Constant {

    public boolean set = false;

    private int time = 0;
    private GamePanel gamePanel;

    public boolean exploded  = false;

    private BufferedImage bufferedImage, bufferedImage1, bufferedImage2;
    Explosion explosion;

    public void loadImage() {
        try {
            this.bufferedImage = ImageIO.read(new File(BOMB_OBJECT));
            this.bufferedImage1 = ImageIO.read(new File(BOMB1_OBJECT));
            this.bufferedImage2 = ImageIO.read(new File(BOMB2_OBJECT));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Bomb(GamePanel gamePanel) {
        try {
            this.x = - tileSize;
            this.y = - tileSize;
            loadImage();
            explosion = new Explosion(gamePanel);
            this.gamePanel = gamePanel;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setBomb(Player player) {
        if (!set) {
            this.x = ((player.x + tileSize / 2) / tileSize) * tileSize;
            this.y = ((player.y + tileSize / 2) / tileSize) * tileSize;
            set = true;
            time = 0;
            explosion = new Explosion(gamePanel);
            explosion.setCoordinates(this);
            explosion.checkCollisionExploded();
        }
    }

    public void update() {
        if (set) {
            spriteCounter++;
            time ++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 0;
        }
        if (time == 100) {
            explode();
        }
    }

    public void explode() {
        exploded = true;
        explosion.update();
        set = false;


    }
    public void draw(Graphics2D graphics2D) {
        if (exploded) {
            explosion.draw(graphics2D);
            exploded = false;
            new Bomb(gamePanel);
        } else {
            if (spriteNum == 0) {
                graphics2D.drawImage(bufferedImage, this.x, this.y, tileSize, tileSize , null);
            } else if (spriteNum == 1) {
                graphics2D.drawImage(bufferedImage1, this.x, this.y, tileSize, tileSize, null);
            } else {
                graphics2D.drawImage(bufferedImage2, this.x, this.y, tileSize, tileSize, null);
            }

        }
    }

}
