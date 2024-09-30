package src.window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class gamePanel extends Canvas{

    final int originalTileSize = 16;
    private int scale = 2;
    final int maxCol = 32;
    final int maxLine = 18;

    final int TileSize = originalTileSize * scale;
    public final int screenWidth = maxCol * TileSize;
    public final int screenHeight = maxLine * TileSize;


    public gamePanel(){
        this.setBackground(Color.BLACK);
        setSize(new Dimension(screenWidth,screenHeight));

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
