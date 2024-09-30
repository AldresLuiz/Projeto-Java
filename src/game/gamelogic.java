package src.game;

import src.Entidade;
import src.entidades.Player;
import src.window.gamePanel;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class gamelogic {

    private Entidade[] entidades = new Entidade[1];
    private Player p;
    private gamePanel gp;

    public gamelogic(gamePanel gpv){
        gp = gpv;
        loadentidades();
    }

    private void loadentidades(){
        entidades[0] = new Player();
        p = (Player) entidades[0];
    }

    public void update(){
        BufferStrategy bs = gp.bufferStrategy();
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gp.screenWidth, gp.screenHeight);
        p.update(bs);
        bs.show();
    }
}
