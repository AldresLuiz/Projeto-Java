package src.entidades;

import src.Entidade;
import src.game.gameCanvas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Player extends Entidade implements KeyListener{

    private char[] teclas = {'w', 'a', 's', 'd'};
    private boolean[] pressedkeys = {false,false,false,false};
    private String teclasstring = new String(teclas);
    private int ScreenX;
    private int ScreenY;
    public Point Position = new Point(PosX,PosY);
    private int tick;
    private int movetickrate = 4;

    public Player(double scale, gameCanvas gp){
        Width = (int) (scale * gp.originalTileSize);
        Height = (int) (scale * gp.originalTileSize);
        ScreenX = (int) (gp.screenWidth-Width)/2;
        ScreenY = (int) (gp.screenHeight-Height)/2;
        Scale = scale;
        gp.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(teclasstring.indexOf(e.getKeyChar()) != -1){
            pressedkeys[teclasstring.indexOf(e.getKeyChar())] = true;
        }
    }

    public void update(BufferStrategy bs){
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.red);
        g.fillRect((int)(16*16*Scale),(int)(9*16*Scale),Width,Height);
    }

    public void Movement(){
        tick++;
        if(tick<movetickrate+1){
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
        if(PosX < 0){
            PosX = 0;
        }
        if(PosY < 0){
            PosY = 0;
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
