package demo.tile.brick;

import SuperObject.Bomb.Bomb;
import Implements.Constant;
import Implements.ImagePath;
import SuperObject.Bomb.BombManager;
import demo.entity.Entity;

import java.awt.*;

public class Brick extends Entity implements ImagePath, Constant {

    BrickImage brickImage = new BrickImage();
    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        collision = true;
        death = false;
    }

    public void update(BombManager bombManager) {
        bombManager.killEntity(this);
        if (death) {
            spriteCounter ++ ;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        if (death) {
            graphics2D.drawImage(brickImage.brickExplosion[spriteNum],
                    this.x, this.y, tileSize, tileSize, null);
        } else {
            graphics2D.drawImage(brickImage.brick, this.x, this.y, tileSize, tileSize, null);
        }
    }

    public boolean checkCollision(int x, int y) {
        return x - 1 == this.x / tileSize  && y - 1 == this.y / tileSize;
    }
}
