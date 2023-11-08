package Objects;

import game.ViewController;

import javax.swing.ImageIcon;
import java.awt.*;

public abstract class Characters
{
    protected int x,y,dx,dy;    //Las variables de las coordenadas x e y. Desplazamiento x e y.
    private Image image;       //La variable imagen donde irá la imagen de la figura
    private ImageIcon imageIcon;
    protected boolean up, down, right, left, intersection; //Las variables que nos indican donde tenemos camino libre, sin muro.

    
    public Characters () {};

    
    public Characters (String path, int x, int y)
    {
        this.x = x;
        this.y = y;
        imageIcon = new ImageIcon(this.getClass().getResource(path));
        image = imageIcon.getImage();
    }

    
    public int getX()
    {
        return x;
    }

    
    public int getY()
    {
        return y;
    }

   
    public void setX(int x) {
        this.x = x;
    }

   
    public void setY(int y) {
        this.y = y;
    }

    
    public Image getImage()
    {
        return image;
    }

    
    public void move()
    {
        x +=dx;
        y +=dy;
        intersection =false;
    }

    
    public void stop()
    {
        x -= dx;
        y -= dy;
    }

    
    public void back()
    {
        stop();
        dx = dx*(-1);
        dy = dy*(-1);
    }

    
    public void up()
    {
        dy=-ViewController.getInstance().getGameSpeed();
        dx=0;
    }

    
    public void down()
    {
        dy=ViewController.getInstance().getGameSpeed();
        dx=0;
    }

   
    public void right()
    {
        dx=ViewController.getInstance().getGameSpeed();
        dy=0;
    }

   
    public void left()
    {
        dx=-ViewController.getInstance().getGameSpeed();
        dy=0;
    }

    
    public void updateImage(String path)
    {
        imageIcon = new ImageIcon(this.getClass().getResource(path));
        image = imageIcon.getImage();
    }

    
    public int getBoxX()
    {
        int x=this.x/60;
        return x;
    }

   
    public int getBoxY()
    {
        int y=this.y/60;
        return y;
    }


    
    public Rectangle createRectangle()
    {
        return new Rectangle(this.x,this.y,60,60);
    }

    
    public void intersection(){
        intersection = true;
    }

    
    public void availableDirections(boolean newUp, boolean newDown, boolean newRight, boolean newLeft)
    {
        this.up = newUp;
        this.down = newDown;
        this.right = newRight;
        this.left = newLeft;
    }

}
