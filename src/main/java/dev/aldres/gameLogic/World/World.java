package dev.aldres.gameLogic.World;

import javax.imageio.ImageIO;

import dev.aldres.annotations.RenderWorldGraphics;
import dev.aldres.annotations.WorldSetter;
import dev.aldres.gameCore.gameCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

@WorldSetter
public class World {
    private HashMap<Integer, Integer> TileIndetifier = new HashMap<>();
    private gameCanvas gc;
    private Integer[][] worldMapper;
    private gameTileManager gameTM;
    private Point playerPoint;
    private double scale;

    public World(gameCanvas gc, Point pos){
        this.gameTM = new gameTileManager(gc.scale);
        playerPoint = pos;
        this.scale = gc.scale;
        this.gc = gc;
        this.colorSet();
        this.mapRender();
    }

    public void colorSet(){
        TileIndetifier.put(0xffffff,0);
        TileIndetifier.put(0x7f7f7f,1);
        TileIndetifier.put(0x000000,2);
        TileIndetifier.put(0xc3c3c3,3);
        TileIndetifier.put(0xff7f27,4);
        TileIndetifier.put(0x00ff00,5);
        TileIndetifier.put(0x22b14c,6);
        TileIndetifier.put(0xb5e61d,7);
    }

    public void mapRender(){
        HashMap<Integer,Integer> colornotfound = new HashMap<>();
        try {
            BufferedImage image = ImageIO.read(new File("src/world.png"));

            worldMapper = new Integer[image.getWidth()][image.getHeight()];

            for (int y = 0; y < worldMapper[0].length; y++) {
                for (int x = 0; x < worldMapper.length; x++) {
                    int color = image.getRGB(x, y) & 0x00FFFFFF;
                    worldMapper[x][y] = 0;
                    colornotfound.put(color,-color);
                    if (TileIndetifier.containsKey(color)) {
                        worldMapper[x][y] = TileIndetifier.get(color);
                        colornotfound.put(color,color);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RenderWorldGraphics
    public void Render(Graphics g){
        int posY = playerPoint.y;
        int posX = playerPoint.x;

        for(int y = 0; y < gc.maxLine+2; y++) {
            for(int x = 0; x < gc.maxCol+2; x++) {

                int worldX = posX - (gc.maxCol/2) + x;
                int worldY = posY - (gc.maxLine/2) + y;

                if(worldX >= 0 && worldY >= 0 && worldX < worldMapper.length && worldY < worldMapper[0].length) {
                    g.drawImage(gameTM.getTile(worldMapper[worldX][worldY]),
                            (int) (x * 16 * scale),
                            (int) (y * 16 * scale),
                            null);
                }
            }
        }
    }
}
