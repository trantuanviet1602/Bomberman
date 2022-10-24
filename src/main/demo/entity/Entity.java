package demo.entity;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import demo.entity.Player.Player;

import javax.swing.plaf.ComponentUI;
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


    public void draw(Graphics2D graphics2D) {};
    public void update(BombManager bombManager) {};

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
}
