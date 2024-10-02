package src.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class gameCanvas extends Canvas{

    public final int originalTileSize = 16;
    public final double scale = 1.5;
    final int maxCol = 32;
    final int maxLine = 18;

    final double TileSize =  originalTileSize * scale;
    public final double screenWidth = (maxCol * TileSize);
    public final double screenHeight = (maxLine * TileSize);
    private gameWindow gameW;


    public gameCanvas(gameWindow gw){
        gameW = gw;
    }

    public void setDimension(){
        gameW.setSize(new Dimension((int)screenWidth + gameW.getInsets().left + gameW.getInsets().right,(int)screenHeight + gameW.getInsets().top + gameW.getInsets().bottom));
    }


    public BufferStrategy bufferStrategy(){
        if(getBufferStrategy() == null){
            createBufferStrategy(3);
            return getBufferStrategy();
        }
        return getBufferStrategy();
    }

    public Graphics graphics(){
        return this.getGraphics();
    }
}
