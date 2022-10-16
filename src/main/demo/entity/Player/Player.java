package demo.entity.Player;

import SuperObject.Bomb.Bomb;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.GamePanel;
import Implements.ImagePath;
import demo.Keyboard;
import demo.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity implements Constant, ImagePath {

    public Bomb bomb;
    PlayerImage playerImage = new PlayerImage();
    public Keyboard keyboard;

    public Player(GamePanel gamePanel, Keyboard keyboard) {
        this.gamePanel = gamePanel;
        this.keyboard = keyboard;
        setDefaultValues();
        bomb = new Bomb(gamePanel);

        //TODO: Tạo vùng collision cho nhân vật.
        solidArea = new Rectangle(this.x, this.y, tileSize - 10, tileSize - 2 );
    }

    public void setDefaultValues() {
        x = 32;
        y = 32;
        speed = 2;
        direction = "down";
    }




    public void update(BombManager bombManager) {
        if (!death) {
            upCollision = false;
            downCollision = false;
            leftCollision = false;
            rightCollision = false;
            if (keyboard.up) {
                direction = "up";
                gamePanel.collisionCheck.CheckTile(this);
                if (!upCollision) y -= speed;
            }
            if (keyboard.down) {
                direction = "down";
                gamePanel.collisionCheck.CheckTile(this);
                if (!downCollision) y += speed;
            }
            if (keyboard.left) {
                direction = "left";
                gamePanel.collisionCheck.CheckTile(this);
                if (!leftCollision) x -= speed;
            }
            if (keyboard.right) {
                direction = "right";
                gamePanel.collisionCheck.CheckTile(this);
                if (!rightCollision) x += speed;
            }

            if (keyboard.space) {
                bombManager.setBomb(this);
            }

            bombManager.killEntity(this);

            bomb.update();
        }

        if(keyboard.up|| keyboard.down|| keyboard.left|| keyboard.right ) {
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 0;
        }

        if (death) {
            spriteCounter ++ ;
            if (spriteCounter > 100) {
                spriteNum = (spriteNum + 1);
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage bufferedImage = null ;

        if (!death) {
            //TODO: Thay đổi BufferedImage dựa trên hướng đi của nhân vật và tạo animation.
            switch (direction) {
                case "up" -> {
                    bufferedImage = playerImage.up[spriteNum];
                }

                case "down" -> {
                    bufferedImage = playerImage.down[spriteNum];
                }

                case "left" -> {
                    bufferedImage = playerImage.left[spriteNum];
                }

                case "right" -> {
                    bufferedImage = playerImage.right[spriteNum];
                }
                default -> bufferedImage = playerImage.down[0];
            }
        } else {
            try {
                bufferedImage = playerImage.death[spriteNum];
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        graphics2D.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
