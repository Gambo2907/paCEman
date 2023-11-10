package AbstractFactory;

import java.util.Random;

public abstract class Ghost extends Characters
{
    protected boolean panic, death;
    protected Random random;
    
    public Ghost (String path, int x, int y)
    {
        super(path,x,y);
    }


    public void panic()
    {
        super.updateImage("/Resources/panic_ghost.gif");
        panic = true;
    }

    
    public void finalPanic()
    {
        panic = false;
    }

    
    public void death()
    {
        death =true;
    }

    
    public boolean isDeath() {
        return death;
    }

    
    public void exit(){
        y=180;
        x=x-60;
        death = false;
    }

    public void artificialIntelligence(int pacmanX, int pacmanY){
        random = new Random();
    }
}

