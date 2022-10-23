package SuperObject.Bomb;

import Implements.Constant;
import Game.GamePanel;
import Implements.ImagePath;
import demo.entity.Entity;
import demo.entity.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion extends Entity implements ImagePath, Constant {
    public int bombLength;

    private int mapX, mapY;

    private int counter = 0;
    public int spriteNum = 0;

    private int validUp = 0, validDown = 0, validRight = 0, validLeft = 0;
    private boolean stopUp, stopDown, stopLeft, stopRight;
    GamePanel gamePanel;
    ExplosionImage explosionImage = new ExplosionImage();
    public Explosion(GamePanel gamePanel) {
        this.x = 0;
        this.y = 0;
        this.gamePanel = gamePanel;
        bombLength = 1;
    }



    public void setCoordinates(Bomb bomb, Player player) {
        this.x = bomb.x;
        this.y = bomb.y;
        //TODO: Thực hiện lấy tọa độ của Bomb trên bản đồ, để thực hiện CheckCollision.
        mapX = x / tileSize;
        mapY = y / tileSize;
        //TODO: Khởi tạo các giá trị mặc định cho CheckCollision.
        validLeft = 0;
        validRight = 0;
        validUp = 0;
        validDown = 0;
        stopDown = false;
        stopLeft = false;
        stopRight = false;
        stopUp = false;
        bombLength = player.bombLength;
    }


    public void update() {
        if (spriteNum != 3) {
            counter++;
        }
        if (counter > 5) {
            spriteNum ++;
            counter = 0;
        }
    }

    public boolean killed(Entity entity) {
        //TODO: Kiểm tra xem Bomb có giết được ai hay không.
        return entity.x >= this.x - validLeft  * tileSize - 1 && entity.x <= this.x + (validRight + 1) * tileSize - 3
                && ((entity.y + tileSize / 2)/ tileSize ) * tileSize == this.y
                || ((entity.x + tileSize / 2)/ tileSize ) * tileSize == this.x
                && entity.y >= this.y - (validUp) * tileSize - 1 && entity.y <= this.y + (validDown + 1) * tileSize - 3;
    }

    public void checkCollisionExploded() {

        //TODO: CheckCollision cho Bomb để tạo flame.
        System.out.println(this.bombLength);
        for (int i = 1; i <= this.bombLength ; i++) {
            if (!stopRight && gamePanel.tileManager.gameMap.mapTile[mapY]
                    .charAt(Math.min(mapX + i, maxScreenCol - 1)) != '#') validRight++;
            else stopRight = true;
            if (!stopLeft && gamePanel.tileManager.gameMap.mapTile[mapY]
                    .charAt(Math.max(mapX - i, 0)) != '#') validLeft++;
            else stopLeft = true;
            if (!stopUp && gamePanel.tileManager.gameMap.mapTile[Math.max(mapY - i, 0)]
                    .charAt(mapX) != '#') validUp++;
            else stopUp = true;
            if (!stopDown && gamePanel.tileManager.gameMap.mapTile[Math.min(mapY + i, maxScreenRow - 1)]
                    .charAt(mapX) != '#') validDown++;
            else stopDown = true;
        }

    }

    public boolean isExploding() {
        return spriteNum > 0 && spriteNum < 3;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage bombExplode = null, explodeVertical = null, explodeHorizontal = null,
                leftLast = null, rightLast = null, topLast = null, downLast = null;
        if(spriteNum != 3) {
            bombExplode = explosionImage.bombExploded[spriteNum];
            explodeVertical = explosionImage.explosionVertical[spriteNum];
            explodeHorizontal = explosionImage.explosionHorizontal[spriteNum];
            leftLast = explosionImage.explosionHorizontalLeftLast[spriteNum];
            rightLast = explosionImage.explosionHorizontalRightLast[spriteNum];
            topLast = explosionImage.explosionVerticalTopLast[spriteNum];
            downLast = explosionImage.explosionVerticalDownLast[spriteNum];
        }

        for (int i = - validRight + 1 ; i <= validLeft - 1; i++) {
            graphics2D.drawImage(explodeHorizontal, this.x - i * tileSize , this.y , tileSize, tileSize, null);
        }
        for (int i = - validDown + 1 ; i <= validUp - 1; i++) {
            graphics2D.drawImage(explodeVertical, this.x , this.y - i * tileSize, tileSize, tileSize, null);
        }
        graphics2D.drawImage(bombExplode, this.x, this.y, tileSize, tileSize, null);
        if (validLeft > 0) graphics2D.drawImage(leftLast, this.x - validLeft * tileSize , this.y , tileSize, tileSize, null);
        if (validRight > 0) graphics2D.drawImage(rightLast, this.x + validRight * tileSize, this.y, tileSize, tileSize, null);
        if (validUp > 0) graphics2D.drawImage(topLast, this.x, this.y - validUp * tileSize, tileSize, tileSize, null);
        if (validDown > 0) graphics2D.drawImage(downLast, this.x, this.y + validDown * tileSize, tileSize, tileSize, null);
    }

    public void increaseBombLength() {
        this.bombLength = this.bombLength + 1;
        //System.out.println(bombLength);
    }
}
