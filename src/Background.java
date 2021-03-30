package src;

import src.settings.GameBg;
import javax.imageio.ImageIO;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.net.URL;

public class Background extends Tank implements GameBg
{
    BufferedImage bg_map;

    URL sound = Tank.class.getResource("resources/music.wav");
    AudioClip bg_music = newAudioClip(sound);
    Boolean game_over = true;

    public Background()
    {
        try
        {
            File file = new File(getClass().getResource("resources/Background.bmp").toURI());
            this.bg_map = ImageIO.read(file);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void show(Graphics2D g, ImageObserver observer)
    {
        g.drawImage(bg_map, 0, 0, this);
        int w = this.bg_map.getWidth();        //width for map background
        for(int i =0; i < 8; i ++)
        {
            g.drawImage(this.bg_map, w,0 , this);
            w = w + 320;
        }
        w =0;

        int h = this.bg_map.getHeight();   //height for map background
        for(int i =0; i < 8; i++)
        {
            g.drawImage(this.bg_map, w,h, this);
            w = w + 320;
        }
        w =0;
        h = h + 240;
        for(int i =0; i < 8; i++)
        {
            g.drawImage(this.bg_map, w, h, this);
            w += 320;
        }
    }

    @Override
    public void music()
    {
        bg_music.loop();
    }

    @Override
    public void over()
    {
        if (game_over)
        {
            bg_music.stop();
            game_over = false;
        }
    }
}
