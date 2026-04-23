import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;

/**
 * GamePanel class
 * A class that extends JPanel
 * Constructor sets background color, initializes the sprite array, and starts an animation timer
 *
 * @author Purdue CS
 * @version April 10, 2026
 */

class GamePanel extends JPanel {
    // A list that contains the sprites to be animated
    private final ArrayList<Sprite> sprites;
    private Player player;
    private ArrayList<Monster> monsters = new ArrayList<>();
    private boolean gameOver = false;
    private boolean win = false;
    private boolean started = false;
    private int monsterFrameCount = 0;
    private final Random random = new Random();
    private int goalX = 0;
    private int goalY = 0;
    private boolean goalOnRight = true;
    public static final int TILE_SIZE = 50;

    public void startGame() {
        if (started) {
            return;
        }
        started = true;
        gameOver = false;
        win = false;
        monsterFrameCount = 0;
        generateGoal();
    }

    public GamePanel(Color color) {
        sprites = new ArrayList<>();
        setFocusable(true);
        setBackground(color);
        setPreferredSize(new Dimension(800, 600));

        player = new Player("sprites/char.png", 100, 100);
        addSprite(player);

        String[] verticalOpps = {"sprites/opp1.png", "sprites/opp2.png", "sprites/opp3.png", "sprites/opp4.png", "sprites/opp5.png", "sprites/opp6.png"};
        String[] horizontalOpps = {"sprites/opp7.png", "sprites/opp8.png", "sprites/opp1.png", "sprites/opp2.png", "sprites/opp3.png", "sprites/opp4.png"};

        for (int i = 0; i < 6; i++) {
            int x = (i * 2 + 1) * TILE_SIZE;
            Monster m = new Monster(verticalOpps[i], x, 0, true);
            monsters.add(m);
            addSprite(m);
        }

        for (int i = 0; i < 6; i++) {
            int y = (i * 2 + 1) * TILE_SIZE;
            Monster m = new Monster(horizontalOpps[i], 0, y, false);
            monsters.add(m);
            addSprite(m);
        }

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!started || gameOver || win) return;
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_W) player.move(0, -1, getWidth(), getHeight());
                if (key == KeyEvent.VK_S) player.move(0, 1, getWidth(), getHeight());
                if (key == KeyEvent.VK_A) player.move(-1, 0, getWidth(), getHeight());
                if (key == KeyEvent.VK_D) player.move(1, 0, getWidth(), getHeight());

                checkGame();
            }
        });

        new Timer(64, e -> {
            if (started && !gameOver && !win) {
                monsterFrameCount++;
                if (monsterFrameCount % 4 == 0) {
                    moveMonsters();
                    checkGame();
                }
            }
            repaint();
        }).start();
    }

    private void generateGoal() {
        int width = getWidth() > 0 ? getWidth() : getPreferredSize().width;
        int height = getHeight() > 0 ? getHeight() : getPreferredSize().height;
        goalOnRight = random.nextBoolean();

        if (goalOnRight) {
            goalX = width - TILE_SIZE;
            goalY = random.nextInt(height / TILE_SIZE) * TILE_SIZE;
        } else {
            goalY = height - TILE_SIZE;
            goalX = random.nextInt(width / TILE_SIZE) * TILE_SIZE;
        }
    }

    // Method to add sprites to the game panel
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    // Method that loops through all sprites and draws them every time repaint is called by the timer
    protected void paintComponent(Graphics g) {

        // This super call is required!
        super.paintComponent(g);

        g.setColor(new Color(255, 255, 255, 30));

        for (int x = 0; x < getWidth(); x += TILE_SIZE) {
            g.drawLine(x, 0, x, getHeight());
        }

        for (int y = 0; y < getHeight(); y += TILE_SIZE) {
            g.drawLine(0, y, getWidth(), y);
        }

        if (started) {
            g.setColor(new Color(0, 255, 255, 170));
            g.fillRect(goalX, goalY, TILE_SIZE, TILE_SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(goalX, goalY, TILE_SIZE, TILE_SIZE);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString("GOAL", goalX + 8, goalY + 30);
        } else {
            int boxWidth = 520;
            int boxHeight = 260;
            int boxX = (getWidth() - boxWidth) / 2;
            int boxY = (getHeight() - boxHeight) / 2;

            g.setColor(new Color(0, 0, 0, 200));
            g.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Game Instructions", boxX + 140, boxY + 50);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Use W/A/S/D to move your character.", boxX + 30, boxY + 95);
            g.drawString("Avoid all moving opponents.", boxX + 30, boxY + 130);
            g.drawString("Reach the glowing goal box to win.", boxX + 30, boxY + 165);
            g.drawString("The goal box is randomly placed on", boxX + 30, boxY + 200);
            g.drawString("the right or bottom edge.", boxX + 30, boxY + 235);
        }

        // Loop through sprites and call draw on each
        for (Sprite s : sprites) {
            s.draw((Graphics2D) g, s.getX(), s.getY(), getWidth(), getHeight());
        }

        if (!started) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("PRESS START", 250, 500);
            return;
        }

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 250, 300);
        }

        if (win) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("YOU WIN!", 250, 300);
        }
    }

    private void moveMonsters() {
        for (Monster m : monsters) {
            m.move(getWidth(), getHeight());
        }
    }

    private void checkGame() {
        for (Monster m : monsters) {
            if (((int) player.getX() == (int) m.getX()) && ((int) player.getY() == (int) m.getY())) {
                gameOver = true;
            }
        }

        if ((int) player.getX() == goalX && (int) player.getY() == goalY) {
            win = true;
        }
    }

}

