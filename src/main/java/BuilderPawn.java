import java.util.List;

public class BuilderPawn {

    private String pawnId;
    private Space currentSpace;

    public BuilderPawn(String pawnId) {
        this.pawnId = pawnId;
    }

    public BuilderPawn(String pawnId, Space currentSpace) {
        this.pawnId = pawnId;
        this.currentSpace = currentSpace;
    }

    public List<Space> getMovableSpaces(Board board) {
        return null;
    }

    protected boolean isSpaceAdjacent(Space space) {
        return false;
    }

    protected boolean isSpaceHeightLessOrEqualThanOne(Space space) {
        return false;
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
        return isSpaceAdjacent(space) && isSpaceHeightLessOrEqualThanOne(space) &&
                !space.hasDome() && !space.hasPawn();
    }

    /**
     *
     * @param space
     * @return
     */
    public boolean moveTo(Space space) {
        return false;
    }

    public List<Space> getBuildableSpaces(Board board) {
        return null;
    }

    /**
     * if pawn can build on the space, then:
     *  1. target space is adjacent
     *  2. target space has no dome
     *  3. target space has no pawn
     *
     * @param space target space to build
     * @return  true, if we can build on the space
     */
    public boolean canBuildOn(Space space) {
        return isSpaceAdjacent(space) && !space.hasPawn() && !space.hasDome();
    }

    public boolean buildOn(Space space) {
        return false;
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
