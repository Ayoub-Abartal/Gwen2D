package main.engine.core;


import main.engine.window.GameWindow;

public abstract class GameApplication {
    
    private GameSettings settings;
    private GameWindow window;
    private GameLoop loop;
    private SceneManager sceneManager; 

    public GameApplication(GameSettings gameSettings){
        this.settings = gameSettings;
    }

    protected abstract Scene createInitialScene();

    protected void onStart(){};
    protected void onStop(){};
    protected void onPause(){};
    protected void onResume(){};

    public final void start(){
        Scene scene = createInitialScene();

        // setup components 
        sceneManager = new SceneManager(scene);
        window = new GameWindow(settings);
        loop = new GameLoop(settings, sceneManager);

        // show window
        window.show(scene);

        onStart();

        loop.startGame();
    }

    public final void stop(){
        onStop();
        loop.stopGame();
        window.hide();
    }

    public final void pause(){
        onPause();
        loop.pauseGame();
    }

    public final void resume(){
        onResume();
        loop.resumeGame();
    }

    protected GameSettings getSettings(){
        return settings;
    }


}




