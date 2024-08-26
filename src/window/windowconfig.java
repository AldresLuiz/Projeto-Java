package src.window;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class windowconfig extends JFrame {

    public String Title = "Home";
    private Integer Height = 500;
    private Integer Width = 500;
    private Boolean isCreated = false;

    public windowconfig(){
        System.out.println("Window Config Class BOOTED.");
    }

    public void create(){
        isCreated = true;
        setSize(Width, Height);
        setTitle(Title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void show(Boolean estado){
        setVisible(estado);
    }

    public void setDimension(int Largura, int Altura){
        Height = Altura;
        Width = Largura;
    }

    @Override
    public Graphics getGraphics() {
        return super.getGraphics();
    }
}
