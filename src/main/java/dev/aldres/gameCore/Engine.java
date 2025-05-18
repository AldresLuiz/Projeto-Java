package dev.aldres.gameCore;

import dev.aldres.gameLogic.gameLogic;

public class Engine extends Window {

    private double FpsTarget = 60;
    private double FpsTime = 1000.0/FpsTarget;
    public int FPS;

    private gameLogic gamelogic;
    private boolean isRunning = false;
    private Thread thread;

    public Engine(){
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

    private void startWindow(){
        updateTitle("game");
        create();
        shows();
    }

    private void run(){
        this.startWindow();
        gamelogic = new gameLogic(gp);
        isRunning = true;

        double PrimeiroTempo = 0;
        double UltimoTempo = System.currentTimeMillis();
        double TempoDecorrido = 0;
        double TempoDeRender = 0;
        double TempoDeTick = 0;
        double TempoDeContador = 0;
        int FpsAtual = 0;

        while(isRunning){
            PrimeiroTempo = System.currentTimeMillis();
            TempoDecorrido = PrimeiroTempo - UltimoTempo;
            UltimoTempo = PrimeiroTempo;

            TempoDeContador += TempoDecorrido;
            TempoDeRender += TempoDecorrido;
            TempoDeTick += TempoDecorrido;

            if(TempoDeTick>=1000.0/128.0){
                gamelogic.RenderTick();
                TempoDeTick-=1000.0/128.0;
            }

            if(TempoDeRender >= FpsTime){
                TempoDeRender -= FpsTime;
                FpsAtual++;

                // Atualizações
                repaint();
                gamelogic.Render();
            }

            if(TempoDeContador >= 1000.0){
                FPS = FpsAtual;
                updateTitle("FPS: " + FPS);
                TempoDeContador -= 1000.0;
                FpsAtual = 0;
            }
        }
        dispose();
    }

    public void dispose(){
        System.out.println("Program Terminated :)");
    }
}
