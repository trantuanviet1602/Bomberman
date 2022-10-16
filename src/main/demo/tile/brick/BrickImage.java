package demo.tile.brick;

import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BrickImage implements ImagePath {
    public BufferedImage brick;
    public BufferedImage[] brickExplosion;
    public BrickImage() {
        try {
            brick = ImageIO.read(new File(BRICK_PATH));
            brickExplosion = new BufferedImage[] {ImageIO.read(new File(BRICK_EXPLOSION[0])),
                    ImageIO.read(new File(BRICK_EXPLOSION[2])), ImageIO.read(new File(BRICK_EXPLOSION[2]))};
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
