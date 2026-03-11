package main.engine.window;

import javax.swing.JFrame;

import main.engine.core.GameSettings;
import main.engine.core.Scene;

public class GameWindow {
    
    private JFrame frame;

    public GameWindow(GameSettings gameSettings){
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(gameSettings.getTitle());
        frame.setResizable(gameSettings.isResizable());

    }

    public void show(Scene scene){
        frame.add(scene);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
        frame.dispose();
    }
}
