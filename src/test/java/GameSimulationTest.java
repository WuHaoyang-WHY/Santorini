import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameSimulationTest {

    Game game;
    Board board;
    Player player1;
    Player player2;
    BuilderPawn player1Pawn1, player1Pawn2;
    BuilderPawn player2Pawn1, player2Pawn2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        game = new Game();
        board = game.getBoard();
        player1 = game.getPlayerList().get(0);
        player2 = game.getPlayerList().get(1);
        player1Pawn1 = player1.getBuilderPawn(0);
        player1Pawn2 = player1.getBuilderPawn(1);
        player2Pawn1 = player2.getBuilderPawn(0);
        player2Pawn2 = player2.getBuilderPawn(1);

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void testWholeGame() {
        initStage();
        // p1 go first
        // try to move pawn 1, will fail
        boolean movePawn1 = player1Pawn1.moveTo(board.getSpace(1, 1));
        assertFalse(movePawn1);
        // p1p2 move to 0, 2
        assertTrue(player1Pawn2.moveTo(board.getSpace(0, 2)));
        // p1 build on 0, 1
        assertTrue(player1Pawn2.buildOn(board.getSpace(0, 1)));
        assertEquals(board.getSpace(0, 1).getHeight(), 1);

        // p2 move and build
        // p2p2 move to 2, 1
        assertTrue(player2Pawn2.moveTo(board.getSpace(2, 1)));
        // p2 build on 1, 0
        assertTrue(player2Pawn1.buildOn(board.getSpace(1, 0)));

        // p1p1 move up to 0, 1
        assertTrue(player1Pawn1.moveTo(board.getSpace(0, 1)));
        // p1 build on 1, 2
        assertTrue(player1Pawn1.buildOn(board.getSpace(1, 2)));

        // p2p1 move to 1, 0
        assertTrue(player2Pawn1.moveTo(board.getSpace(1, 0)));
        // p2 build on 2, 0
        assertTrue(player2Pawn1.buildOn(board.getSpace(2, 0)));

        // p1p2 move to 1, 1
        assertTrue(player1Pawn2.moveTo(board.getSpace(1, 1)));
        // p1 build on 1, 2
        assertTrue(player1Pawn2.buildOn(board.getSpace(1, 2)));

        // p21 move to 2, 0
        assertTrue(player2Pawn1.moveTo(board.getSpace(2, 0)));
        // p2 build on 1, 0
        assertTrue(player2Pawn1.buildOn(board.getSpace(1, 0)));

        // p11 move to 1, 2
        assertTrue(player1Pawn1.moveTo(board.getSpace(1, 2)));
        // p1 build on 0, 1
        assertTrue(player1Pawn1.buildOn(board.getSpace(0, 1)));

        // p21 move to 1, 0
        assertTrue(player2Pawn1.moveTo(board.getSpace(1, 0)));
        // p2 build on 2, 0
        assertTrue(player2Pawn1.buildOn(board.getSpace(2, 0)));

        // p12 move to 0, 2
        assertTrue(player1Pawn2.moveTo(board.getSpace(0, 2)));
        // p1 build on 0, 1
        assertTrue(player1Pawn2.buildOn(board.getSpace(0, 1)));

        // p22 move to 1, 1
        assertTrue(player2Pawn2.moveTo(board.getSpace(1, 1)));
        // p21 build dome on 0, 1
        assertTrue(player2Pawn1.putDomeOn(board.getSpace(0, 1)));

        // p12 move to 0, 3
        assertTrue(player1Pawn2.moveTo(board.getSpace(0, 3)));
        // p12 build on 0, 2
        assertTrue(player1Pawn2.buildOn(board.getSpace(0, 2)));

        // p22 move to 2, 1
        assertTrue(player2Pawn2.moveTo(board.getSpace(2, 1)));
        // p22 build on 2, 0
        assertTrue(player2Pawn2.buildOn(board.getSpace(2, 0)));

        // p12 move to 1, 3
        assertTrue(player1Pawn2.moveTo(board.getSpace(1, 3)));
        // p12 build on 0, 2
        assertTrue(player1Pawn2.buildOn(board.getSpace(0, 2)));

        // p21 move to 2, 0 and win the game
        assertTrue(player2Pawn1.moveTo(board.getSpace(2, 0)));

        // end game
        assertTrue(player2.isWinner());
        assertEquals(game.getWinner(), player2);
        assertTrue(game.isGameEnd());

    }

    private void initStage() {
        player1Pawn1.initPlacePawn(board.getSpace(0, 0));
        player1Pawn2.initPlacePawn(board.getSpace(0, 1));
        player2Pawn1.initPlacePawn(board.getSpace(1, 1));
        player2Pawn2.initPlacePawn(board.getSpace(1, 0));
    }

}
