import java.util.ArrayList;
import java.util.List;

public class Game {

    /**
     * Default length of board is 5 * 5
     */
    private static final int DEFAULT_BOARD_LENGTH = 5;

    /**
     * Default player number
     */
    private static final int DEFAULT_PLAYER_NUMBER = 2;

    private List<Player> playerList;
    private Board board;
    private int playerNumber;
    private int boardLength;

    /**
     * Set to true by the winner when the game has ended.
     */
    private boolean gameEnd;
    private Player winner;

    public Game() {
        this(DEFAULT_PLAYER_NUMBER, DEFAULT_BOARD_LENGTH);
    }

    public Game(int playerNumber) {
        this(playerNumber, DEFAULT_BOARD_LENGTH);
    }

    public Game(int playerNumber, int boardLength) {
        assert playerNumber > 1;
        assert boardLength > 1;
        this.playerNumber = playerNumber;
        this.boardLength = boardLength;
        playerList = new ArrayList<>();
        initPlayers();
        initBoard();
    }

    /**
     * This is the game start process. To be replaced by UI or command line interaction in the future.
     *
     * Prompt players to choose start position for pawns -> start game (move & build) until one player wins.
     *
     */
    public void startGame() {}

    private void initPlayers() {
        for (int i = 0; i < playerNumber; i ++) {
            String playerId = "Player" + i;
            this.playerList.add(new Player(this, playerId));
        }
    }

    private void initBoard() {
        this.board = new Board(this, this.boardLength);
    }


    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getBoardLength() {
        return boardLength;
    }

    public void setBoardLength(int boardLength) {
        this.boardLength = boardLength;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
