import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private static final int DEFAULT_PAWN_NUMBER = 2;

    private Game game;
    private String playerId;
    private List<BuilderPawn> builderPawnList;
    private boolean isWinner;

    public Player(Game game, String playerId) {
        this.game = game;
        this.playerId = playerId;
        initBuilderPawns();
    }


    /**
     *
     * @param pawnNumber
     * @return
     */
    public String generatePawnId(String pawnNumber) {
        return playerId + "'s" + pawnNumber;
    }

    public BuilderPawn getBuilderPawn(int pawnNumber) {
        return getBuilderPawn(generatePawnId(String.valueOf(pawnNumber)));
    }

    public BuilderPawn getBuilderPawn(String pawnId) {
        for (BuilderPawn builderPawn: builderPawnList) {
            if (builderPawn.getPawnId().equalsIgnoreCase(pawnId)) {
                return builderPawn;
            }
        }

        return null;
    }

    public void declareWinner() {
        this.setWinner(true);
        game.setWinner(this);
        game.setGameEnd(true);
    }

    private void initBuilderPawns() {
        List<BuilderPawn> builderPawnList = new ArrayList<>();
        for (int i = 0; i < DEFAULT_PAWN_NUMBER; i ++) {
            String pawnId = generatePawnId(String.valueOf(i));
            builderPawnList.add(new BuilderPawn(pawnId, this));
        }

        this.builderPawnList = builderPawnList;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public List<BuilderPawn> getBuilderPawnList() {
        return builderPawnList;
    }

    public void setBuilderPawnList(List<BuilderPawn> builderPawnList) {
        this.builderPawnList = builderPawnList;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}
