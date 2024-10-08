package src.game;

public class gameEngine extends gameWindow {

    public double FpsTarget = 60;
    private double FpsTime = 1000.0/FpsTarget;
    private double FpsTimeLimiter = 0;
    private int FpsCounter = 0;
    public int FPS;

    private gameLogic gamelogic;
    private boolean isRunning = false;
    private Thread thread;

    public gameEngine(){
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
    }

    public void run(){
        this.startWindow();
        gamelogic = new gameLogic(gp);
        isRunning = true;

        double PrimeiroTempo = 0;
        double UltimoTempo = System.currentTimeMillis();
        double TempoDecorrido = 0;
        double TempoSobra = 0;
        double TempoTick = 0;

        while(isRunning){
            PrimeiroTempo = System.currentTimeMillis();
            TempoDecorrido = PrimeiroTempo - UltimoTempo;
            UltimoTempo = PrimeiroTempo;
            FpsTimeLimiter += TempoDecorrido;
            TempoSobra += TempoDecorrido;
            TempoTick += TempoDecorrido;

            if(TempoTick>=1000.0/128.0){
                gamelogic.tick();
                TempoTick-=1000.0/128.0;
            }

            if(TempoSobra >= FpsTime){
                TempoSobra -= FpsTime;
                FpsCounter++;

                // Atualizações
                repaint();
                gamelogic.update();
                this.FpsCounter();
            }
        }
        this.dispose();
    }

    public void FpsCounter(){
        if(FpsTimeLimiter >= 1000.0){
            updateTitle("FPS: "+FpsCounter+" RES: "+gp.screenWidth+"x"+gp.screenHeight);
            FPS = FpsCounter;
            FpsTimeLimiter -= 1000.0;
            FpsCounter = 0;
            // Fps Counter
        }
    }

    public void dispose(){
        System.out.println("Program Terminated :)");
    }
}
