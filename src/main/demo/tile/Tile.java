package demo.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tile {
    public BufferedImage bufferedImage;
    public boolean collision = false;

    public Tile(String path) {
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
