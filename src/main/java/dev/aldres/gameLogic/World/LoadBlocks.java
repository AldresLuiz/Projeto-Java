package dev.aldres.gameLogic.World;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import dev.aldres.gameCore.gameCanvas;
import dev.aldres.gameTypes.Block;

public class LoadBlocks {

    private gameTileManager tileManager;
    public HashMap<Integer, Integer> tileIdentifier = new HashMap<>();
    private Block[] blocks;
    
    public LoadBlocks(String xmlPath, gameCanvas gc){
        tileManager = new gameTileManager(gc);

        try{
            File xmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("block");
            blocks = new Block[nList.getLength()];
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Block block = new Block();
                    block.id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent());
                    block.name = elem.getElementsByTagName("name").item(0).getTextContent();
                    block.color = Integer.decode(elem.getElementsByTagName("color").item(0).getTextContent());
                    block.isSolid = Boolean.parseBoolean(elem.getElementsByTagName("isSolid").item(0).getTextContent());
                    block.image = tileManager.getTile(block.id);
                    tileIdentifier.put(block.color,block.id);
                    blocks[block.id] = block;
                }
            }
        }catch(Exception e){
            
        } 
    }

    public Block getBlockById(int id){
        return blocks[id];
    }
}
