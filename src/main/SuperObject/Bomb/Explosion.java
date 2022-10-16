package SuperObject.Bomb;

import Implements.Constant;
import demo.GamePanel;
import Implements.ImagePath;
import demo.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Explosion implements ImagePath, Constant {
    private int x;
    private int y;
    private int bombLength = 1;

    private int mapX, mapY;

    private int counter = 0;
    private int spriteNum = 0;

    private int validUp = 0, validDown = 0, validRight = 0, validLeft = 0;
    private BufferedImage bombExploded, bombExploded1, bombExploded2
    , explosionHorizontal
    , explosionHorizontal1
    , explosionHorizontal2
    , explosionHorizontalLeftLast
    , explosionHorizontalLeftLast1
    , explosionHorizontalLeftLast2
    , explosionHorizontalRightLast
    , explosionHorizontalRightLast1
    , explosionHorizontalRightLast2
    , explosionVertical
    , explosionVertical1
    , explosionVertical2
    , explosionVerticalTopLast
    , explosionVerticalTopLast1
    , explosionVerticalTopLast2
    , explosionVerticalDownLast
    , explosionVerticalDownLast1
    , explosionVerticalDownLast2;
    GamePanel gamePanel;

    public Explosion(GamePanel gamePanel) {
        setImage();
        this.x = 0;
        this.y = 0;
        this.gamePanel = gamePanel;
    }



    public void setCoordinates(Bomb bomb) {
        this.x = bomb.x;
        this.y = bomb.y;
        //TODO: Thực hiện lấy tọa độ của Bomb trên bản đồ, để thực hiện CheckCollision.
        mapX = x / tileSize;
        mapY = y / tileSize;
        //TODO: Khởi tạo các giá trị mặc định cho CheckCollision, kiểm tra từ Up -> Down và từ Left -> Right.
        validLeft = bombLength;
        validRight = 0;
        validUp = bombLength;
        validDown = 0;
    }

    public void setImage()  {
        try {
            bombExploded = ImageIO.read(new File(BOMB_EXPLODED));
            bombExploded1 = ImageIO.read(new File(BOMB_EXPLODED_1));
            bombExploded2 = ImageIO.read(new File(BOMB_EXPLODED_2));
            explosionHorizontal = ImageIO.read(new File(EXPLOSION_HORIZONTAL));
            explosionHorizontal1 = ImageIO.read(new File(EXPLOSION_HORIZONTAL_1));
            explosionHorizontal2 = ImageIO.read(new File(EXPLOSION_HORIZONTAL_2));
            explosionVerticalDownLast = ImageIO.read(new File(EXPLOSION_VERTICAL_DOWN_LAST));
            explosionVerticalDownLast1 = ImageIO.read(new File(EXPLOSION_VERTICAL_DOWN_LAST_1));
            explosionVerticalDownLast2 = ImageIO.read(new File(EXPLOSION_VERTICAL_DOWN_LAST_2));
            explosionVerticalTopLast = ImageIO.read(new File(EXPLOSION_VERTICAL_TOP_LAST));
            explosionVerticalTopLast1 = ImageIO.read(new File(EXPLOSION_VERTICAL_TOP_LAST_1));
            explosionVerticalTopLast2 = ImageIO.read(new File(EXPLOSION_VERTICAL_TOP_LAST_2));
            explosionVertical = ImageIO.read(new File(EXPLOSION_VERTICAL));
            explosionVertical1 = ImageIO.read(new File(EXPLOSION_VERTICAL_1));
            explosionVertical2 = ImageIO.read(new File(EXPLOSION_VERTICAL_2));
            explosionHorizontalLeftLast = ImageIO.read(new File(EXPLOSION_HORIZONTAL_LEFT_LAST));
            explosionHorizontalLeftLast1 = ImageIO.read(new File(EXPLOSION_HORIZONTAL_LEFT_LAST_1));
            explosionHorizontalLeftLast2 = ImageIO.read(new File(EXPLOSION_HORIZONTAL_LEFT_LAST_2));
            explosionHorizontalRightLast = ImageIO.read(new File(EXPLOSION_HORIZONTAL_RIGHT_LAST));
            explosionHorizontalRightLast1 = ImageIO.read(new File(EXPLOSION_HORIZONTAL_RIGHT_LAST_1));
            explosionHorizontalRightLast2 = ImageIO.read(new File(EXPLOSION_HORIZONTAL_RIGHT_LAST_2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
        if( entity.x >= this.x - validLeft * tileSize  && entity.x <= this.x + ( validRight + 1) * tileSize - 1
                && entity.y >= this.y - validUp * tileSize && entity.y <= this.y + ( validDown + 1) * tileSize - 1) {
            entity.death = true;
            return true;
        }
        else return false;
    }

    public void checkCollisionExploded() {

        //TODO: CheckCollision cho Bomb để tạo flame, kiểm tra từ Up -> Down và từ Left -> Right.
        for (int i = Math.max(0, mapX - bombLength);
             i <= Math.min(maxScreenCol - 1, mapX + bombLength); i++) {
            if (i < mapX) {
                if (gamePanel.tileManager.gameMap.mapTile[mapY].charAt(i) == '#') {
                    validLeft = Math.max(validLeft - mapX + i, 0);
                }
            } else if (i > mapX) {
                if (gamePanel.tileManager.gameMap.mapTile[mapY].charAt(i) != '#') {
                    validRight++;
                }
            }
        }

        for (int i = Math.max(0, mapY - bombLength);
             i <= Math.min(maxScreenRow - 1, mapY + bombLength); i++) {
            if (i < mapY) {
                if (gamePanel.tileManager.gameMap.mapTile[i].charAt(mapX) == '#') {
                    validUp = Math.max(validUp - mapY + i, 0);
                }
            } else if (i > mapY) {
                if (gamePanel.tileManager.gameMap.mapTile[i].charAt(mapX) != '#') {
                    validDown++;
                }
            }
        }

        System.out.println(validUp + " " + validDown + " " + validLeft + " " + validRight);
    }

    public boolean isExploding() {
        return spriteNum > 0 && spriteNum < 3;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage bombExplode = null, explodeVertical = null, explodeHorizontal = null,
                leftLast = null, rightLast = null, topLast = null, downLast = null;
        if (spriteNum == 0) {
            bombExplode = bombExploded;
            explodeVertical = explosionVertical;
            explodeHorizontal = explosionHorizontal;
            leftLast = explosionHorizontalLeftLast;
            rightLast = explosionHorizontalRightLast;
            topLast = explosionVerticalTopLast;
            downLast = explosionVerticalDownLast;
        } else if (spriteNum == 1) {
            bombExplode = bombExploded1;
            explodeVertical = explosionVertical1;
            explodeHorizontal = explosionHorizontal1;
            leftLast = explosionHorizontalLeftLast1;
            rightLast = explosionHorizontalRightLast1;
            topLast = explosionVerticalTopLast1;
            downLast = explosionVerticalDownLast1;
        } else if (spriteNum == 2) {
            bombExplode = bombExploded2;
            explodeVertical = explosionVertical2;
            explodeHorizontal = explosionHorizontal2;
            leftLast = explosionHorizontalLeftLast2;
            rightLast = explosionHorizontalRightLast2;
            topLast = explosionVerticalTopLast2;
            downLast = explosionVerticalDownLast2;
        }
        for (int i = - validRight + 1; i < validLeft; i++) {
            graphics2D.drawImage(explodeHorizontal, this.x , this.y - i * tileSize, tileSize, tileSize, null);
        }
        for (int i = - validUp + 1; i < validDown; i++) {
            graphics2D.drawImage(explodeVertical, this.x , this.y - i * tileSize, tileSize, tileSize, null);
        }
        graphics2D.drawImage(bombExplode, this.x, this.y, tileSize, tileSize, null);
        if (validLeft > 0) graphics2D.drawImage(leftLast, this.x - validLeft * tileSize , this.y , tileSize, tileSize, null);
        if (validRight > 0) graphics2D.drawImage(rightLast, this.x + validRight * tileSize, this.y, tileSize, tileSize, null);
        if (validUp > 0) graphics2D.drawImage(topLast, this.x, this.y - validUp * tileSize, tileSize, tileSize, null);
        if (validDown > 0) graphics2D.drawImage(downLast, this.x, this.y + validDown * tileSize, tileSize, tileSize, null);
    }

}
