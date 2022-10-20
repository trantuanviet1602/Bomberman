package demo.entity.Enemy.Oneal;

import Game.GamePanel;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.Enemy.Balloom.BalloomImage;
import demo.entity.Enemy.Enemy;
import demo.entity.Entity;
import demo.entity.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Oneal extends Enemy {
    private int time = 0;
    OnealImage onealImage = new OnealImage();
    public Oneal(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        solidArea = new Rectangle(32 , 32  , Constant.tileSize - 2, Constant.tileSize - 2);
        this.gamePanel = gamePanel;
        direction = "left";
        this.speed = 1;
    }

    public void movement() {

    }

    @Override
    public void update(Player player, BombManager bombManager) {
        if (!death) {

            speed = Math.abs((int) (Math.random() * 100) % 3);
            if (direction.equals("up")) {
                this.y = Math.max(this.y - speed, Constant.tileSize);
                if (time == 64) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "right";
                    }
                    time = 0;
                }

            }

            if (direction.equals("down")) {
                this.y = Math.min(this.y + speed, (Constant.maxScreenRow - 2) * Constant.tileSize);
                if (time == 64) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "up";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "right";
                    }
                    time = 0;
                }

            }

            if (direction.equals("left")) {
                this.x = Math.max(Constant.tileSize, this.x - speed);
                if (time == 64) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "up";
                    } else {
                        direction = "right";
                    }
                    time = 0;
                }

            }

            if (direction.equals("right")) {
                this.x = Math.min(this.x + speed, (Constant.maxScreenCol - 2) * Constant.tileSize);
                if (time == 64) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "up";
                    }
                    time = 0;
                }

            }
            killPlayer(player);
            bombManager.killEntity(this);
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
            time ++ ;
        } else {
            spriteCounter ++ ;
            if (spriteCounter > 10) {
                if (deathSpriteNum != 3) deathSpriteNum = deathSpriteNum + 1;
                spriteCounter = 0;
            }
        }
    }



    public void draw(Graphics2D graphics2D) {
        BufferedImage bufferedImage = null ;

        if (!death) {
            //TODO: Thay đổi BufferedImage dựa trên hướng đi của nhân vật và tạo animation.
            switch (direction) {

                case "left" -> {
                    bufferedImage = onealImage.left[spriteNum];
                }

                case "right" -> {
                    bufferedImage = onealImage.right[spriteNum];
                }
                default -> bufferedImage = onealImage.left[0];
            }
        } else {
            bufferedImage = onealImage.death;
        }
        graphics2D.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
