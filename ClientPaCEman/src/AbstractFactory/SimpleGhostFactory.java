package AbstractFactory;

public class SimpleGhostFactory implements GhostFactory {

    @Override
    public Ghost createGhostBlinky(int x, int y) {
        return new Blinky(x,y);
    }

    @Override
    public Ghost createGhostClyde(int x, int y) {
        return new Clyde(x,y);
    }

    @Override
    public Ghost createGhostInky(int x, int y) {
        return new Inky(x,y);
    }

    @Override
    public Ghost createGhostPinky(int x, int y) {
        return new Pinky(x,y);
    }

}
