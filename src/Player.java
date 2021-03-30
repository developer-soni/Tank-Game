package src;

import src.settings.Mode;
import src.settings.Moves;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class Player extends Tank implements Moves
{
    private BufferedImage tank_Img, setImg;
    private ArrayList<src.Bullet> Bullet, currBullets;
    private Image[] bullet_Img;
    int centerA, centerB, bulletType = 0, bulletA_Speed = 0, bulletB_Speed = 0; //for tank A and B position and speed
    int tankW, tankH, a, b; //for tank width and height
    final int health = 4;
    int health_cnt, pts = 0; //health and point counters
    int speed = 3, angle = 0, moveRate = 0, player_Loc, lives = 2, resetW = 4; //angle, movement rate and player location
    int speedA = 0, speedB = 0;
    private String control;

    Player(String tank_Img, ArrayList bullet, ArrayList currBullet, Image bulletImg[], int playerLoc)
    {
        this.bullet_Img = bulletImg;
        try
        {
            this.tank_Img = getBufferedImage(tank_Img);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        this.Bullet = bullet;
        this.currBullets = currBullet;
        tankW = this.tank_Img.getWidth() / 60;
        tankH = this.tank_Img.getHeight();
        a = playerLoc * borderX / 3;
        b = borderY / 2;
        health_cnt = health;
        this.player_Loc = playerLoc;
        this.control = playerLoc + "";
        centerA = a + tankW / 4;
        centerB = b + tankH / 4;
    }

    public void show(Graphics graphic, ImageObserver observer)
    {
        if (health_cnt > 0)
        {
            setImg = tank_Img.getSubimage(tankW * (angle / 6), 0, tankW, tankH);
            graphic.drawImage(setImg, a, b, observer);
            graphic.drawImage(tankHealth[4 - health_cnt], a + 5, b - 10, observer);
        }
        else if (health_cnt <= 0 && lives > 0)
        {
            graphic.drawImage(tankHealth[resetW++], a, b, observer);
            if (resetW == 12)
            {
                resetW = 5;
                health_cnt = health;
                a = player_Loc * borderX / 3;
                b = player_Loc * borderY / 3;
                lives--;
                tPlayer[player_Loc].pts += 5;
                tPlayer[player_Loc].a = (tPlayer[player_Loc].player_Loc) * borderX / 3;
                tPlayer[player_Loc].b = (tPlayer[player_Loc].player_Loc) * borderY / 3;
                boom1.play();
            }
        }
        else
        {
            gameCheck = true;
        }
    }

    public void flow()
    {
        angle += moveRate;
        if (angle == -6)
        {
            angle = 354;
        }
        else if (angle == 360)
        {
            angle = 0;
        }
        if ((a + speedA < borderX - 70) && (a + speedA > 0)
                && (!(tPlayer[player_Loc].collide(a + speedA, b, tankW, tankH)))
                && (!(randomObj.collide(a + speedA, b, tankW, tankH))))
        {
            a += speedA;
        }
        if ((b + speedB < borderY - 88) && (b + speedB > 0)
                && (!(tPlayer[player_Loc].collide(a, b + speedB, tankW, tankH)))
                && (!(randomObj.collide(a, b + speedB, tankW, tankH))))
        {
            b += speedB;
        }

        for (int i = 0; i < Bullet.size(); i++)
        {
            if (Bullet.get(i).collide(a + 20, b, tankW - 20, tankH))
            {
                if (health_cnt >= 1)
                {
                    health_cnt -= (tPlayer[player_Loc].bulletType + 1);
                    boom2.play();
                }
            }
        }
    }

    public boolean collide(int a, int b, int w, int h)
    {
        a += 5;
        b += 5;
        w -= 10;
        h -= 15;
        if ((b + h > this.b) && (b < this.b + tankH))
        {
            if ((a + w > this.a) && (a < this.a + tankW))
            {
                return true;
            }
        }
        return false;
    }

    public void update(Observable observer, Object eventType)
    {
        Mode gameEvent = (Mode) eventType;
        if (gameEvent.tEvent <= 1)
        {
            KeyEvent key = (KeyEvent) gameEvent.eventNo;
            String tank_motion = tank_move.get(key.getKeyCode());
            if (tank_motion.equals("left" + control))
            {
                moveRate = 6 * gameEvent.tEvent;
            }
            else if (tank_motion.equals("right" + control))
            {
                moveRate = -6 * gameEvent.tEvent;
            }
            else if (tank_motion.equals("up" + control))
            {
                speedB = (int) (-1 * speed * Math.sin(Math.toRadians(angle))) * gameEvent.tEvent;
                speedA = (int) (speed * Math.cos(Math.toRadians(angle))) * gameEvent.tEvent;
            }
            else if (tank_motion.equals("down" + control))
            {
                speedB = (int) (speed * Math.sin(Math.toRadians(angle))) * gameEvent.tEvent;
                speedA = (int) (-1 * speed * Math.cos(Math.toRadians(angle))) * gameEvent.tEvent;
            }
            else if (tank_motion.equals("shoot" + control))
            {
                if (gameEvent.tEvent == 0)
                {
                    bulletA_Speed = (int) (15 * Math.cos(Math.toRadians(angle)));
                    bulletB_Speed = (int) (-15 * Math.sin(Math.toRadians(angle)));
                    centerA = a + tankW / 4 + bulletA_Speed * 2;
                    centerB = b + tankH / 4 + bulletB_Speed * 2;
                    currBullets.add(new Bullet(bullet_Img[bulletType], centerA, centerB, bulletA_Speed, bulletB_Speed));
                }
            }
        }
    }
}
