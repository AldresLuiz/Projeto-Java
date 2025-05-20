package dev.aldres.gameLogic.World;

import javax.imageio.ImageIO;

import dev.aldres.gameCore.gameCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class gameTileManager {

    private BufferedImage TileImage;
    private BufferedImage[] TileLoader;

    public gameTileManager(gameCanvas gc) {
        double scale = gc.scale;
        int tileSize = gc.originalTileSize;
        int count = 0;

        try {
            TileImage = ImageIO.read(new File("src/tilemap.png"));

            TileLoader = new BufferedImage[(TileImage.getWidth()/tileSize)*(TileImage.getHeight()/tileSize)];

            for(byte y = 0 ; y < TileImage.getWidth()/tileSize ; y++ ){
                for(byte x = 0 ; x < TileImage.getHeight()/tileSize ; x++ ){
                    BufferedImage tile;
                    BufferedImage tileScaled = new BufferedImage((int)(scale*tileSize),(int)(scale*tileSize),1);
                    Graphics2D g = tileScaled.createGraphics();
                    tile = TileImage.getSubimage(x*tileSize,y*tileSize,tileSize,tileSize);
                    g.drawImage(tile,0,0,(int)(scale*tileSize),(int)(scale*tileSize),null);
                    TileLoader[count] = tileScaled;
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public BufferedImage getTile(int id){
        return TileLoader[id];
    }


}
