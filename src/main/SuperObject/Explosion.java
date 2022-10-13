package SuperObject;

import demo.Constant;
import demo.GamePanel;
import demo.ImagePath;

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
    private BufferedImage bombExploded, bombExploded1, bombExploded2;
    GamePanel gamePanel;

    public Explosion(GamePanel gamePanel) {
        setImage();
        this.x = 0;
        this.y = 0;
        this.gamePanel = gamePanel;
    }

    public void checkCollisionExploded() {
        for (int i = Math.max(0, mapX - bombLength);
             i <= Math.min(maxScreenCol - 1, mapX + bombLength); i++) {
            if (i < mapX) {
                if (gamePanel.tileManager.map.mapTile[mapY].charAt(i) == '#') {
                    validLeft = Math.max(validLeft - mapX + i, 0);
                }
            } else if (i > mapX) {
                if (gamePanel.tileManager.map.mapTile[mapY].charAt(i) != '#') {
                    validRight++;
                }
            }
        }

        for (int i = Math.max(0, mapY - bombLength);
             i <= Math.min(maxScreenRow - 1, mapY + bombLength); i++) {
            if (i < mapY) {
                if (gamePanel.tileManager.map.mapTile[i].charAt(mapX) == '#') {
                    validUp = Math.max(validUp - mapY + i, 0);
                }
            } else if (i > mapY) {
                if (gamePanel.tileManager.map.mapTile[i].charAt(mapX) != '#') {
                    validDown++;
                }
            }
        }

        System.out.println(validUp + " " + validDown + " " + validLeft + " " + validRight);
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

    public void draw(Graphics2D graphics2D) {
        if (spriteNum == 0) {
            graphics2D.drawImage(bombExploded, this.x, this.y, tileSize, tileSize, null);
        } else if (spriteNum == 1) {
            graphics2D.drawImage(bombExploded1, this.x, this.y, tileSize, tileSize, null);
        } else if (spriteNum == 2) {
            graphics2D.drawImage(bombExploded2, this.x, this.y, tileSize, tileSize, null);
        }
    }
}
