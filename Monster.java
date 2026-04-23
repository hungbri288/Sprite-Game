/**
 * Monster class
 * Create Monster class
 * Initializes the Monster objects
 *
 * @author Hung Nguyen
 * @version April 10, 2026
 */

public class Monster extends PixelSprite {
    private final boolean vertical;
    private int direction = 1;
    private int speed = 2;

    public Monster(String file, int x, int y, boolean vertical) {
        super(file, x, y);
        this.vertical = vertical;
    }

    public void move(int panelWidth, int panelHeight) {
        if (vertical) {
            double newY = getY() + direction * GamePanel.TILE_SIZE * speed;

            if (newY < 0) {
                direction = 1;
                newY = 0;
            } else if (newY > panelHeight - GamePanel.TILE_SIZE) {
                direction = -1;
                newY = panelHeight - GamePanel.TILE_SIZE;
            }

            setY(newY);
        } else {
            double newX = getX() + direction * GamePanel.TILE_SIZE * speed;

            if (newX < 0) {
                direction = 1;
                newX = 0;
            } else if (newX > panelWidth - GamePanel.TILE_SIZE) {
                direction = -1;
                newX = panelWidth - GamePanel.TILE_SIZE;
            }

            setX(newX);
        }
    }
}
