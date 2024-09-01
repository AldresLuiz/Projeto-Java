package src.window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class window extends JFrame {

    public String Title = "Home";
    private Integer Height = 500;
    private Integer Width = 500;
    private Boolean isCreated = false;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bs;

    public window(){
        System.out.println("Window Config Class BOOTED.");
    }

    public void create(){
        isCreated = true;

        image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);

        canvas = new Canvas();
        Dimension size = new Dimension(Width, Height);
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);

        pack();
        setSize(size);
        setTitle(Title);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
    }

    public void show(Boolean estado){
        setVisible(estado);
        setFocusable(true);
    }

    public void setDimension(int Largura, int Altura){
        Height = Altura;
        Width = Largura;
    }

    public void updateTitle(String t){
        setTitle(t);
    }

    public void update(){

    }

    public Graphics getGraphics() {
        return bs.getDrawGraphics();
    }
}
