# Breakout Design
## Alisha Zhang


## Design Goals
* Gameplay:
    * User moves the paddle using ```LEFT``` and ```RIGHT``` keys to catch the ball to clear the bricks.
    * Level is passed when all bricks are cleared, or a corresponding cheatkey is pressed.
    * Game is won if all existing levels (3 currently) are passed
* User Interface:
  * No buttons, the game automatically advances, and stops before each level. User presses a key on the keyboard to continue
  * All controls are done through keys, no mouse click needed
* Features:
  * 3 Levels
  * 3 Different brick types
  * 3 powerups (randomly generated on specified bricks)
  * 6 cheatkeys 
  * 2 different paddle behaviors


## High-Level Design
* Game Components:
  * Ball, Paddle, Bricks, 3 Levels, Power-ups, Cheatkeys, Display/Splash Screens
* Ball, paddle, and bricks have their own class controlling their movement, positioning, and other properties.
* A Power up icon class controls the display of power up icons when user obtains a power up element.
* A level controller controls what is going on in each level after game starts, contains instances of ball, paddle, bricks, and power ups, and controls their interactions
* A display controller controls the display of the start screen and splash screens
* A game controller controls the flow of the game, calls levels and displays when needed.
* The main class calls an instance of the game controller to launch the game
* Game will end when user loses all lives in a level, or finishes all three levels.

## Assumptions or Simplifications
* The maximum columns of bricks is 9 for every level (unless you change ```BRICK_WIDTH```)
* Game window is set to be not resizeable

## Changes from the Plan
* **Level 3 and 1 brick formation**: slightly changed because it didn't look as good when I actually implemented it. The shape and the idea stayed the same. I switch 1 with 3 because turned out level 3's was easier to finished than 1's
* **Brick colors**: still using colors to indicate number of hits the brick needs, but changed to using color gradient palette for a different color for each level, because when I implemented the colors I had on the plan document, it looked really ugly, and I thought the color gradient looked a lot better.
* **Added pause and resume**: clicking on space pauses and resumes the game, also releases the ball after it's been reset after a miss

## How to Add New Levels
1. Add a brick formation file for the new level in the DataFiles folder
2. Add the path to this new file in ```LevelControl``` class as ```private static final String LEVEL_levelnumber```
3. Add a switch case for the new level to ```getLevelFileName()``` method in ```LevelControl``` class
4. Add a color coding for the bricks in the new level to ```getBrickColor()``` in ```Brick``` class
5. Add a case for the new level in the cheatkey used to jump to a specific level.
6. Modify ```finishedLastLevel```
