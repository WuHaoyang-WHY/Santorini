import java.util.ArrayList;
import java.util.List;

public class BuilderPawn {

    private String pawnId;
    private Space currentSpace;
    private Player player;

    public BuilderPawn(String pawnId, Player player) {
        this.pawnId = pawnId;
        this.player = player;
    }

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

    /**
     * if pawn can move to a space, it means at least:
     *  1. adjacent
     *  2. target height - curr height <= 1
     *  3. target space has no pawn
     *  4. target space has no dome
     *
     * @param space
     * @return
     */
    public boolean canMoveTo(Space space) {
        return isSpaceAdjacent(space) && isSpaceHeightDiffLessOrEqualThanOne(space) &&
                !space.hasDome() && !space.hasPawn();
    }

    /**
     *
     * @param space
     * @return
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
        return isSpaceAdjacent(space) && !space.hasPawn() && !space.hasDome() && space.getHeight() < 3;
    }

    public boolean buildOn(Space space) {
        if(!canBuildOn(space)) {
            return false;
        }
        return space.addHeight();
    }

    protected boolean hasWonGame(Space oldSpace) {
        return currentSpace.getHeight() == 3 && oldSpace.getHeight() < 3;
    }

    /**
     * Place Pawn at initial stage
     * @param board
     * @param space
     * @return
     */
    public boolean initPlacePawn(Board board, Space space) {
        if (!space.hasPawn()) {
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
