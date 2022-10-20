package demo.entity.Enemy.Balloom;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import demo.entity.Enemy.Enemy;
import demo.entity.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Balloom extends Enemy {
    BalloomImage balloomImage = new BalloomImage();
    public Balloom(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        solidArea = new Rectangle(32 , 32  , Constant.tileSize - 2, Constant.tileSize - 2);
        this.gamePanel = gamePanel;
        direction = "left";
        this.speed = 1;
    }

    @Override
    public void update(Player player, BombManager bombManager) {
        if (!death) {
            upCollision = false;
            downCollision = false;
            leftCollision = false;
            rightCollision = false;
            if (direction.equals("up")) {
                gamePanel.collisionCheck.CheckTile(this);
                if (!upCollision) {
                    this.y -= speed;
                } else {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "right";
                    }
                }
            }

            if (direction.equals("down")) {
                gamePanel.collisionCheck.CheckTile(this);
                if (!downCollision) {
                    this.y += speed;
                } else {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "up";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "right";
                    }
                }
            }

            if (direction.equals("left")) {
                gamePanel.collisionCheck.CheckTile(this);
                if (!leftCollision) {
                    this.x -= speed;
                } else {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "up";
                    } else {
                        direction = "right";
                    }
                }
            }

            if (direction.equals("right")) {
                gamePanel.collisionCheck.CheckTile(this);
                if (!rightCollision) {
                    this.x += speed;
                } else {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "up";
                    }
                }
            }
            killPlayer(player);
            bombManager.killEntity(this);
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
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
                    bufferedImage = balloomImage.left[spriteNum];
                }

                case "right" -> {
                    bufferedImage = balloomImage.right[spriteNum];
                }
                default -> bufferedImage = balloomImage.left[0];
            }
        } else {
            bufferedImage = balloomImage.death;
        }
        graphics2D.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
