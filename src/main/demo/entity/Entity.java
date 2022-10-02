package demo.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Entity {
    public int x,y;
    public int speed;

    public Image image;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
                        up, down, left, right;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;

}
