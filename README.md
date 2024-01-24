# breakout
## Alisha Zhang
## finished January 23, 2024

DO NO FORK THIS REPOSITORY, clone it directly to your computer.

This project implements the game of Breakout with multiple levels.

### Timeline

 * Start Date: January 13, 2024

 * Finish Date: January 23, 2024

 * Hours Spent: (including time spend trying to figure out how javafx works) ~45 hours


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
   * ```SPACE```: start/pause game, launches the ball at the start of each level
   * ```LEFT```/```RIGHT```: move the paddle to the left/right
   * _no mouse input_

 * Cheat keys:
   * ```L```: adds one life to current level
   * ```R```: resets the ball and paddle to initial position
   * ```C```: clears current level and goes to the next level
   * ```S```: slows doesn the ball(s)
   * ```P```: skips all levels and wins the game
   * ```1``` - ```9```: goes to the corresponding level; goes to the last level if number input is greater than the number of existing levels

### Notes/Assumptions

 * Assumptions or Simplifications:
   * The maximum columns of bricks is 9 for every level (unless you change ```BRICK_WIDTH```)
   * Game window is set to be not resizeable

 * Known Bugs:
   * If the ball comes in contact with the paddle at one specific angle (almost horizontally), it might get stuck inside the paddle and bounce up instead of falling off the screen

 * Features implemented: 
   * 3 Levels
   * 3 Different brick types 
     * multi-hit bricks
     * cross-bomb bricks
     * power-up bricks
   * 3 power-ups (randomly generated on specified bricks)
     * speeding up the ball
     * adding a new ball
     * lengthening the paddle
   * 6 cheat-keys
     * increase life
     * skip current level
     * jump to a specific level
     * win the game
     * slow down the ball
     * resets paddle and ball
   * 1 paddle behavior
     * ball bouncing differently on different parts of the paddle

 * Features unimplemented:
   * Paddle "catching" the ball 

 * Noteworthy Features:
   * The color of the bricks indicate the hits left to clear it. The lighter the brick color is, the less hit it needs. When a block with multiple hits gets hit, it changes into the color of the next lower hit count.


### Assignment Impressions
I really enjoyed the assignment. I think a little game on a graphical interface is a great way to get started to learn to design projects because it's not boring and it's easy to visualize the changes and edits in the code. I also like how it's not technically challenging so that we could focus more on the designing aspect.

I learned a lot through it, and I found out a lot of things that I still need to learn and improve on. I was really confused on where to start in the beginning, and I thought the second lecture really helped me figure out what parts/classes/methods I need for this project. 