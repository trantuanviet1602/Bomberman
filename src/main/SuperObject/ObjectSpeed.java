package SuperObject;

import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectSpeed extends  SuperObject implements ImagePath {
    public ObjectSpeed() {
        this.name = "Speed";


        try {
            this.bufferedImage = ImageIO.read(new File(SPEED_OBJECT));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
