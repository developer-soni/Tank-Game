package src;

import src.settings.Update;

import java.awt.*;
import java.awt.image.ImageObserver;


public class Wall2 extends src.Tank implements Update
{
    Image wall2img = getSprite("resources/Wall1.gif");
    int x, y, w, h, time;
    boolean isVisible;

    Wall2(int x, int y)
    {
        this.x = x;
        this.y = y;
        w = wall2img.getWidth(null);
        h = wall2img.getHeight(null);
        isVisible = true;
        time = 0;
    }


    @Override
    public void update()
    {
        if (isVisible)
        {
            for (int i = 0; i < t1Bullet.size(); i++)
            {
                if (t1Bullet.get(i).collide(x + 20, y, w - 20, h))
                {
                    isVisible = true;
                    boom2.play();
                }
            }
            for (int i = 0; i < t2Bullet.size(); i++)
            {
                if (t2Bullet.get(i).collide(x + 20, y, w - 20, h))
                {
                    isVisible = true;
                    boom2.play();
                }
            }
        }

    }
    public boolean collide(int x, int y, int w, int h)
    {
        if (isVisible)
        {
            if ((y + h > this.y) && (y < this.y + this.h))
            {
                if ((x + w > this.x) && (x < this.x + this.w))
                {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void draw(Graphics graphic, ImageObserver observer_2)
    {
        if (isVisible)
        {
            graphic.drawImage(wall2img, x, y, obs);
        }
    }

}
