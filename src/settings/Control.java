package src.settings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter
{
    Mode game;

    public Control(Mode game_Event)
    {
        this.game = game_Event;
    }

    public void keyPressed(KeyEvent key)
    {
        game.setValue(key, 1);
    }

    public void keyReleased(KeyEvent key)
    {
        game.setValue(key, 0);
    }
}

