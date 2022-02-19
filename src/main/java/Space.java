public class Space {

    private int xCoordinate;
    private int yCoordinate;
    private int height;
    private boolean hasDome;
    private BuilderPawn pawn;

    public Space(int xCoordinate, int yCoordinate) {
        this(xCoordinate, yCoordinate, 0, false);
    }

    public Space(int xCoordinate, int yCoordinate, int height) {
        this(xCoordinate, yCoordinate, height, false);
    }

    public Space(int xCoordinate, int yCoordinate, int height, boolean hasDome) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.hasDome = hasDome;
    }

    public boolean hasPawn() {
        return pawn != null;
    }

    public BuilderPawn getPawn() {
        return pawn;
    }

    public void setPawn(BuilderPawn pawn) {
        this.pawn = pawn;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean hasDome() {
        return hasDome;
    }

    public void setHasDome(boolean hasDome) {
        this.hasDome = hasDome;
    }
}
