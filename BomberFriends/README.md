# Bomber Friends-inspired game

A Java desktop game developed as the final project for the **Advanced Programming** course during my bachelor's studies.

**Author:** Ava Moshfegh  
**Completed:** 3 July 2022

Move through a maze, plant bombs, break walls, collect power-ups, and defeat enemies. This game is inspired by Bomber Friends.

## Features

- A 22-column by 14-row board with 40-pixel tiles.
- Fixed boundary and interior walls, 50 randomly placed normal walls, and 20 randomly placed special walls.
- Three enemy types with different health, movement speeds, and bomb abilities.
- Directional player and bomber animations loaded from GIF files.
- Bomb explosions extending up, down, left, and right.
- Special walls that reveal a random health, bomb-power, or blast-range reward after three hits.
- A main menu, settings window, health display, and win/loss messages.

## Controls

| Key | Action |
| --- | --- |
| W | Move up |
| A | Move left |
| S | Move down |
| D | Move right |
| Space | Plant a bomb |

Select **start game** from the main menu to show the game window. Select **settings** to edit enemy counts, player bomb radius, and player bomb power.

## Player and objective

The player starts near the upper-right corner with 3 health points. Initial bomb power and blast radius are both 1. Player bombs explode 2 seconds after placement.

Enemies are initially placed in the left half of the board. Defeating every enemy triggers a win message; reaching zero health triggers a loss message. Bomb explosions can damage the player as well as enemies.

## Enemies

Souls are the fastest enemies, skeletons have the most health, and bombers add timed explosions to the arena. Enemy movement uses wall-collision checks and direction changes.

| Enemy | Default count | Health | Movement per update |
| --- | --- | --- | --- |
| Soul | 3 | 1 | 6 pixels |
| Skeleton (`Skelton`) | 2 | 3 | 2 pixels |
| Bomber | 1 | 1 | 4 pixels |

The movement task is scheduled every 100 milliseconds. Bombers have bomb power 4, range 5, and a 5-second fuse. Their bomb-placement task is scheduled every 10 seconds.

## Board, walls, and rewards

The arena contains 22 columns and 14 rows, with each tile measuring 40 by 40 pixels. Indestructible walls form the boundary and an interior pattern at alternating row and column positions. Normal and special walls are placed randomly, creating a different layout when a world is generated.

| Wall | Behavior |
| --- | --- |
| Fixed wall | Blocks movement and cannot be destroyed. |
| Normal wall | Breaks after one bomb hit. There are 50 at world creation. |
| Special wall | Requires three bomb hits and reveals a random reward. There are 20 at world creation. |

A special wall reveals one of three rewards: a heart adds 1 health point, a power item adds 1 to bomb damage, or a range item adds 1 to the blast radius. Collecting the reward updates the player's attributes and clears the item from the tile.

## Bombs and explosions

Pressing Space plants a bomb on the player's current tile. Each bomb stores its position, damage, blast radius, fuse time, and whether it belongs to a bomber enemy. A timer triggers its explosion.

The explosion draws a central effect and directional effects along the four axes. Collision checks apply damage to characters in the affected area. Destructible walls are updated after the explosion effect, and special walls can reveal a reward. Bomber-owned bombs are marked separately so their explosions do not damage enemies.

## Menus and settings

The main menu has **start game**, **settings**, and **exit** buttons. The settings window contains fields for soul, skeleton, and bomber counts, along with player bomb radius and power. The **set** button stores the entered values, and **back** returns to the menu.

The game displays the player's health below the board. When the player loses all health or all enemies are defeated, a result dialog appears and the main menu is shown again.

## Design

The project separates character behavior, board tiles, bomb logic, and user-interface windows into Java classes. `GameObject` provides shared position and collision-boundary behavior. `Player` and `Enemy` extend it, and the three enemy classes specialize `Enemy` with their own health, speed, images, and bomb behavior.

`Tile` represents a board cell. Wall subclasses define whether a tile can be destroyed and whether it contains a reward. `World` connects these objects, generates the arena, schedules enemy actions, and checks game results. Swing handles the windows and keyboard input; AWT handles drawing and rectangular collision bounds.

## Requirements

- A Java Development Kit (JDK). The source has been compiled successfully with OpenJDK 21.
- A graphical desktop environment for the Swing interface.
- The original `assets` folder, with its filenames and subfolders preserved.

The project uses Java Swing, AWT, and standard Java utilities. No external Java libraries or build tool are required.

## Build and run

Keep all 18 `.java` files and the `assets` folder in the project root. From that directory, run:

```sh
mkdir out
javac -d out *.java
java -cp out Game
```

If `out` already exists, skip the first command. Run the final command from the project root, not from inside `out`: images are loaded using relative paths beginning with `assets/`.

In an IDE, open the folder as a Java project, select a JDK, and run `Game.main()`. Set the working directory to the project root.

## Required images

| Directory | Filenames |
| --- | --- |
| `assets/` | `bomb.png` |
| `assets/player/` | `up.gif`, `down.gif`, `left.gif`, `right.gif`, `idle.png`, `dying.png` |
| `assets/enemy/` | `soul.png`, `skelton.png` |
| `assets/enemy/bomber/` | `up.gif`, `down.gif`, `left.gif`, `right.gif`, `dying.gif` |
| `assets/explosion/` | `core.png`, `up.png`, `down.png`, `left.png`, `right.png` |
| `assets/wall/` | `hardWall.png`, `normalWall.png`, `amazingWall.png`, `heart.png`, `p.png`, `r.png` |

Preserve these filenames and their capitalization so the game can load its character animations, wall textures, bombs, rewards, and explosion effects.

## Source file descriptions

| File | Purpose |
| --- | --- |
| `Game.java` | Contains `main()` and creates the game, menu, and settings windows. |
| `GamePanel.java` | Draws the world and handles WASD movement and Space input. |
| `World.java` | Generates walls and enemies, schedules enemy movement and bombing, checks collisions, draws the scene, and displays results. |
| `GameObject.java` | Stores character coordinates and dimensions; provides tile lookup and collision rectangles. |
| `Direction.java` | Defines the up, down, left, right, idle, and dying states. |
| `Player.java` | Manages player movement, directional sprites, health, bomb attributes, and bomb placement. |
| `Enemy.java` | Provides shared enemy movement, health, drawing, and damage behavior. |
| `Soul.java` | Defines the fast soul enemy with 1 health point. |
| `Skelton.java` | Defines the slower skeleton enemy with 3 health points. |
| `Bomber.java` | Defines the animated bomber enemy and its bomb power, radius, and fuse. |
| `Tile.java` | Represents board cells, textures, occupancy, collision bounds, and hooks for destruction and rewards. |
| `HardWall.java` | Defines indestructible walls that block movement. |
| `NormalWall.java` | Defines walls destroyed by a single bomb hit. |
| `AmazingWall.java` | Tracks hits on special walls, selects a random reward, and applies it to the player. |
| `Bomb.java` | Stores bomb attributes and schedules the explosion after its fuse. |
| `Explosion.java` | Draws blast effects, checks damage, and updates affected walls and tiles. |
| `menuWindow.java` | Creates the main menu and handles its start, settings, and exit buttons. |
| `settingsWindow.java` | Creates the configuration fields and stores enemy-count and player-bomb settings. |
