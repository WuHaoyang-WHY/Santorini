import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    @Test
    void testAddHeightSuccess() {
        Space space = new Space(0, 0);
        assertTrue(space.addHeight());
        assertEquals(space.getHeight(), 1);
        assertTrue(space.addHeight());
        assertEquals(space.getHeight(), 2);
        assertTrue(space.addHeight());
        assertEquals(space.getHeight(), 3);
    }

    @Test
    void testAddHeightToLevel3Fail() {
        Space space = new Space(0, 0, 3);
        assertFalse(space.addHeight());
        assertEquals(space.getHeight(), 3);
    }

    @Test
    void testAddHeightToDomeFail() {
        Space space = new Space(0, 0, 3, true);
        assertFalse(space.addHeight());
        assertEquals(space.getHeight(), 3);

        space = new Space(0, 0, 2, true);
        assertFalse(space.addHeight());
        assertEquals(space.getHeight(), 2);
    }
}