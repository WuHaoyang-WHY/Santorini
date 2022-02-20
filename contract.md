**Operation: Move** 
- moveTo(targetSpace: Space)

**Preconditions:**
- Game started and all players have finished initially placing their builder pawns.

**Postconditions:**
- The builder pawn instance's former association with old space broke.
- The builder pawn was associated with current new target space.
- If the move can declare a winner (e.g. move up to 3rd level of tower):
  - The corresponding _player.isWinner_ became TRUE
  - _game.isGameEnd_ became TRUE
