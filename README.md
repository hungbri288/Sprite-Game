# Sprite Game

A simple Java Swing-based game where you control a character sprite to reach a randomly placed goal while avoiding moving monster sprites.

## Description

This game features:
- Grid-based movement using WASD keys
- A player character that must navigate to a glowing goal box
- Moving monster sprites that the player must avoid
- Start and restart functionality
- Random goal placement on either the right or bottom edge of the game area

The objective is to reach the goal without colliding with any monsters. If you touch a monster, it's game over. Successfully reaching the goal results in a win.

## Requirements

- Java Development Kit (JDK) 17 or higher
- The game uses Swing for the GUI and requires images in the `sprites/` folder

## How to Run

1. Ensure you have JDK 17+ installed.
2. Compile the Java files:
   ```
   javac *.java
   ```
3. Run the game:
   ```
   java Main
   ```

## Controls

- **W**: Move up
- **A**: Move left
- **S**: Move down
- **D**: Move right
- **Start Button**: Begin the game
- **Restart Button**: Restart the game

## Game Mechanics

- The game area is divided into a grid with tiles of 50x50 pixels.
- Monsters move automatically in vertical or horizontal patterns.
- The goal box appears randomly on the right or bottom edge after starting the game.
- The game ends when you either win by reaching the goal or lose by colliding with a monster.

## Assets

The game uses the following sprite images located in the `sprites/` folder:
- `char.png`: Player character
- `opp1.png` to `opp8.png`: Monster sprites

## Author

Hung Nguyen  
Version: April 10, 2026

## Troubleshooting

- Ensure all Java files are in the same directory.
- Make sure the `sprites/` folder contains all required image files.
- If the game doesn't start, check that you have Java installed by running `java -version`.