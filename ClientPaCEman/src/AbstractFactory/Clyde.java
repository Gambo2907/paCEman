package AbstractFactory;


public class Clyde extends Ghost
{
    
    public Clyde(int x, int y)
    {
        super("/Resources/clyde.gif", x, y);
    }

    @Override
    public void finalPanic()
    {
        super.finalPanic();
        updateImage("/Resources/clyde.gif");
    }

    @Override
    public void death(){
        super.death();
        x = 540;
        y = 300;
        dx=dy=0;
    }

    @Override
    public void artificialIntelligence(int pacmanX, int pacmanY)
    {
        super.artificialIntelligence(pacmanX, pacmanY);
        int movement = random.nextInt(3);
        if(!death && intersection){
            switch(movement){
                case 0:
                    if(up){
                        up();
                        break;}
                case 1:
                    if(down){
                        down();
                        break;}
                case 2:
                    if(right){
                        right();
                        break;}
                case 3:
                    if(left){
                        left();
                        break;
                    }
            }
        }
    }

}