package dev.aldres.gameCore;

import javax.swing.*;

public class Window extends JFrame {

    public String Title = "Home";
    public gameCanvas gp = new gameCanvas(this);
    public Integer Height = (int)gp.screenHeight;
    public Integer Width = (int)gp.screenWidth;

    public Window(){
    }

    public void create(){
        this.setTitle(Title);
        this.setResizable(false);
        this.add(gp);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gp.setDimension();
        this.setLocationRelativeTo(null);
    }

    public void shows(){
        this.setVisible(!this.isVisible());
        this.setFocusable(true);
    }

    public void updateTitle(String t){
        this.setTitle(t);
    }

}
