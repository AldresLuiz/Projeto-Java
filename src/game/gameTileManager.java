package src.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class gameTileManager {

    private BufferedImage TileImage;
    private BufferedImage[] TileLoader = new BufferedImage[1024];

    public gameTileManager() {
        int count = 0;
        try {
            TileImage = ImageIO.read(new File("src/tilemap.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        for(byte y = 0 ; y < 32 ; y++ ){
            for(byte x = 0 ; x < 32 ; x++ ){
                TileLoader[count] = TileImage.getSubimage(x*16,y*16,16,16);
                count++;
            }
        }
    }

    public Image getTile(int id,double scale){
        return TileLoader[id].getScaledInstance((int)(scale*16),(int)(scale*16),0);
    }


}
