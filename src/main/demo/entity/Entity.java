package demo.entity;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;

import java.awt.*;

public abstract class Entity {
    protected int x,y;
    protected int speed;
    protected boolean death  = false;

    protected String direction = "";

    protected int spriteCounter = 0;
    protected int spriteNum = 0;

    protected int deathSpriteNum = 0;

    protected Rectangle solidArea;
    protected boolean upCollision, rightCollision, downCollision, leftCollision = false;

    protected GamePanel gamePanel;

    protected boolean collision;


    public void draw(Graphics2D graphics2D) {}
    public void update(BombManager bombManager) {}

    public boolean checkCollision(int x, int y) {
        return x - 1 == this.x / Constant.tileSize  && y - 1 == this.y / Constant.tileSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDeathSpriteNum() {
        return deathSpriteNum;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isCollision() {
        return collision;
    }

    public boolean isDownCollision() {
        return downCollision;
    }

    public boolean isLeftCollision() {
        return leftCollision;
    }

    public boolean isRightCollision() {
        return rightCollision;
    }

    public boolean isUpCollision() {
        return upCollision;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setDownCollision(boolean downCollision) {
        this.downCollision = downCollision;
    }

    public void setRightCollision(boolean rightCollision) {
        this.rightCollision = rightCollision;
    }

    public void setUpCollision(boolean upCollision) {
        this.upCollision = upCollision;
    }

    public void setLeftCollision(boolean leftCollision) {
        this.leftCollision = leftCollision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public String getDirection() {
        return direction;
    }
}
