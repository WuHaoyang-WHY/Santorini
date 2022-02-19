import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int DEFAULT_PAWN_NUMBER = 2;

    private Game game;
    private String playerId;
    private List<BuilderPawn> builderPawnList;

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

    public BuilderPawn getBuilderPawn(String pawnId) {
        for (BuilderPawn builderPawn: builderPawnList) {
            if (builderPawn.getPawnId().equalsIgnoreCase(pawnId)) {
                return builderPawn;
            }
        }

        return null;
    }

    private void initBuilderPawns() {
        List<BuilderPawn> builderPawnList = new ArrayList<>();
        for (int i = 0; i < DEFAULT_PAWN_NUMBER; i ++) {
            String pawnId = generatePawnId(String.valueOf(i));
            builderPawnList.add(new BuilderPawn(pawnId));
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
}
