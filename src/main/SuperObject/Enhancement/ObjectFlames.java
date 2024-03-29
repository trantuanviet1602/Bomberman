package SuperObject.Enhancement;

import Game.GamePanel;
import Implements.ImagePath;
import demo.entity.Player.Player;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectFlames extends Enhancement {
    public ObjectFlames(int x, int y, GamePanel gamePanel) {
        try {
            this.bufferedImage = ImageIO.read(new File(ImagePath.FLAMES_OBJECT));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public void update(Player player) {
        super.update(player);
        if (this.death) {
            player.bombLength++;
        }
    }
}
