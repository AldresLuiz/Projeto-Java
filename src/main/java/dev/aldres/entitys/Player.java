package dev.aldres.entitys;

import dev.aldres.annotations.RenderPlayerGraphics;
import dev.aldres.annotations.UpdateGameTicks;
import dev.aldres.annotations.PlayerSetter;
import dev.aldres.gameCore.gameCanvas;
import dev.aldres.gameTypes.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@PlayerSetter
public class Player extends Entity implements KeyListener{

    private char[] teclas = {'w', 'a', 's', 'd'};
    private boolean[] pressedkeys = {false,false,false,false};
    private String teclasstring = new String(teclas);
    private int ScreenX;
    private int ScreenY;
    public Point Position = new Point(PosX,PosY);
    private int tick;
    private int movetickrate = 4;

    public Player(gameCanvas gp){
        Width = (int) (gp.scale * gp.originalTileSize);
        Height = (int) (gp.scale * gp.originalTileSize);
        ScreenX = (int) (gp.screenWidth)/2;
        ScreenY = (int) (gp.screenHeight)/2;
        gp.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(teclasstring.indexOf(e.getKeyChar()) != -1){
            pressedkeys[teclasstring.indexOf(e.getKeyChar())] = true;
        }
    }

    @RenderPlayerGraphics
    public void Render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(ScreenX,ScreenY,Width,Height);
    }

    @UpdateGameTicks
    public void Movement(){
        tick++;
        if(tick<movetickrate){
            return;
        }
        if(pressedkeys[0]){
            PosY--;
        }
        if(pressedkeys[1]){
            PosX--;
        }
        if(pressedkeys[2]){
            PosY++;
        }
        if(pressedkeys[3]){
            PosX++;
        }

        

        Position.x = PosX;
        Position.y = PosY;
        tick -= movetickrate;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(teclasstring.indexOf(e.getKeyChar()) != -1){
            pressedkeys[teclasstring.indexOf(e.getKeyChar())] = false;
        }
    }
}
