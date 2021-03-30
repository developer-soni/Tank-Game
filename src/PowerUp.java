package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class PowerUp extends src.Tank {
    Image image;
    int x, y, damage, width, height;
    boolean health;

    PowerUp(Image img, int x, int y, int dmg, boolean hlth)
    {
        this.x = x;
        this.y = y;
        this.image = img;
        this.damage = dmg;
        this.health = hlth;
        width = img.getWidth(null);
        height = img.getWidth(null);
    }

    public boolean update()
    {
        if(tank1.collide(x, y, width, height))
        {
            if(health) tank1.health_cnt = tank1.health;
            tank1.bulletType = damage;
            return true;
        }
        else if(tank2.collide(x, y, width, height))
        {
            if(health) tank2.health_cnt = tank2.health;
            tank2.bulletType = damage;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void draw(Graphics graphic, ImageObserver observer)
    {
        graphic.drawImage(image, x, y, observer);
    }
}
