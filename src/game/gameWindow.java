package src.game;

import javax.swing.*;
import java.awt.*;

public class gameWindow extends JFrame {

    public String Title = "Home";
    public gameCanvas gp = new gameCanvas(this);
    public Integer Height = (int)gp.screenHeight;
    public Integer Width = (int)gp.screenWidth;

    public gameWindow(){
        System.out.println("Window Config Class BOOTED.");
    }

    public void create(){
        Dimension size = new Dimension(Width,Height);

        this.setTitle(Title);
        this.setResizable(false);
        this.add(gp);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gp.setDimension();
        this.setLocationRelativeTo(null);
    }

    public void shows(Boolean estado){
        this.setVisible(estado);
        this.setFocusable(true);
    }

    public void updateTitle(String t){
        this.setTitle(t);
    }

}
