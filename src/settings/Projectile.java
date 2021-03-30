package src.settings;


import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface Projectile
{
    public boolean collide(int x, int y, int w, int h);
    boolean flow();
    public void show(Graphics graphic, ImageObserver observer);
}