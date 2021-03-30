package src.settings;

public class Frame
{
    private int frame = 0;
    private int frame_Size;
    private int movement[];
    private Mode gameEvent;
    int pos = 0;

    public Frame(Mode game, int movement[], int frame_Size)
    {
        this.gameEvent = game;
        this.movement = movement;
        this.frame_Size = frame_Size;
    }

    public void play()
    {
        if (frame < movement[movement.length-1])
        {
            frame += frame_Size;
            if (frame == movement[pos])
            {
                gameEvent.setValue(pos++);
            }
        }
        else
        {
            frame = pos = 0;
        }
    }
}
