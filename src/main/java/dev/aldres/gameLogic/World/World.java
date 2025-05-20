package dev.aldres.gameLogic.World;

import javax.imageio.ImageIO;

import dev.aldres.annotations.RenderWorldGraphics;
import dev.aldres.annotations.WorldSetter;
import dev.aldres.entitys.Player;
import dev.aldres.gameCore.gameCanvas;
import dev.aldres.gameTypes.Block;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;

@WorldSetter
public class World {
    public Block[][] worldMapper;
    Player player;
    Double offsetX, offsetY;
    private LoadBlocks loadblocks;
    private Point playerPoint;
    private gameCanvas gc;
    private double scale;

    public World(gameCanvas gc, Point pos){
        playerPoint = pos;
        this.scale = gc.scale;
        this.gc = gc;
        loadblocks = new LoadBlocks("src/blocks.xml",gc);
        this.mapRender();
    }

    public void mapRender(){
        try {
            BufferedImage image = ImageIO.read(new File("src/world.png"));

            worldMapper = new Block[image.getWidth()][image.getHeight()];

            for (int y = 0; y < worldMapper[0].length; y++) {
                for (int x = 0; x < worldMapper.length; x++) {
                    int color = image.getRGB(x, y) & 0x00FFFFFF;
                    if (loadblocks.tileIdentifier.containsKey(color)) {
                        worldMapper[x][y] = loadblocks.getBlockById(loadblocks.tileIdentifier.get(color));
                        continue;
                    }
                    worldMapper[x][y] = loadblocks.getBlockById(0);
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

        offsetX = player.PosX.doubleValue() - player.PosX.intValue();
        offsetY = player.PosY.doubleValue() - player.PosY.intValue();

        for(int y = 0; y < gc.maxLine+2; y++) {
            for(int x = 0; x < gc.maxCol+2; x++) {

                int worldX = posX - (gc.maxCol/2) + x;
                int worldY = posY - (gc.maxLine/2) + y;

                if(worldX >= 0 && worldY >= 0 && worldX < worldMapper.length && worldY < worldMapper[0].length) {
                    g.drawImage(worldMapper[worldX][worldY].image,
                            (int) ((x-offsetX) * gc.originalTileSize * scale),
                            (int) ((y-offsetY) * gc.originalTileSize * scale),
                            null);
                }
            }
        }
    }
}
