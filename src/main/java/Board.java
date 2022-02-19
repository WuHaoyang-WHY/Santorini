import java.util.List;

public class Board {

    private Game game;
    private Space[][] spacesBoard;

    /**
     * This exists for possible extension of board length
     */
    private int boardLength;

    public Board(Game game, int boardLength) {
        // init spaces
        this.game = game;
        this.boardLength = boardLength;
        this.spacesBoard = initSpaces();
    }

    private Space[][] initSpaces() {
        Space[][] spaces = new Space[boardLength][boardLength];
        for (int i = 0; i < boardLength; i ++) {
            for (int j = 0; j < boardLength; j ++) {
                Space space = new Space(i, j);
                spaces[i][j] = space;
            }
        }

        return spaces;
    }

    /**
     * Find the certain space in board according to x and y coordinate
     * @param x row
     * @param y column
     * @return  target space
     * @throws IllegalArgumentException if the coordinate is invalid
     */
    public Space getSpace(int x, int y) {
        if (!isCoordinateValid(x, y)) {
            throw new IllegalArgumentException();
        }
        return spacesBoard[x][y];
    }

    /**
     * A coordinate is valid when x and y lies within the board length
     * @param x row
     * @param y column
     * @return  true if coordinate is valid
     */
    public boolean isCoordinateValid(int x, int y) {
        return x >= 0 && x < boardLength && y >= 0 && y < boardLength;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public int getBoardLength() {
        return boardLength;
    }

    public void setBoardLength(int boardLength) {
        this.boardLength = boardLength;
    }

    public Space[][] getSpacesBoard() {
        return spacesBoard;
    }

    public void setSpacesBoard(Space[][] spacesBoard) {
        this.spacesBoard = spacesBoard;
    }
}
