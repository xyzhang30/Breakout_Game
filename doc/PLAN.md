# Breakout Plan
### Alisha Zhang

#### Examples

You need to put blank lines to write some text

in separate paragraphs.


Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.


You can also make lists:
* Bullets are made with asterisks
1. You can order things with numbers.


You can put links in like this: [Duke CompSci](https://www.cs.duke.edu)



## Interesting Breakout Variants

 * Idea #1: Bricks n Balls: this is probably a biased answer, but I really like bricks n balls and I used to have the exact same app shown on the youtube video on my phone. I like this variant because, especially for harder levels, the player has to really think about and "plan" which blocks to hit first, and aim for a correct angle that maximizes the bounces of the ball.

 * Idea #2: Brick Breaker Hero: I think this one is interesting because it puts the relatively simple game of breaking bricks with a ball into a context with character and plot setting, with more visual varieties. The boss in each level also add an interesting and attention catching point for the player.


## Paddle Ideas

 * Idea #1: "catching" the ball when it hits the paddle and releasing it when user clicks again on the screen. The user can move the ball with the paddle with it's sticking on it. Triggered by a power up

 * Idea #2: the middle part of the paddle bounces normally, the left and right thirds cause the ball to bounce back in the direction it came


## Block Ideas

 * Idea #1: __Power up blocks__: gives the user a random (or predetermined) power up when hit 

 * Idea #2: __Cross bomb blocks__: clears all the blocks on the vertical and the horizontal line it's on when it's hit

 * Idea #3: __Multi-hit blocks__: blocks have different colors, corresponding to the number of times it has to be hit in order to be cleared: grey/blue = 1, green = 2, yellow/orange = 3, red = 4 


## Power-up Ideas

 * Idea #1: adds 1 ball to clear blocks (as long as there is at least one ball on the screen, if one of the two balls goes off the bottom of the screen, the player does not lose lives)

 * Idea #2: makes the paddle 1.5 times the original length (easier to catch the ball)

 * Idea #3: adds 1 extra life
 
 * Idea #4: speeds up the ball (bad power up)


## Cheat Key Ideas

 * Idea #1: clicking on __L__ increases the current lives by 1

 * Idea #2: clicking on __C__ clears the screen and goes to the next level

 * Idea #3: clicking on __a number (1-9)__ skips current level and goes to the corresponding level, if number pressed is bigger than the highest level, it goes to the highest level.

 * Idea #4: clicking on __S__ slows down the ball (easier for the player to react)

 * Idea #5: clicking on __R__ resets the current level (reset the paddle and the ball to the starting position and restart current level)

 * Idea #6: clicking on __P__ skips all levels and wins the game (show winning screen)

## Level Descriptions

(number means the number of times the block has to be hit, 0 means there's no block at that position)

 * Idea #1

0 0 0 0 0 0 0 0 0

0 4 4 4 2 4 4 4 0

0 3 4 3 2 3 4 3 0

0 2 3 2 1 2 3 2 0 

0 1 2 1 0 1 2 1 0

0 1 1 0 0 0 1 1 0

0 1 0 0 0 0 0 1 0 

 * Idea #2

0 0 0 0 0 0 0 0 0

0 4 3 2 0 3 2 1 0

0 3 1 3 0 2 4 2 0

0 2 3 4 0 1 2 3 0

0 0 0 0 0 0 0 0 0

0 2 1 4 0 1 4 3 0

0 1 3 1 0 4 2 4 0

0 4 1 2 0 3 4 1 0


 * Idea #3

0 0 0 0 0 0 0 0 0

0 0 0 1 1 1 0 0 0

0 0 1 1 2 1 1 0 0

0 1 1 2 3 2 1 1 0

1 1 2 3 4 3 2 1 1

0 1 1 2 3 2 1 1 0

0 0 1 1 2 1 1 0 0

0 0 0 1 1 1 0 0 0


## Class Ideas

 * Idea #1: __Ball class__: has all the information about the ball itself, includes it's x and y coordinates, radius, etc.
   * _getVelocityX()_, _getVelocityY()_, _setVelocityX()_, _setVelocityY()_ methods would be included in this class to get or set the velocity of the ball, mainly used for bouncing off of walls, bricks, and the paddle.

 * Idea #2: __Block class__: has properties of different blocks, used to generate the blocks on the screen after reading in the level block layout from a file
   * _getType()_, _cleared()_ should be methods in this class. Cleared checks for whether this block is hit/is hit enough times to be removed, and getType gets the type of the block, in order to determine how many hits are needed to clear it, and whether to drop a power up when cleared

 * Idea #3: __Paddle class__: includes the paddle
   * _move()_ would be a method in this class to get the 

 * Idea #4: __LevelControl class__: includes the information of the game: current level, current lives, etc.
   * _setLevel()_, _nextLevel()_ and _loseLife()_ would be methods in this class. NextLevel() increases the level number by 1 once the current level is cleared; SetLevel() skips to the level specified in the parameter when a cheatkey is pressed; loseLife() decrease the lives by 1, called when the ball goes off the bottom of the screen.


