package src.window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class window extends JFrame {

    public String Title = "Home";
    private Boolean isCreated = false;
    public gamePanel gp = new gamePanel();
    public Integer Height = gp.screenHeight;
    public Integer Width = gp.screenWidth;

    public window(){
        System.out.println("Window Config Class BOOTED.");
    }

    public void create(){
        isCreated = true;

        Dimension size = new Dimension(Width,Height);

        this.pack();
        this.setSize(size);
        this.setTitle(Title);
        this.setLayout(new BorderLayout());
        this.add(gp);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void shows(Boolean estado){
        this.setVisible(estado);
        this.setFocusable(true);
    }

    public void setDimension(int Altura, int Largura){
        Height = Altura;
        Width = Largura;
    }

    public void updateTitle(String t){
        this.setTitle(t);
    }

}
