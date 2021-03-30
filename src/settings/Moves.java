package src.settings;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public interface Moves extends Observer
{
    public void flow();
    public void show(Graphics graphic, ImageObserver observer);
    public void update(Observable object, Object argument);
}
