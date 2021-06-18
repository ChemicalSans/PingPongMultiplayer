package Client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageLoader {

    BufferedImage öe;

    public ImageLoader() {
        try {

            öe = ImageIO.read(new File("src\\Sprites\\Skin_Österreich.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
