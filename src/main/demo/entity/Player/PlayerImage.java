package demo.entity.Player;

import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlayerImage implements ImagePath {
    public BufferedImage[] up, down, left, right, death;
    public PlayerImage() {
        try {
            up = new BufferedImage[] {ImageIO.read(new File(UP_PATH)),
                    ImageIO.read(new File(UP1_PATH)), ImageIO.read(new File(UP2_PATH))};
            down = new BufferedImage[] {ImageIO.read(new File(DOWN_PATH)),
                    ImageIO.read(new File(DOWN1_PATH)), ImageIO.read(new File(DOWN2_PATH))};
            left = new BufferedImage[] {ImageIO.read(new File(LEFT_PATH)),
                    ImageIO.read(new File(LEFT1_PATH)), ImageIO.read(new File(LEFT2_PATH))};
            right = new BufferedImage[] {ImageIO.read(new File(RIGHT_PATH)),
                    ImageIO.read(new File(RIGHT1_PATH)), ImageIO.read(new File(RIGHT2_PATH))};
            death = new BufferedImage[] {ImageIO.read(new File(DEATH_PATH[0])),
                    ImageIO.read(new File(UP1_PATH)), ImageIO.read(new File(DEATH_PATH[2]))};
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
