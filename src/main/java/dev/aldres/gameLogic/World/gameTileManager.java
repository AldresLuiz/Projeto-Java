package dev.aldres.gameLogic.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class gameTileManager {

    private BufferedImage TileImage;
    private BufferedImage[] TileLoader = new BufferedImage[1024];

    public gameTileManager(double scale) {
        int count = 0;
        try {
            TileImage = ImageIO.read(new File("src/tilemap.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        for(byte y = 0 ; y < 32 ; y++ ){
            for(byte x = 0 ; x < 32 ; x++ ){
                BufferedImage tile;
                BufferedImage tileScaled = new BufferedImage((int)(scale*16),(int)(scale*16),1);
                Graphics2D g = tileScaled.createGraphics();
                tile = TileImage.getSubimage(x*16,y*16,16,16);
                g.drawImage(tile,0,0,(int)(scale*16),(int)(scale*16),null);
                TileLoader[count] = tileScaled;
                count++;
            }
        }
    }

    public BufferedImage getTile(int id){
        return TileLoader[id];
    }


}
