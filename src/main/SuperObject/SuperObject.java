package SuperObject;

import Implements.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject implements Constant {
    public BufferedImage bufferedImage;
    public String name;
    public boolean collision = false;
    public int x, y;
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(bufferedImage, x, y, tileSize, tileSize, null);
    }
}
