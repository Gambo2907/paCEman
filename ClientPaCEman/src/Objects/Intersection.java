package Objects;

import java.awt.*;

public class Intersection
{

    private int x,y;
   
    public Intersection() {}
    
    public Intersection(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    
    public Rectangle createRectangle()
    {
        return new Rectangle(x*60,y*60,60,60);
    }
}
