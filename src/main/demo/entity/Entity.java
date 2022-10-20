package demo.entity;

import Implements.Constant;
import SuperObject.Bomb.BombManager;
import Game.GamePanel;
import demo.entity.Player.Player;

import javax.swing.plaf.ComponentUI;
import java.awt.*;

public abstract class Entity {
    public int x,y;
    public int speed;


    public boolean death  = false;

    public String direction = "";

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public int deathSpriteNum = 0;

    public Rectangle solidArea;
    public boolean upCollision, rightCollision, downCollision, leftCollision = false;

    public GamePanel gamePanel;

    public boolean collision;

    public boolean checkPosition(Player player) {
       return true;
    }

    public void draw(Graphics2D graphics2D) {};
    public void update(BombManager bombManager) {};

    public boolean checkCollision(int x, int y) {
        return x - 1 == this.x / Constant.tileSize  && y - 1 == this.y / Constant.tileSize;
    }


}
