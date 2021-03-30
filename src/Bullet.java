package src;

import src.settings.Projectile;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Bullet extends src.Tank implements Projectile
{
    Image img_Bullet;
    int a, b, sizeA, sizeB, speedA, speedB;

    Bullet(Image img, int a, int b, int SpeedA, int SpeedB)
    {
        this.img_Bullet = img;
        this.a = a;
        this.b = b;
        this.speedA = SpeedA;
        this.speedB = SpeedB;
        sizeA = img.getWidth(null);
        sizeB = img.getHeight(null);
    }

    public void show(Graphics graphic, ImageObserver observer)
    {
        graphic.drawImage(img_Bullet, a, b, observer);
    }

    @Override
    public boolean flow()
    {
        b += speedB;
        a += speedA;
        if ((a > borderX + sizeA || a < -1 * sizeA) || (b > borderY + sizeB || b < -1 * sizeB))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean collide(int x, int y, int w, int h)
    {
        if ((y + h > this.b) && (y < this.b + sizeB))
        {
            if ((x + w > this.a) && (x < this.a + sizeA))
            {
                this.a = 2 * borderX;
                this.b = 2 * borderY;
                return true;
            }
        }
        return false;
    }
}
