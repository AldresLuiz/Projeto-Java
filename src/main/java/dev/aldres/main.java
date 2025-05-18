package dev.aldres;
import dev.aldres.gameCore.Engine;

public class main {

    private Engine engine = new Engine();

    public static void main(String[] args){
        new main();
    }

    public main (){
        engine.start();
    }
}
