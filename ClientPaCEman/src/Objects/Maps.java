package Objects;

import game.ViewController;

import javax.swing.ImageIcon;
import java.awt.Image;


public class Maps
{
    
    private int [][] level =
                    {{0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,1,1,1,1,1,1,1,1,1,1,1,0},
                    {0,1,0,0,1,0,0,0,0,1,0,1,0},
                    {0,1,0,1,1,1,1,1,1,1,1,1,0},
                    {0,1,1,1,0,0,0,1,0,1,0,1,0},
                    {0,1,0,1,0,3,0,1,0,1,0,1,0},
                    {0,1,1,1,0,3,0,1,0,1,0,1,0},
                    {0,1,0,1,0,3,0,1,0,1,0,3,0},
                    {0,1,1,1,0,3,0,1,0,1,0,1,0},
                    {0,1,0,1,0,3,0,1,0,1,0,1,0},
                    {0,1,1,1,0,0,0,1,0,1,0,1,0},
                    {0,1,0,1,1,1,1,1,1,1,1,1,0},
                    {0,1,0,0,1,0,0,0,0,1,0,1,0},
                    {0,1,1,1,1,1,1,1,1,1,1,1,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0}};

    private Image image;      
    private ImageIcon imageIcon;
    private int dots;
    private int x, y;

    
    public Maps() { dots = 0;}

    

    public Image getImage(int x, int y)
    {
        int location = level[x][y];
        if(location == 0)// MURO
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/wall.gif"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 1)// PUNTO
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/Pac-Dot.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 2)// PILDORA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/pill.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 3)// VACIO
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/empty.gif"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 4) // CEREZA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/cherry.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 5)// MELON
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/melon.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 6)// MANZANA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/apple.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 7)// NARANJA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/orange.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 8)// FRESA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/strawberry.png"));
            image = imageIcon.getImage();
            return image;
        }

        else
        {
            return null;
        }
    }
    
    public int getValue(int x, int y)
    {
        return level[x][y];
    }

    
    public void eatDot(int x, int y)
    {
        level[x][y] = 3;
    }

    
    public int sizeMapX()
    {
        return level.length;
    }
    
    public int sizeMapY()
    {
        return level[0].length;
    }

   
    public boolean verifyBox(java.lang.Integer x,java.lang.Integer y){
        if (level[x][y] == 0){
            System.out.println("NO SE PUEDE AGREGAR UN FANTASMA EN EL LUGAR SOLICITADO YA QUE ES UN MURO, INTENTA CON OTRO");
            return false;
        }
        return true;

    }

  
    public void addPill(java.lang.Integer x,java.lang.Integer y){
        if (level[x][y] == 1){
            level[x][y] = 2;
            ViewController.getInstance().setDots(1);
        }else if (level[x][y] == 0){
            System.out.println("NO SE PUEDE AGREGAR LA PILDORA EN EL LUGAR SOLICITADO YA QUE ES UN MUERO, INTENTA CON OTRO");
        }else{
            level[x][y] = 2;
        }
    }

    
    public void addFruit(java.lang.Integer x,java.lang.Integer y, java.lang.Integer fruit){
        if (level[x][y] == 1){
            level[x][y] = fruit;
            ViewController.getInstance().setDots(1);
        }else if (level[x][y] == 0){
            System.out.println("NO SE PUEDE AGREGAR LA FRUTA EN EL LUGAR SOLICITADO YA QUE ES UN MUERO, INTENTA CON OTRO");
        }else{
            level[x][y] = fruit;
        }

    }

    
    public int totalDots(){
        for(int x = 0; x< sizeMapX(); x++){
            for (int y = 0; y< sizeMapY(); y++){
                int value = getValue(x,y);
                if((value == 1) || (value == 2)){
                    dots++;
                }
            }
        }
        return dots;
    }

    

    public boolean up(int x, int y){
        if(level[x][y-1] == 0){
            return false;
        }
        else{
            return true;}
    }

   
    public boolean down(int x, int y){
        if(level[x][y+1] == 0){
            return false;
        }
        else{
            return true;}
    }

    
    public boolean right(int x, int y){
        if(level[x+1][y] == 0){
            return false;
        }
        else{
            return true;}
    }

    
    public boolean left(int x, int y){
        if(level[x-1][y] == 0){
            return false;
        }
        else{
            return true;}
    }



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}
}
