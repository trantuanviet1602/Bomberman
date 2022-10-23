package demo.entity.Enemy.Oneal;

import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class OnealImage implements ImagePath {
    public BufferedImage[] left, right;
    public BufferedImage death;
    public OnealImage() {
        try {
            left = new BufferedImage[] {ImageIO.read(new File(ONEAL_LEFT[0])), ImageIO.read(new File(ONEAL_LEFT[1])), ImageIO.read(new File(ONEAL_LEFT[2]))};
            right = new BufferedImage[] {ImageIO.read(new File(ONEAL_RIGHT[0])),ImageIO.read(new File(ONEAL_RIGHT[1])),ImageIO.read(new File(ONEAL_RIGHT[2]))};
            death = ImageIO.read(new File(ONEAL_DEATH));
        } catch (Exception e) {
            System.out.println("Cannot load image from Oneal");
        }
    }
}
