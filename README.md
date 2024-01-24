# breakout
## Alisha Zhang
## finished January 23, 2024

DO NO FORK THIS REPOSITORY, clone it directly to your computer.

This project implements the game of Breakout with multiple levels.

### Timeline

 * Start Date: January 13, 2024

 * Finish Date: January 23, 2024

 * Hours Spent: (including time spend trying to figure out how javafx works)
1/14 (4-5); 
1/15 (5-6); 
1/16 (5-6); 
1/19 (6-7); 
1/20 (8); 
1/21 (9-10);
1/22 (5-6)
1/23


### Attributions

 * Resources used for learning (including AI assistance)
   * [JavaFX Basics](https://horstmann.com/corejava/corejava_11ed-bonuschapter13-javafx.pdf) (week 1 reading)
   * [JavaFX stages](https://youtu.be/As7TEjqJ3Ao?si=ATI0a_s1QEEIi9O7)
   * [JavaFX scenes + drawing stuff](https://youtu.be/7nlU3_kEjTE?si=mToogJ46e_A_Y86K) (did not watch the rest because he started using FXML and SceneBuilder after these two videos)

 * Resources used directly (including AI assistance)
   * El Capitan spinning beachball GIF by madebyjw.com -- https://gfycat.com/rapidathleticilladopsis
   * [JavaFX API documentation](https://openjfx.io/javadoc/17/)
   * [Reading files in java](https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line)
   * [Color Palette](https://www.color-hex.com/color-palettes/)
   * AI (used mainly for syntax purposes)


### Running the Program

 * Main class: ```Main```

 * Data files needed: ```LevelOneBricks```, ```LevelTwoBricks```, ```LevelThreeBricks``` (needs to be in the DataFiles folder under the same directory as the code files, if moved, change the file path in ```levelControl```)

 * Key/Mouse inputs: 
   * ```SPACE```: start/pause game
   * ```LEFT```/```RIGHT```: move the paddle to the left/right

 * Cheat keys:
   * ```L```: adds one life to current level
   * ```R```: resets the ball and paddle to initial position
   * ```C```: clears current level and goes to the next level


### Notes/Assumptions

 * Assumptions or Simplifications:

 * Known Bugs:
   * If the ball comes in contact with the paddle at a weird angle, it'll get stuck inside the paddle and bounce up instead of falling off the screen

 * Features implemented:

 * Features unimplemented:

 * Noteworthy Features:
   * The color of the bricks indicate the hits left to clear it. The lighter the brick color is, the less hit it needs. When a block with multiple hits gets hit, it will change into the color of the next lower hit count.


### Assignment Impressions


