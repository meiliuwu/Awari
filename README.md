# Awari - Board Game

### Alpha-beta Algorithm - Minimax
The Alpha-Beta pruning algorithm is implemented to play a two-player game called Awari.

### RULES
Awari is an Ashanti abstract strategy game in the Mancala family of board games (pit and pebble games) played worldwide. Here we will use slightly modified Abapa rules set out by Awari International for tournaments. They are as follows:

__The objective__ is to win stones by placing your last stone being moved into a pit of your opponent that already contains one or two stones, making the final count two or three, and then capturing and placing these stones into your storehouse.

### Board and initial setup
The board is comprised of twelve pits. Each side belongs to one player. The numbers in each pit represents the number of stones in that pit.

The direction of play is __counterclockwise__, moving left to right around the board in a circle. __Each player has six pits__ on one side of the board and a storehouse to their right, which is for storing stones won during the game. __To start the game__, place a constant number (usually 4) stones into each player’s pits, and none in the storehouse.

__Rule 1: MOVING__ On their turn, the player chooses any pit on their side, removes all the stones in that pit, and, starting at the next pit to the right moving counterclockwise, puts one stone into each pit that comes next without skipping any pits, except the pit moved from and the storehouses.

__Rule 2: WINNING STONES IN ONE PIT__ If a player places their last stone into a pit on the opponent’s side that makes the new count in that pit either 2 or 3 stones, the player wins these stones and places them in their storehouse.

__Rule 3: WINNING STONES IN MULTIPLE PITS__ If the last stone does capture the pit’s stones, then the previous pit is checked. If that pit, too, has a count of 2 or 3 stones, then those stones are also placed into the current player’s storehouse, and the pit previous to it is checked next. This capturing continues until a checked pit cannot be captured, or it resides outside your opponent’s set of pits.
*__Important Note__*: No capture is possible if it would leave an opponent with no stones in any of their pits. That is, you cannot entirely wipe-out an opponent in one move (known as a grand-slam). The move is allowed, but no capture will occur. 

__Rule 4: SKIPPING PITS__ If a player chooses a pit containing 12 or more stones, the player will skip the pit they started from and continue to the next pit.

__Rule 5: MANDATORY MOVE__ If any player has six empty pits, the next player must move at least 1 stone into the pits of the empty player if they have any pits with enough stones to reach an empty pit. If not, the game ends and the remainder of the stones are forfeit. The player with the largest number of stones in their storehouse is the winner.
The game also ends when, at any time, a player has captured a majority of the stones, which in the standard game is 25 of the 48 total stones.

### Programming highlights
__studentAI.java file.__ Assuming that you are player 1. This studentAI.java file will extend the abstract Player class. Here the abstract methods defined in Player.java are coded. There are three things required for the implementation:
1. Minimax search with alpha-beta pruning
2. Cut-off search at a fixed depth limit (time-efficiency)
3. A static board evaluation (SBE) function

