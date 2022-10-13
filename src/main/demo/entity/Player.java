package demo.entity;

import SuperObject.Bomb;
import demo.Constant;
import demo.GamePanel;
import demo.ImagePath;
import demo.Keyboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player extends Entity implements Constant, ImagePath {
    GamePanel gamePanel;
    Keyboard keyboard;

    Bomb bomb;


    public Player(GamePanel gamePanel, Keyboard keyboard) {
        this.gamePanel = gamePanel;
        this.keyboard = keyboard;
        setDefaultValues();
        getPlayerImage();
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


    public void getPlayerImage() {
        try {
            up = ImageIO.read(new File(UP_PATH));
            up1 = ImageIO.read(new File(UP1_PATH));
            up2 = ImageIO.read(new File(UP2_PATH));
            down = ImageIO.read(new File(DOWN_PATH));
            down1 = ImageIO.read(new File(DOWN1_PATH));
            down2 = ImageIO.read(new File(DOWN2_PATH));
            left = ImageIO.read(new File(LEFT_PATH));
            left1 = ImageIO.read(new File(LEFT1_PATH));
            left2 = ImageIO.read(new File(LEFT2_PATH));
            right = ImageIO.read(new File(RIGHT_PATH));
            right1 = ImageIO.read(new File(RIGHT1_PATH));
            right2 = ImageIO.read(new File(RIGHT2_PATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() {
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
            bomb.setBomb(this);
        }

        bomb.update();


        if(keyboard.up|| keyboard.down|| keyboard.left|| keyboard.right) {
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage bufferedImage ;

        //TODO: Thay đổi BufferedImage dựa trên hướng đi của nhân vật và tạo animation.
        switch (direction) {
            case "up" -> {
                if (spriteNum == 0) {
                    bufferedImage = up;
                } else if (spriteNum == 1) {
                    bufferedImage = up1;
                } else {
                    bufferedImage = up2;
                }
            }

            case "down" -> {
                if (spriteNum == 0) {
                    bufferedImage = down;
                } else if (spriteNum == 1) {
                    bufferedImage = down1;
                } else {
                    bufferedImage = down2;
                }
            }

            case "left" -> {
                if (spriteNum == 0) {
                    bufferedImage = left;
                } else if (spriteNum == 1) {
                    bufferedImage = left1;
                } else {
                    bufferedImage = left2;
                }
            }

            case "right" -> {
                if (spriteNum == 0) {
                    bufferedImage = right;
                } else if (spriteNum == 1) {
                    bufferedImage = right1;
                } else {
                    bufferedImage = right2;
                }
            }
            default -> bufferedImage = down;
        }
        bomb.draw(graphics2D);
        graphics2D.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
