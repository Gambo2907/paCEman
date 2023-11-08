package Objects;

import java.awt.*;

public class Box
{
    private int x,y;
    private boolean crossing;
    
    public Box() {};

    
     
    public Box(int x, int y) {
        this.x=x;
        this.y=y;
        crossing = true;
    }

    
    public int getX()
    {
        return x;
    }

    
    public int getY()
    {
        return y;
    }

}