package src.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class gameWorld {
    private Integer[][] worldMapper = new Integer[512][512];
    private gameTileManager gameTM;
    private HashMap<Integer, Integer> TileIndetifier = new HashMap<>();

    public gameWorld(double scale){
        this.gameTM = new gameTileManager(scale);
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

            if (image.getWidth() != 512 || image.getHeight() != 512) {
                throw new IllegalArgumentException("A imagem deve ter exatamente 512x512 pixels.");
            }

            for (int y = 0; y < 512; y++) {
                for (int x = 0; x < 512; x++) {
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
        System.out.println(colornotfound.values());
    }

    public void update(BufferStrategy bs, double scale, Point playerpoint){
        BufferedImage bi = new BufferedImage((int)(32*16*scale),(int)(18*16*scale),1);

        int posY = playerpoint.y;
        int posX = playerpoint.x;

        for(int y = 0; y < 18; y++) {
            for(int x = 0; x < 32; x++) {

                int worldX = posX - 16 + x;
                int worldY = posY - 9 + y;

                if(worldX >= 0 && worldY >= 0 && worldX < worldMapper.length && worldY < worldMapper[0].length) {
                    bi.getGraphics().drawImage(gameTM.getTile(worldMapper[worldX][worldY], scale),
                            (int) (x * 16 * scale),
                            (int) (y * 16 * scale),
                            null);
                }
            }
        }
        bs.getDrawGraphics().drawImage(bi, 0, 0, null);
    }
}
