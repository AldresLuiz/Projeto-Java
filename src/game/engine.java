package src.game;

import src.window.window;

import java.awt.*;

public class engine {

    private Thread thread;
    private boolean isRunning = false;
    private double UPDATE_CAP = 1000.0/60.0;
    private window window = new window();
    public int FPS;
    private Integer frames = 0;
    private double FrameTime = 0;

    public engine(){

    }

    public void start(){
        thread = new Thread(this::run);
        thread.start();
    }

    public void stop(){

    }

    public void startWindow(){
        window.updateTitle("game");
        window.setDimension(500, 500);
        window.create();
        window.show();
    }

    public void run(){
        this.startWindow();
        isRunning = true;

        double PrimeiroTempo = 0;
        double UltimoTempo = System.currentTimeMillis();
        double TempoDecorrido = 0;
        double TempoSobra = 0;

        while(isRunning){
            PrimeiroTempo = System.currentTimeMillis();
            TempoDecorrido = PrimeiroTempo - UltimoTempo;
            FrameTime += TempoDecorrido;
            UltimoTempo = PrimeiroTempo;
            TempoSobra += TempoDecorrido;


            while(TempoSobra >= UPDATE_CAP){
                TempoSobra -= UPDATE_CAP;
                frames++;

                // Renderização/Atualização na tela
                window.update();
                this.FpsCounter();
            }
        }
        this.dispose();
    }

    public void FpsCounter(){
        if(FrameTime >= 1000.0){
            window.updateTitle("FPS: "+frames);
            FPS = frames;
            frames = 0;
            FrameTime -= 1000.0;
            // Fps Counter
        }
    }

    public void dispose(){

    }
}
