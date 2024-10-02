package src;
import src.game.gameEngine;

public class main {

    private gameEngine engine = new gameEngine();

    public static void main(String args[]){
        new main();
    }

    public main (){
        engine.start();
    }
}
