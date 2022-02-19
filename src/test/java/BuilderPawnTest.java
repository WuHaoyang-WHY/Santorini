import static org.junit.jupiter.api.Assertions.*;

class BuilderPawnTest {
    Game game;
    Board board;
    Player player;
    BuilderPawn pawn;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        game = new Game();
        board = game.getBoard();
        player =  game.getPlayerList().get(0);
        pawn = player.getBuilderPawn(0);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void canMoveToAdjacentTest() {
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        Space space3 = board.getSpace(0, 1);
        Space space4 = board.getSpace(2, 2);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);

        assertFalse(pawn.canMoveTo(space1));
        assertTrue(pawn.canMoveTo(space2));
        assertTrue(pawn.canMoveTo(space3));
        assertFalse(pawn.canMoveTo(space4));
    }

    @org.junit.jupiter.api.Test
    void canMoveToHeightTest() {

        Space space1 = board.getSpace(0, 0);
        space1.setHeight(1);
        Space space2 = board.getSpace(1, 1);
        space2.setHeight(2);
        Space space3 = board.getSpace(0, 1);
        space3.setHeight(3);
        Space space4 = board.getSpace(1, 0);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);

        assertTrue(pawn.canMoveTo(space2));
        assertFalse(pawn.canMoveTo(space3));
        assertTrue(pawn.canMoveTo(space4));
    }

    @org.junit.jupiter.api.Test
    void canMoveToOccupiedTest() {
        BuilderPawn pawn2 = game.getPlayerList().get(0).getBuilderPawn(1);
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);

        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);
        pawn2.setCurrentSpace(space2);
        space2.setPawn(pawn2);

        assertFalse(pawn.canMoveTo(space2));
        assertFalse(pawn2.canMoveTo(space1));

        Space space3 = board.getSpace(0, 1);
        space3.setHasDome(true);
        assertFalse(pawn.canMoveTo(space3));
        assertFalse(pawn2.canMoveTo(space3));
    }

    @org.junit.jupiter.api.Test
    void moveToNormalCaseTest() {
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);

        assertFalse(pawn.moveTo(space1));
        assertTrue(pawn.moveTo(space2));
        assertNull(space1.getPawn());
        assertEquals(space2.getPawn(), pawn);
    }

    @org.junit.jupiter.api.Test
    void moveToWinGameTest() {
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);
        space1.setHeight(2);
        space2.setHeight(3);

        assertTrue(pawn.moveTo(space2));
        assertNull(space1.getPawn());
        assertEquals(space2.getPawn(), pawn);

        // has won game, end game
        assertTrue(player.isWinner());
        assertEquals(game.getWinner(), player);
        assertTrue(game.isGameEnd());
    }


    @org.junit.jupiter.api.Test
    void canBuildOnTest() {
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        Space space3 = board.getSpace(0, 1);
        Space space4 = board.getSpace(1, 0);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);

        space2.setHeight(3);
        space3.setHasDome(true);
        assertFalse(pawn.canBuildOn(space1));
        assertFalse(pawn.canBuildOn(space2));
        assertFalse(pawn.canBuildOn(space3));
        assertTrue(pawn.canBuildOn(space4));
    }

    @org.junit.jupiter.api.Test
    void buildOnTest() {
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        Space space3 = board.getSpace(0, 1);
        Space space4 = board.getSpace(1, 0);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);
        space2.setHeight(3);
        space3.setHasDome(true);

        assertFalse(pawn.buildOn(space1));
        assertFalse(pawn.buildOn(space2));
        assertFalse(pawn.buildOn(space3));
        assertTrue(pawn.buildOn(space4));
        assertEquals(space4.getHeight(), 1);
    }

    @org.junit.jupiter.api.Test
    void canPutDomeOnTest() {
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        Space space3 = board.getSpace(0, 1);
        Space space4 = board.getSpace(1, 0);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);

        space2.setHeight(3);
        space3.setHasDome(true);
        assertFalse(pawn.canPutDomeOn(space1));
        assertTrue(pawn.canPutDomeOn(space2));
        assertFalse(pawn.canPutDomeOn(space3));
        assertFalse(pawn.canPutDomeOn(space4));
    }

    @org.junit.jupiter.api.Test
    void putDomeOnTest() {
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        Space space3 = board.getSpace(0, 1);
        Space space4 = board.getSpace(1, 0);
        pawn.setCurrentSpace(space1);
        space1.setPawn(pawn);

        space2.setHeight(3);
        space3.setHasDome(true);
        assertFalse(pawn.putDomeOn(space1));
        assertTrue(pawn.putDomeOn(space2));
        assertFalse(pawn.putDomeOn(space3));
        assertFalse(pawn.putDomeOn(space4));

        assertTrue(space2.hasDome());
    }


    @org.junit.jupiter.api.Test
    void initPlacePawnTest() {
        BuilderPawn pawn2 = game.getPlayerList().get(0).getBuilderPawn(1);
        Space space1 = board.getSpace(0, 0);
        Space space2 = board.getSpace(1, 1);
        assertTrue(pawn.initPlacePawn(space1));
        assertEquals(space1.getPawn(), pawn);
        assertEquals(pawn.getCurrentSpace(), space1);

        assertFalse(pawn2.initPlacePawn(space1));
        assertEquals(space1.getPawn(), pawn);
        assertEquals(pawn.getCurrentSpace(), space1);

        assertTrue(pawn2.initPlacePawn(space2));
        assertEquals(space2.getPawn(), pawn2);
        assertEquals(pawn2.getCurrentSpace(), space2);
    }
}