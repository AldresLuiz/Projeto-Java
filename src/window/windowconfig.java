package src.window;

import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class windowconfig {

    public String Title = "Home";
    private Integer Height = 500;
    private Integer Width = 500;

    public windowconfig(){
        JFrame wind = new JFrame();
        wind.setTitle(Title);
        wind.setSize(Width, Height);
        wind.setDefaultCloseOperation(EXIT_ON_CLOSE);
        wind.setResizable(false);
        wind.setVisible(true);
    }
}
