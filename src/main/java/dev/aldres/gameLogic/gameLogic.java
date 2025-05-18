package dev.aldres.gameLogic;

import dev.aldres.gameCore.gameCanvas;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class gameLogic {

    private BufferStrategy bs;
    private RenderAndUpdate rGraphics;
    private gameCanvas gp;
    private Graphics g;


    public gameLogic(gameCanvas gc){
        gp = gc;
        bs = gp.bufferStrategy();
        g = bs.getDrawGraphics();

        rGraphics = new RenderAndUpdate(gc,g);
    }
        

    public void Render(){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,(int)gp.screenWidth,(int)gp.screenHeight);

        rGraphics.Render();
        bs.show();
    }

    public void RenderTick(){
        rGraphics.UpdateTicks();
    }
}
