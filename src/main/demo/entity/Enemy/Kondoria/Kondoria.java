package demo.entity.Enemy.Kondoria;

import Game.GamePanel;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.Enemy.Enemy;
import demo.entity.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Kondoria extends Enemy {
    private boolean setCoordinate = false;
    int currentX ;
    int currentY ;

    int randomLength;
    KondoriaImage kondoriaImage = new KondoriaImage();
    public Kondoria(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        solidArea = new Rectangle(32 , 32  , Constant.tileSize - 2, Constant.tileSize - 2);
        this.gamePanel = gamePanel;
        direction = "left";
        this.speed = 1;
    }

    public boolean isPosition(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public void update(Player player, BombManager bombManager) {
        if (!death) {
            upCollision = false;
            downCollision = false;
            leftCollision = false;
            rightCollision = false;

            if(!setCoordinate) {
                currentX = this.x;
                currentY = this.y;
                randomLength = (int) (Math.random() * 100) % 3 + 1;
                setCoordinate = true;
            }
            if (direction.equals("up")) {
                gamePanel.collisionCheck.CheckTile(this);
                if(!upCollision) this.y = Math.max(this.y - speed, Constant.tileSize);
                if (upCollision||isPosition(currentX, Math.max(currentY - randomLength * Constant.tileSize, Constant.tileSize))) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "right";
                    }
                    setCoordinate = false;
                }

            }

            if (direction.equals("down")) {
                gamePanel.collisionCheck.CheckTile(this);
                if(!downCollision) this.y = Math.min(this.y + speed, (Constant.maxScreenRow - 2) * Constant.tileSize);
                if (downCollision||isPosition(currentX, Math.min(currentY + randomLength * Constant.tileSize,
                        (Constant.maxScreenRow - 2) * Constant.tileSize))) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "up";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "right";
                    }
                    setCoordinate = false;
                }

            }

            if (direction.equals("left")) {
                gamePanel.collisionCheck.CheckTile(this);
                if(!leftCollision) this.x = Math.max(Constant.tileSize, this.x - speed);
                if (leftCollision||isPosition(Math.max(currentX - randomLength * Constant.tileSize, Constant.tileSize), currentY)) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "up";
                    } else {
                        direction = "right";
                    }
                    setCoordinate = false;
                }

            }

            if (direction.equals("right")) {
                gamePanel.collisionCheck.CheckTile(this);
                if(!rightCollision) this.x = Math.min(this.x + speed, (Constant.maxScreenCol - 2) * Constant.tileSize);
                if (rightCollision||isPosition(Math.min(currentX + randomLength * Constant.tileSize,
                        (Constant.maxScreenCol - 2) * Constant.tileSize), currentY)) {
                    int newDirection = (int) (Math.random() * 100) % 3;
                    if (newDirection == 0) {
                        direction = "down";
                    } else if (newDirection == 1) {
                        direction = "left";
                    } else {
                        direction = "up";
                    }
                    setCoordinate = false;
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
        BufferedImage bufferedImage ;

        if (!death) {
            //TODO: Thay đổi BufferedImage dựa trên hướng đi của nhân vật và tạo animation.
            switch (direction) {

                case "left" -> bufferedImage = kondoriaImage.left[spriteNum];
                case "right" -> bufferedImage = kondoriaImage.right[spriteNum];
                default -> bufferedImage = kondoriaImage.left[0];
            }
        } else {
            bufferedImage = kondoriaImage.death;
        }
        graphics2D.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
