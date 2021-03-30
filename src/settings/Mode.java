package src.settings;

import java.awt.event.KeyEvent;
import java.util.Observable;

public class Mode extends Observable
{
    public int tEvent;
    public Object eventNo;

    public void setValue(KeyEvent key, int keyType)
    {
        tEvent = keyType;
        eventNo = key;
        setChanged();
        notifyObservers(this);
    }

    public void setValue(String message)
    {
        tEvent = 2;
        eventNo = message;
        setChanged();
        notifyObservers(this);
    }

    public void setValue(int time)
    {
        tEvent = 3;
        eventNo = time;
        setChanged();
        notifyObservers(this);
    }

}
