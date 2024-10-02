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

    public Player(double scale, gameCanvas gp){
        Width = (int) (scale * gp.originalTileSize);
        Height = (int) (scale * gp.originalTileSize);
        PosX = (int) (gp.screenWidth-Width)/2;
        PosY = (int) (gp.screenHeight-Height)/2;
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
        g.setColor(Color.WHITE);
        g.fillRect(PosX,PosY,Width,Height);
    }

    public void Movement(){
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
