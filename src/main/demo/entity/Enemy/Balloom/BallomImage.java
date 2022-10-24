package demo.entity.Enemy.Balloom;

import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BallomImage implements ImagePath{
    public BufferedImage[] left, right;
    public BufferedImage death;
    public BallomImage() {
        try {
            left = new BufferedImage[] {ImageIO.read(new File(BALLOOM_LEFT[0])),
                    ImageIO.read(new File(BALLOOM_LEFT[1])), ImageIO.read(new File(BALLOOM_LEFT[2]))};
            right = new BufferedImage[] {ImageIO.read(new File(BALLOOM_RIGHT[0])),
                    ImageIO.read(new File(BALLOOM_RIGHT[1])), ImageIO.read(new File(BALLOOM_RIGHT[2]))};
            death = ImageIO.read(new File(BALLOOM_DEATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
