package demo.entity;

import demo.GamePanel;
import demo.Keyboard;
import demo.entity.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public abstract class Entity {
    public int x,y;
    public int speed;


    public boolean death  = false;

    public String direction = "";

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Rectangle solidArea;
    public boolean upCollision, rightCollision, downCollision, leftCollision = false;

    public GamePanel gamePanel;

    public boolean collision;

    public boolean checkPosition(Player player) {
       return true;
    }


}
