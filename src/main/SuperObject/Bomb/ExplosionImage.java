package SuperObject.Bomb;

import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ExplosionImage implements ImagePath {
    public BufferedImage[] explosionVertical, explosionHorizontal,
    explosionVerticalTopLast, explosionVerticalDownLast,
    explosionHorizontalLeftLast, explosionHorizontalRightLast, bombExploded;

    public ExplosionImage() {
        try {
            explosionHorizontal = new BufferedImage[] {ImageIO.read(new File(EXPLOSION_HORIZONTAL)),
                    ImageIO.read(new File(EXPLOSION_HORIZONTAL_1)), ImageIO.read(new File(EXPLOSION_HORIZONTAL_2))};
            explosionVertical = new BufferedImage[] {ImageIO.read(new File(EXPLOSION_VERTICAL)),
                    ImageIO.read(new File(EXPLOSION_VERTICAL_1)), ImageIO.read(new File(EXPLOSION_VERTICAL_2))};
            explosionVerticalTopLast = new BufferedImage[] {ImageIO.read(new File(EXPLOSION_VERTICAL_TOP_LAST)),
                    ImageIO.read(new File(EXPLOSION_VERTICAL_TOP_LAST_1)), ImageIO.read(new File(EXPLOSION_VERTICAL_TOP_LAST_2))};
            explosionVerticalDownLast = new BufferedImage[] {ImageIO.read(new File(EXPLOSION_VERTICAL_DOWN_LAST)),
                    ImageIO.read(new File(EXPLOSION_VERTICAL_DOWN_LAST_1)), ImageIO.read(new File(EXPLOSION_VERTICAL_DOWN_LAST_2))};
            explosionHorizontalRightLast = new BufferedImage[] {ImageIO.read(new File(EXPLOSION_HORIZONTAL_RIGHT_LAST)),
                    ImageIO.read(new File(EXPLOSION_HORIZONTAL_RIGHT_LAST_1)), ImageIO.read(new File(EXPLOSION_HORIZONTAL_RIGHT_LAST_2))};
            explosionHorizontalLeftLast = new BufferedImage[] {ImageIO.read(new File(EXPLOSION_HORIZONTAL_LEFT_LAST)),
                    ImageIO.read(new File(EXPLOSION_HORIZONTAL_LEFT_LAST_1)), ImageIO.read(new File(EXPLOSION_HORIZONTAL_LEFT_LAST_2))};
            bombExploded = new BufferedImage[] {ImageIO.read(new File(BOMB_EXPLODED)), ImageIO.read(new File(BOMB_EXPLODED_1)), ImageIO.read(new File(BOMB_EXPLODED_2))};
        } catch (Exception e) {
            System.out.println("Cannot Load Explosion Image");
        }
    }
}
