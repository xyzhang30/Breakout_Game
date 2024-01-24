# Breakout Design
## Alisha Zhang


## Design Goals


## High-Level Design


## Assumptions or Simplifications
* The maximum columns of bricks is 9 for every level (unless you change the ```BRICK_WIDTH``` variable), since the window is not resizeable

## Changes from the Plan
* **Level 3 and 1 brick formation**: slightly changed because it didn't look as good when I actually implemented it. The shape and the idea stayed the same. I switch 1 with 3 because turned out level 3's was easier to finished than 1's
* **Brick colors**: still using colors to indicate number of hits the brick needs, but changed to using color gradient palette for a different color for each level, because when I implemented the colors I had on the plan document, it looked really ugly, and I thought the color gradient looked a lot better.
* **Added pause and resume**: clicking on space pauses and resumes the game, also releases the ball after it's been reset after a miss

possible bonus feature: 
* for powerup blocks, player must catch the dropped powerup element in order to use it.
* lightmode/darkmode;

## How to Add New Levels
1. Add a brick formation file for the new level in the DataFiles folder
2. Add the path to this new file in ```LevelControl``` class as ```private static final String LEVEL_levelnumber```
3. Add a switch case for the new level to ```getLevelFileName()``` method in ```LevelControl``` class
4. Add a color coding for the bricks in the new level to ```getBrickColor()``` in ```Brick``` class
