package dev.aldres.entitys;

import dev.aldres.annotations.RenderPlayerGraphics;
import dev.aldres.annotations.UpdateGameTicks;
import dev.aldres.annotations.PlayerSetter;
import dev.aldres.gameCore.gameCanvas;
import dev.aldres.gameLogic.World.World;
import dev.aldres.gameTypes.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

@PlayerSetter
public class Player extends Entity implements KeyListener{

    private boolean[] pressedkeys;
    private char[] teclas = {'w', 'a', 's', 'd', ' '};
    private String teclasstring = new String(teclas);
    public Point Position = new Point(PosX.intValue(),PosY.intValue());
    private gameCanvas gp;
    private int movetickrate = 0;
    private Double moveSpeed = 0.1;
    private int ScreenX;
    private int ScreenY;
    public World world;
    private int tick;

    private BigDecimal velocityY = BigDecimal.ZERO, gravity = BigDecimal.valueOf(0.005), terminalvelocityY = BigDecimal.valueOf(1);
    private Boolean onGround = false;



    public Player(gameCanvas gp){
        pressedkeys = new boolean[teclas.length];
        for(int i = 0; i < teclas.length; i++){
            pressedkeys[i] = false;
        }
        this.gp = gp;
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
        BigDecimal XRight = PosX.add(BigDecimal.valueOf(0.99));
        BigDecimal YDown = PosY.add(BigDecimal.valueOf(0.99));

        if(
            pressedkeys[1] &&
            PosX.compareTo(BigDecimal.ZERO) == 1 && 
            !world.worldMapper[PosX.subtract(BigDecimal.valueOf(moveSpeed)).intValue()][PosY.intValue()].isSolid && 
            !world.worldMapper[PosX.subtract(BigDecimal.valueOf(moveSpeed)).intValue()][YDown.intValue()].isSolid
        ){
                
            PosX = PosX.subtract(BigDecimal.valueOf(moveSpeed));
            XRight = PosX.add(BigDecimal.valueOf(0.99));
        }

        if(
            YDown.add(velocityY).compareTo(BigDecimal.valueOf(world.worldMapper[0].length)) == -1 && 
            !world.worldMapper[PosX.intValue()][YDown.add(velocityY).intValue()].isSolid &&
            !world.worldMapper[XRight.intValue()][YDown.add(velocityY).intValue()].isSolid
        ){
            onGround = false;
            PosY = PosY.add(velocityY);
            YDown = PosY.add(BigDecimal.valueOf(0.99));

            if (PosY.subtract(velocityY).compareTo(BigDecimal.ZERO) == 1 &&
                (world.worldMapper[PosX.intValue()][PosY.subtract(gravity).subtract(BigDecimal.valueOf(moveSpeed)).intValue()].isSolid ||
                world.worldMapper[XRight.intValue()][PosY.subtract(gravity).subtract(BigDecimal.valueOf(moveSpeed)).intValue()].isSolid)
                ){
                    velocityY = BigDecimal.ZERO;
            }

            velocityY = velocityY.add(gravity);
            if(velocityY.compareTo(terminalvelocityY) == 1){
                velocityY = terminalvelocityY;
            }
        } else {
            onGround = true;
            velocityY = BigDecimal.ZERO;
        }

        if(
            pressedkeys[3] && 
            PosX.compareTo(BigDecimal.valueOf(world.worldMapper.length)) == -1 && 
            !world.worldMapper[XRight.add(BigDecimal.valueOf(moveSpeed)).intValue()][PosY.intValue()].isSolid &&
            !world.worldMapper[XRight.add(BigDecimal.valueOf(moveSpeed)).intValue()][YDown.intValue()].isSolid
        ){
            PosX = PosX.add(BigDecimal.valueOf(moveSpeed));
            XRight = PosX.add(BigDecimal.valueOf(0.99));
        }

        if (
            (pressedkeys[4] || pressedkeys[0]) &&
             PosY.subtract(velocityY).compareTo(BigDecimal.ZERO) == 1 &&
              !world.worldMapper[PosX.intValue()][PosY.subtract(velocityY).intValue()].isSolid &&
              !world.worldMapper[XRight.intValue()][PosY.subtract(velocityY).intValue()].isSolid
        ){
            if (onGround) {
                velocityY = BigDecimal.valueOf(-0.2);
                PosY = PosY.subtract(velocityY);
                YDown = PosY.add(BigDecimal.valueOf(0.99));
            }
        }

        System.out.println("PosX: "+PosX+" , PosY: "+PosY);

        Position.x = PosX.intValue();
        Position.y =  PosY.intValue();
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
