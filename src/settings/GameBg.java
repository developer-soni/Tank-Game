package src.settings;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public interface GameBg
{
    void show(Graphics2D graphic, ImageObserver observable);
    void music();
    void over();
}