package AbstractFactory;


public interface GhostFactory {
    Ghost createGhostBlinky(int x, int y);
    Ghost createGhostClyde(int x, int y);

    Ghost createGhostInky(int x, int y);

    Ghost createGhostPinky(int x, int y);
}
