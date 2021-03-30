package src;

import src.settings.Mode;
import src.settings.Moves;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Observable;

public class Random extends src.Tank implements Moves
{
    Image health_pUp = getSprite("resources/powerup_Health.png");
    Image pUp1 = getSprite("resources/powerup.png");
    Image pUp2 = getSprite("resources/powerup_2.png");
    ArrayList<PowerUp> pUp = new ArrayList();
    ArrayList<Wall> wall = new ArrayList();
    ArrayList<Wall2> wall2 = new ArrayList();


    @Override
    public void flow() { }

    @Override
    public void show(Graphics graphic, ImageObserver observer)
    {
        for (int i = 0; i < pUp.size(); i++)
        {
            if (!pUp.get(i).update())
            {
                pUp.get(i).draw(graphic, observer);
            }
            else
            {
                pUp.remove(i);
            }
        }

        for (int i = 0; i < wall.size(); i++)
        {
            wall.get(i).update();
            wall.get(i).draw(graphic, observer);
        }
        for (int i = 0; i < wall2.size(); i++)
        {
            wall2.get(i).update();
            wall2.get(i).draw(graphic, observer);
        }
    }

    public boolean collide(int x, int y, int w, int h)
    {
        for (int i = 0; i < wall.size(); i++)       //for unbreakable wall collision
        {
            if (wall.get(i).collide(x, y, w, h))
            {
                return true;
            }
        }

        for (int i = 0; i < wall2.size(); i++)    //for unbreakable wall collision
        {
            if (wall2.get(i).collide(x, y, w, h))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Observable observer, Object event)
    {
        Mode gEvent = (Mode) event;    //game event and type event
        if (gEvent.tEvent == 3)
        {
            if ((int) gEvent.eventNo == 0)
            {
                pUp.add(new PowerUp(health_pUp, 100, 100, 0, true));
                pUp.add(new PowerUp(pUp1, borderX - 100, borderY - 100, 1, false));
            }
            if ((int) gEvent.eventNo == 1)
            {
                pUp.add(new PowerUp(pUp2, 700, borderY - 100, 2, false));
                pUp.add(new PowerUp(health_pUp, borderX - 700, 100, 0, true));
            }
            if ((int) gEvent.eventNo == 2)
            {
                pUp.add(new PowerUp(health_pUp, 400, borderY / 2, 0, true));
                pUp.add(new PowerUp(pUp1, borderX - 400, borderY / 2, 1, false));
            }
            if ((int) gEvent.eventNo == 3)
            {
                pUp.add(new PowerUp(pUp2, 500, borderY / 3, 2, false));
                pUp.add(new PowerUp(health_pUp, borderX - 500, borderY / 3, 0, true));
            }
        }
    }
    public Random()
    {
        for (int i = 0; i < borderY / 32; i++)
        {
            wall2.add(new Wall2(borderX / 2, i * (borderY / 5)));
            wall.add(new Wall((borderX / 2) - 64, i * (borderY / 20)));
            wall.add(new Wall((borderX / 2) - 128, i * (borderY / 22)));
            wall.add(new Wall((borderX / 2) + 64, i * (borderY / 20)));
            wall.add(new Wall((borderX / 2) + 128, i * (borderY / 22)));
            wall.add(new Wall((borderX / 2) - 350, i * (borderY / 25)));
            wall.add(new Wall((borderX / 2) + 400, i * (borderY / 25)));
        }
        pUp.add(new PowerUp(pUp1,60,600,2,false));
        pUp.add(new PowerUp(pUp1,1100,100,2,false));

        pUp.add(new PowerUp(pUp2,630,360,2,true));

        pUp.add(new PowerUp(health_pUp,200,100,0,true));
        pUp.add(new PowerUp(health_pUp,1100,600,0,true));

    }
}
