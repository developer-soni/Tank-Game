package src.settings;


import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface Update
{
    void update();
    void draw(Graphics graphic, ImageObserver observer);
}
