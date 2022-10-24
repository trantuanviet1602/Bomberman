package SuperObject.Enhancement;

import Game.GamePanel;
import Implements.ImagePath;
import demo.tile.GameMap;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectBombPass extends Enhancement implements ImagePath {
    public ObjectBombPass(int x, int y, GamePanel gamePanel) {
        try {
            this.bufferedImage = ImageIO.read(new File(BOMBPASS_OBJECT));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.x = x;
        this.y = y;
    }
}
