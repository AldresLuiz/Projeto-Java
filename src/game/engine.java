package src.game;

import src.window.window;

import java.awt.*;

public class engine extends window{

    private Thread thread;
    private boolean isRunning = false;
    private double UPDATE_CAP = 1000.0/120.0;
    private Integer frames = 0;
    private double FrameTime = 0;
    public int FPS;
    private gamelogic gmlogic;



    public engine(){
        System.out.println("Engine Class BOOTED. ");
    }

    public void start(){
        thread = new Thread(this::run);
        thread.start();
    }

    public void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void startWindow(){
        updateTitle("game");
        create();
        this.shows(true);
        gmlogic = new gamelogic(gp);
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

                // Atualizações
                gmlogic.update();
                this.FpsCounter();
            }
        }
        this.dispose();
    }

    public void FpsCounter(){
        if(FrameTime >= 1000.0){
            updateTitle("FPS: "+frames);
            FPS = frames;
            frames = 0;
            FrameTime -= 1000.0;
            // Fps Counter
        }
    }

    public void dispose(){
        System.out.println("Program Terminated :)");
    }
}
