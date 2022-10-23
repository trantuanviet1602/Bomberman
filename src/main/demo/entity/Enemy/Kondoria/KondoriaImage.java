package demo.entity.Enemy.Kondoria;

import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class KondoriaImage implements ImagePath{
    public BufferedImage[] left, right;
    public BufferedImage death;
    public KondoriaImage() {
        try {
            left = new BufferedImage[] {ImageIO.read(new File(KONDORIA_LEFT[0])),
                    ImageIO.read(new File(KONDORIA_LEFT[1])), ImageIO.read(new File(KONDORIA_LEFT[2]))};
            right = new BufferedImage[] {ImageIO.read(new File(KONDORIA_RIGHT[0])),
                    ImageIO.read(new File(KONDORIA_RIGHT[1])), ImageIO.read(new File(KONDORIA_RIGHT[2]))};
            death = ImageIO.read(new File(KONDORIA_DEATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
