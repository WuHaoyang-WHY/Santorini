import java.util.ArrayList;
import java.util.List;

public class BuilderPawn {

    private static final int MAX_TOWER_HEIGHT = 3;
    private String pawnId;
    private Space currentSpace;
    private Player player;

    public BuilderPawn(String pawnId, Player player) {
        this.pawnId = pawnId;
        this.player = player;
    }

    /**
     * Return all the spaces that the pawn can move to
     * @param board game board
     * @return  list of move-able spaces
     */
    public List<Space> getMovableSpaces(Board board) {
        List<Space> spaceList = new ArrayList<>();
        for (int i = 0; i < board.getBoardLength(); i ++) {
            for (int j = 0; j < board.getBoardLength(); j ++) {
                Space space = board.getSpace(i, j);
                if (canMoveTo(space)) {
                    spaceList.add(space);
                }
            }
        }

        return spaceList;
    }

    protected boolean isSpaceAdjacent(Space space) {
        int currX = currentSpace.getxCoordinate(), currY = currentSpace.getyCoordinate();
        int targetX = space.getxCoordinate(), targetY = space.getyCoordinate();

        return !(currX == targetX && currY == targetY)
                && (Math.abs(currX - targetX) <= 1 && Math.abs(currY - targetY) <= 1);
    }

    protected boolean isSpaceHeightDiffLessOrEqualThanOne(Space space) {
        return (space.getHeight() - currentSpace.getHeight()) <= 1;
    }

    protected boolean isAdjacentAndUnoccupied(Space space) {
        return isSpaceAdjacent(space) && !space.hasDome() && !space.hasPawn();
    }

    /**
     * if pawn can move to a space, it means at least:
     *  1. adjacent
     *  2. target height - curr height <= 1
     *  3. target space has no pawn
     *  4. target space has no dome
     *
     * @param space Target space to go to
     * @return  true if pawn can move to the target space
     */
    public boolean canMoveTo(Space space) {
        return isAdjacentAndUnoccupied(space) && isSpaceHeightDiffLessOrEqualThanOne(space);

    }

    /**
     * Move the pawn to target space
     * @param space target space to go to
     * @return  true if the move succeed, else false
     */
    public boolean moveTo(Space space) {
        if (!canMoveTo(space)) {
            return false;
        }
        Space oldSpace = currentSpace;
        space.setPawn(this);
        currentSpace.setPawn(null);

        this.setCurrentSpace(space);
        // triggers winning condition and end game
        if (hasWonGame(oldSpace)) {
            player.declareWinner();
        }

        return true;
    }

    /**
     * Get all the spaces that could be built upon
     * @param board game board
     * @return  list of spaces that pawn can build upon
     */
    public List<Space> getBuildableSpaces(Board board) {
        List<Space> spaceList = new ArrayList<>();
        for (int i = 0; i < board.getBoardLength(); i ++) {
            for (int j = 0; j < board.getBoardLength(); j ++) {
                Space space = board.getSpace(i, j);
                if (canBuildOn(space)) {
                    spaceList.add(space);
                }
            }
        }

        return spaceList;
    }

    /**
     * if pawn can build on the space, then:
     *  1. target space is adjacent
     *  2. target space has no dome
     *  3. target space has no pawn
     *  4. target space height < 3
     *
     * @param space target space to build
     * @return  true, if we can build on the space
     */
    public boolean canBuildOn(Space space) {
        return isAdjacentAndUnoccupied(space) && space.getHeight() < MAX_TOWER_HEIGHT;
    }

    /**
     * Build on the target space
     * @param space target space
     * @return  true if build succeed
     */
    public boolean buildOn(Space space) {
        if(!canBuildOn(space)) {
            return false;
        }
        return space.addHeight();
    }

    /**
     * If the pawn could put dome on the space, it should be unoccupied and adjacent, plus height is 3
     * @param space target space
     * @return  true if the pawn can put dome on the space
     */
    public boolean canPutDomeOn(Space space) {
        return isAdjacentAndUnoccupied(space) && space.getHeight() == MAX_TOWER_HEIGHT;
    }

    /**
     * Put dome on the space
     * @param space target space
     * @return  true if putting dome succeed
     */
    public boolean putDomeOn(Space space) {
        if (!canPutDomeOn(space)) {
            return false;
        }

        space.setHasDome(true);
        return true;
    }

    protected boolean hasWonGame(Space oldSpace) {
        return currentSpace.getHeight() == MAX_TOWER_HEIGHT && oldSpace.getHeight() < MAX_TOWER_HEIGHT;
    }

    /**
     * Place Pawn at initial stage
     *
     * @param space target space
     * @return  true if initial placement succeed
     */
    public boolean initPlacePawn(Space space) {
        if (space.hasPawn()) {
            return false;
        }
        space.setPawn(this);
        this.setCurrentSpace(space);

        return true;
    }

    public String getPawnId() {
        return pawnId;
    }

    public void setPawnId(String pawnId) {
        this.pawnId = pawnId;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }
}
