package src.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class gameTileManager {

    private BufferedImage TileImage;
    private Image[] TileLoader = new Image[1024];

    public gameTileManager(double scale) {
        int count = 0;
        try {
            TileImage = ImageIO.read(new File("src/tilemap.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        for(byte y = 0 ; y < 32 ; y++ ){
            for(byte x = 0 ; x < 32 ; x++ ){
                TileLoader[count] = (Image) TileImage.getSubimage(x*16,y*16,16,16).getScaledInstance((int)(16*scale),(int)(16*scale),0);
                count++;
            }
        }
    }

    public Image getTile(int id,double scale){
        return TileLoader[id];
    }


}
