public class Player extends PixelSprite {
    public Player(String file, int x, int y) {
        super(file, x, y);
    }

    public void move(int dx, int dy, int width, int height) {
        double newX = getX() + dx * GamePanel.TILE_SIZE;
        double newY = getY() + dy * GamePanel.TILE_SIZE;
        
        // -50 to make characters not go out of screen
        if (newX >= 0 && newX <= width - 50) {
            setX(newX);
        }
        if (newY >= 0 && newY <= height - 50) {
            setY(newY);
        }
    }
}
