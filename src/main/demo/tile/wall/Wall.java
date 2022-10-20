package demo.tile.wall;

import Implements.Constant;
import Implements.ImagePath;
import demo.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Wall extends Entity implements Constant{
    private BufferedImage bufferedImage;
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            bufferedImage = ImageIO.read(new File(ImagePath.WALL_PATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        collision = true;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(bufferedImage, x, y, tileSize, tileSize, null);
    }


}
