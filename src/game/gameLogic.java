package src.game;

import src.Entidade;
import src.entidades.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class gameLogic {

    private Entidade[] entidades = new Entidade[1];
    private Player p;
    private gameCanvas gp;
    private BufferStrategy bs;
    private gameWorld gameW;

    public gameLogic(gameCanvas gpv){
        gp = gpv;
        gameW = new gameWorld(gp.scale);
        bs = gp.bufferStrategy();
        loadentidades();
    }

    private void loadentidades(){
        entidades[0] = new Player(gp.scale,gp);
        p = (Player) entidades[0];
    }

    public void update(){
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,(int)gp.screenWidth,(int)gp.screenHeight);
        gameW.update(bs,gp.scale,p.Position);
        p.update(bs);
        bs.show();
    }

    public void tick(){
        p.Movement();
    }
}
