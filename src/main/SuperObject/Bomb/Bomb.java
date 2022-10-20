package SuperObject.Bomb;

import Implements.Constant;
import Game.GamePanel;
import Implements.ImagePath;
import demo.entity.Entity;
import demo.entity.Player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Bomb extends Entity implements ImagePath, Constant {
    public boolean set = false;
    private int time = 0;

    private int collisionTime = -15;
    private GamePanel gamePanel;

    public boolean exploded  = false;

    private BufferedImage bufferedImage, bufferedImage1, bufferedImage2;
    public Explosion explosion;

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
            collisionTime = 0;
            this.gamePanel = gamePanel;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setBomb(Player player, BombManager bombManager) {
        boolean check = true;
        int tempX = ((player.x + tileSize / 2) / tileSize) * tileSize;
        int tempY = ((player.y + tileSize / 2) / tileSize) * tileSize;
        for (Bomb bomb : bombManager.bombs) {
            if (bomb.checkCollision(tempX / tileSize + 1, tempY / tileSize + 1) && bomb.set) {
                check = false;
            }
        }
        if (check && !set) {
            this.x = tempX;
            this.y = tempY;
            set = true;
            time = 0;
            collisionTime = 0;
            explosion = new Explosion(gamePanel);
            explosion.setCoordinates(this, player);
            explosion.checkCollisionExploded();
        }
    }

    public void update() {
        if (set) {
            collisionTime++;
            spriteCounter++;
            time ++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
            if (collisionTime >= 500) collision = true;
        } else {
            spriteNum = -1;
        }
        if (time >= 100) {
            explode();
        }
    }

    public void explode() {
        exploded = true;
        explosion.update();
        if (explosion.spriteNum >= 3) {
           set = false;
           collision = false;
        }
    }


    public void draw(Graphics2D graphics2D) {
        if (exploded) {
            explosion.draw(graphics2D);
            exploded = false;
        } else {
            if (spriteNum == 0) {
                graphics2D.drawImage(bufferedImage, this.x, this.y, tileSize, tileSize , null);
            } else if (spriteNum == 1) {
                graphics2D.drawImage(bufferedImage1, this.x, this.y, tileSize, tileSize, null);
            } else if (spriteNum == 2) {
                graphics2D.drawImage(bufferedImage2, this.x, this.y, tileSize, tileSize, null);
            }

        }
    }

}
