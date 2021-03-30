package src;

import src.settings.Control;
import src.settings.Frame;
import src.settings.Mode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Tank extends JApplet implements Runnable {

    static HashMap<Integer, String> tank_move = new HashMap<>();
    static int borderX = 1280, borderY = 720;
    static int screenW = 1280, screenH = 720, displayX1, displayY1, displayX2, displayY2;
    static boolean gameCheck = false;
    private Thread mainThread;
    BufferedImage buffImg1, buffImg2;
    ImageObserver obs;
    Frame timeCount;
    Mode gameEvent;
    Control gameController;
    Background tankBg;
    static Player tank1, tank2;
    static Random randomObj;
    static Player[] tPlayer = new Player[3];
    static Image[] tankHealth;
    static ArrayList<Bullet> t1Bullet, t2Bullet;
    static AudioClip boom1, boom2;
    Font score = new Font("Arial", Font.BOLD, 24);
    Font lives = new Font("Stencil", Font.BOLD, 40);

    public static void main(String[] args)
    {
        final Tank beta = new Tank();
        beta.init();
        JFrame frame = new JFrame("Tank Game Final");
        frame.addWindowListener(new WindowAdapter() {});
        frame.getContentPane().add("Center", beta);
        frame.pack();
        frame.setSize(new Dimension(screenW, screenH));
        frame.setVisible(true);
        frame.setResizable(false);
        beta.start();
    }

    public void init()
    {
        tank_move.put(KeyEvent.VK_A, "left1");
        tank_move.put(KeyEvent.VK_W, "up1");
        tank_move.put(KeyEvent.VK_S, "down1");
        tank_move.put(KeyEvent.VK_D, "right1");
        tank_move.put(KeyEvent.VK_SPACE, "shoot1");
        tank_move.put(KeyEvent.VK_LEFT, "left2");
        tank_move.put(KeyEvent.VK_UP, "up2");
        tank_move.put(KeyEvent.VK_DOWN, "down2");
        tank_move.put(KeyEvent.VK_RIGHT, "right2");
        tank_move.put(KeyEvent.VK_ENTER, "shoot2");

        Image[] healthCnt = {getSprite("resources/health_1.png"),
                getSprite("resources/health_2.png"),
                getSprite("resources/health_3.png"),
                getSprite("resources/health_4.png"),
                getSprite("resources/health_5.png"),
                getSprite("resources/explosion2_1.png"),
                getSprite("resources/explosion2_2.png"),
                getSprite("resources/explosion2_3.png"),
                getSprite("resources/explosion2_4.png"),
                getSprite("resources/explosion2_5.png"),
                getSprite("resources/explosion2_6.png"),
                getSprite("resources/explosion2_7.png")};
        tankHealth = healthCnt;


        Image[] bullet = {getSprite("resources/tinyBullet2.png"),
                getSprite("resources/tinyBullet2.png"),
                getSprite("resources/tinyBullet2.png")};

        tankBg = new Background();

        String t1Img = "resources/Tank_blue_light_strip60.png";
        String t2Img = "resources/Tank_red_light_strip60.png";

        t1Bullet = new ArrayList<Bullet>();
        t2Bullet = new ArrayList<Bullet>();

        tank1 = new Player(t1Img, t2Bullet, t1Bullet, bullet, 1);
        tank2 = new Player(t2Img, t1Bullet, t2Bullet, bullet, 2);

        tPlayer[2] = tank1;
        tPlayer[1] = tank2;

        randomObj = new Random();
        setBackground(Color.black);
        this.setFocusable(true);
        obs = this;
        gameEvent = new Mode();
        gameEvent.addObserver(tank1);
        gameEvent.addObserver(tank2);
        gameEvent.addObserver(randomObj);

        int timedEvents[] = {1000, 800, 900, 700};
        timeCount = new Frame(gameEvent, timedEvents, 1);

        gameController = new Control(gameEvent);
        addKeyListener(gameController);

        boom1 = getAudio("resources/Explosion_large.wav");
        boom2 = getAudio("resources/Explosion_small.wav");

    }

    private AudioClip getAudio(String file)
    {
        URL url = Tank.class.getResource(file);
        return newAudioClip(url);
    }

    public void start()
    {
        tankBg.music();
        mainThread = new Thread(this);
        mainThread.setPriority(Thread.MIN_PRIORITY);
        mainThread.start();
    }

    @Override
    public void run()
    {
        Thread me = Thread.currentThread();
        while (mainThread == me) {
            repaint();
            try {
                mainThread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void paint(Graphics graphic)
    {
        Dimension dim = getSize();
        Graphics2D gr1 = Area(borderX, borderY);
        Graphics2D gr2 = OuterArea(borderX, borderY);
        Refresh(borderX, borderY, gr1);
        gr1.dispose();

        //View for player 1
        if (!gameCheck)
        {
            displayX1 = tank1.a + 30 - screenW / 4;
            if (displayX1 < 0)
            {
                displayX1 = 0;
            }
            else if (displayX1 + screenW / 2 > borderX)
            {
                displayX1 = borderX - screenW / 2;
            }
            displayY1 = tank1.b + 30 - screenH / 2;
            if (displayY1 < 0)
            {
                displayY1 = 0;
            }
            else if (displayY1 + screenH > borderY)
            {
                displayY1 = borderY - screenH;
            }

            //View for player 2
            displayX2 = tank2.a + 30 - screenW / 4;
            if (displayX2 < 0)
            {
                displayX2 = 0;
            }
            else if (displayX2 + screenW / 2 > borderX)
            {
                displayX2 = borderX - screenW / 2;
            }
            displayY2 = tank2.b + 30 - screenH / 2;
            if (displayY2 < 0)
            {
                displayY2 = 0;
            }
            else if (displayY2 + screenH > borderY)
            {
                displayY2 = borderY - screenH;
            }

            gr2.drawImage(buffImg1.getSubimage(displayX1, displayY1, screenW / 2, screenH), 0, 0, this);
            gr2.drawImage(buffImg1.getSubimage(displayX2, displayY2, screenW / 2, screenH), screenW / 2, 0, this);
            gr2.drawLine(dim.width / 2 + 2, 0, dim.width / 2 + 2, dim.height);
            gr2.setFont(score);
            gr2.setColor(Color.WHITE);
            gr2.drawString(tank1.pts + "", dim.width / 4, 40);
            gr2.setColor(Color.WHITE);
            gr2.drawString(tank2.pts + "", dim.width * 3 / 4, 40);
            gr2.setFont(lives);
            gr2.setColor(Color.WHITE);
            gr2.drawString("Player 1 Life: " + tank1.lives + "", 40, 40);
            gr2.drawString("Player 2 Life: " + tank2.lives + "", dim.width - 240, 40);
            gr2.drawRect(dim.width / 2 - (dim.width / 5) / 2 - 1, dim.height * 3 / 4 - 1, dim.width / 5 + 1, dim.height / 5 + 1);
            gr2.drawImage(buffImg1.getScaledInstance(dim.width / 5, dim.height / 5, 1), dim.width / 2 - (dim.width / 5) / 2, dim.height * 3 / 4, this);
            gr2.dispose();
            graphic.drawImage(buffImg2, 0, 0, this);
        }
        else
        {
            graphic.drawImage(buffImg1, 0, 0, this);
        }
    }

    public Graphics2D OuterArea(int w, int h)
    {
        Graphics2D graphics = null;
        if (buffImg2 == null || buffImg2.getWidth() != w || buffImg2.getHeight() != h)
        {
            buffImg2 = (BufferedImage) createImage(w, h);
        }
        graphics = buffImg2.createGraphics();
        graphics.setBackground(getBackground());
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics.clearRect(0, 0, w, h);
        return graphics;
    }

    public Graphics2D Area(int w, int h)
    {
        Graphics2D graphics = null;
        if (buffImg1 == null || buffImg1.getWidth() != w || buffImg1.getHeight() != h)
        {
            buffImg1 = (BufferedImage) createImage(w, h);
        }
        graphics = buffImg1.createGraphics();
        graphics.setBackground(getBackground());
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics.clearRect(0, 0, w, h);
        return graphics;
    }

    public void Refresh(int w, int h, Graphics2D graphics)
    {
        if (!gameCheck)
        {
            timeCount.play();
            tankBg.show(graphics, this);
            tank1.flow();
            tank1.show(graphics, this);
            tank2.flow();
            tank2.show(graphics, this);
            randomObj.show(graphics, this);

            for (int i = 0; i < t1Bullet.size(); i++)
            {
                if (t1Bullet.get(i).flow())
                {
                    t1Bullet.remove(i);
                }
                else
                {
                    t1Bullet.get(i).show(graphics, this);
                }
            }

            for (int i = 0; i < t2Bullet.size(); i++)
            {
                if (t2Bullet.get(i).flow())
                {
                    t2Bullet.remove(i);
                }
                else
                {
                    t2Bullet.get(i).show(graphics, this);
                }
            }
        }
        else
        {
            tankBg.over();
            tankBg.show(graphics, this);
            String gameOverMsg;
            String gameOverMsg1;
            String gameOverMsg2;
            if (tank1.pts > tank2.pts)
            {
                gameOverMsg = "Player 1: " + (tPlayer[2].pts +5);
                gameOverMsg1 = "Player 2: " + tPlayer[1].pts;
                gameOverMsg2 = "!! Player 1 Wins !!";
            }
            else
            {
                gameOverMsg = "Player 2: " + (tPlayer[1].pts +5);
                gameOverMsg1 = "Player 1: " + tPlayer[2].pts;
                gameOverMsg2 = "!! Player 2 Wins !!";
            }
            Font victory_font = new Font("Osaka", Font.BOLD, 60);
            graphics.setFont(victory_font);

            FontRenderContext context = graphics.getFontRenderContext();
            Rectangle2D bounds = victory_font.getStringBounds(gameOverMsg, context);

            double x = (getWidth() - bounds.getWidth()) / 2;
            double y = (getHeight() - bounds.getHeight()) / 2;
            double ascent = -bounds.getY();
            double baseY = y + ascent;

            graphics.setPaint(Color.YELLOW);
            graphics.drawString(gameOverMsg, (int) x, (int) baseY);

            FontRenderContext context1 = graphics.getFontRenderContext();
            Rectangle2D bounds1 = victory_font.getStringBounds(gameOverMsg1, context1);

            double x1 = (getWidth() - bounds1.getWidth()) / 2;
            double y1 = 403.01;
            double ascent1 = -bounds1.getY();
            double baseY1 = y1 + ascent1;

            graphics.setPaint(Color.YELLOW);
            graphics.drawString(gameOverMsg1, (int) x1, (int) baseY1);

            Font victory_font2 = new Font("Osaka", Font.BOLD, 60);
            graphics.setFont(victory_font2);

            FontRenderContext context2 = graphics.getFontRenderContext();
            Rectangle2D bounds2 = victory_font2.getStringBounds(gameOverMsg2, context2);

            double x2 = (getWidth() - bounds2.getWidth()) / 2;
            double y2 = 503 ;
            double ascent2 = -bounds.getY();
            double baseY2 = y2 + ascent2;

            graphics.setPaint(Color.YELLOW);
            graphics.drawString(gameOverMsg2, (int) x2, (int) baseY2);
        }
    }

    public Image getSprite(String resource)
    {
        URL url = Tank.class.getResource(resource);
        Image img = getToolkit().getImage(url);
        try
        {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        }
        catch (Exception e)
        {
            System.out.println(e + "Try again.");
        }
        return img;
    }

    public BufferedImage getBufferedImage(String image) throws IOException
    {
        URL url = Tank.class.getResource(image);
        BufferedImage img = ImageIO.read(url);
        try
        {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        }
        catch (Exception e)
        {
            System.out.println(e + "Try again.");
        }
        return img;
    }
}
