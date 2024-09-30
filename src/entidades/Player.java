package src.entidades;

import src.Entidade;
import src.window.gamePanel;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Player extends Entidade {

    public Player(){
        Width = 50;
        Height = 50;
    }

    public void update(BufferStrategy bs){
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(PosX,PosY,Width,Height);
        PosX++;
    }


}
