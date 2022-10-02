package demo.entity;

import demo.GamePanel;
import demo.ImagePath;
import demo.Keyboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player extends Entity implements ImagePath {
    GamePanel gamePanel;
    Keyboard keyboard;

    public Player(GamePanel gamePanel, Keyboard keyboard) {
        this.gamePanel = gamePanel;
        this.keyboard = keyboard;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 64;
        y = 64;
        speed = 3;
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
            System.out.println("ngu");
        }
    }

    public void update() {
        if (keyboard.up == true) {
            y -= speed;
            direction = "up";
        }
        if (keyboard.down == true) {
            y += speed;
            direction = "down";
        }
        if (keyboard.left == true) {
            x -= speed;
            direction = "left";
        }
        if (keyboard.right == true) {
            x += speed;
            direction = "right";
        }
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
        switch (direction) {
            case "up": {
                if(spriteNum == 0) {
                    bufferedImage = up;
                } else if(spriteNum == 1) {
                    bufferedImage = up1;
                } else {
                    bufferedImage = up2;
                }

                break;
            }
            case "down": {
                if(spriteNum == 0) {
                    bufferedImage = down;
                } else if(spriteNum == 1) {
                    bufferedImage = down1;
                } else {
                    bufferedImage = down2;
                }
                break;
            }
            case "left": {
                if(spriteNum == 0) {
                    bufferedImage = left;
                } else if(spriteNum == 1) {
                    bufferedImage = left1;
                } else {
                    bufferedImage = left2;
                }
                break;
            }
            case "right": {
                if(spriteNum == 0) {
                    bufferedImage = right;
                } else if(spriteNum == 1) {
                    bufferedImage = right1;
                } else {
                    bufferedImage = right2;
                }
                break;
            }
            default:
                bufferedImage = down;
                break;

        }
        graphics2D.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
