package src;
import src.window.engine;
import src.window.windowconfig;

public class main {

    windowconfig window = new windowconfig();

    public static void main(String args[]){
        new main();
    }

    public main (){
        startWindow();
    }

    public void startWindow(){
        window.setDimension(800,600);
        window.show(true);
        window.Title = "Testando Classe";
        window.create();
        startgame();
    }

    public void startgame(){
        engine test = new engine(window);
        test.startGame();
    }
}
