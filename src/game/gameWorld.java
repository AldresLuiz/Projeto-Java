package src.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class gameWorld {
    private byte[][] worldMapper = new byte[512][512];
    private gameTileManager gameTM = new gameTileManager();

    public gameWorld(){
        worldMapper[0][0] = 3;
    }

    public void update(BufferStrategy bs, double scale){
        Graphics g = bs.getDrawGraphics();
        for(int y = 0 ; y < 18 ; y++){
            for(int x = 0 ; x < 32 ; x++){
                g.drawImage(gameTM.getTile(worldMapper[x][y],1.5), (int)(x*16*scale),(int)(y*16*scale),null);
            }
        }
    }
}
