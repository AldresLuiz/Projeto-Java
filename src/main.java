package src;
import src.game.engine;

public class main {

    private engine engine = new engine();

    public static void main(String args[]){
        new main();
    }

    public main (){
        engine.start();
    }
}
