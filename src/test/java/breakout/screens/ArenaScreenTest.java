package breakout.screens;

import breakout.entities.enemies.bricks.Brick;
import breakout.entities.enemies.bricks.tags.BrickType;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the initialization of the ArenaScreen.
 * Ensures that all necessary components are properly initialized and not null.
 */
public class ArenaScreenTest {

    private ArenaScreen arenaScreen;

    @Before
    public void setUp() {
        // Assuming Stage can be instantiated or mocked
        Stage mockStage = new Stage();
        arenaScreen = new ArenaScreen(mockStage);
    }

    @Test
    public void testInitialization() {
        assertNotNull("Orbs should not be null after initialization", arenaScreen.getOrbs());
        assertNotNull("Bricks should not be null after initialization", arenaScreen.getBricks());
        // Add more assertions for other components
    }

    @Test
    public void testPaddleMovement() {
        double initialX = arenaScreen.getPaddle().getX();
        arenaScreen.getPaddle().moveRight(1, 700);
        assertTrue("Paddle should move to the right", arenaScreen.getPaddle().getX() > initialX);
    }

    /**
     * Tests the loadBackground method in ArenaScreen.
     * Ensures that the background is loaded correctly.
     */
    @Test
    public void testLoadBackground() {
        arenaScreen.loadBackground();
        assertNotNull("Background should be loaded", arenaScreen.getBackground());
    }

    /**
     * Tests the loadOrbs method in ArenaScreen.
     * Checks if orbs are created and added to the game.
     */
    @Test
    public void testLoadOrbs() {
        arenaScreen.loadOrbs();
        assertFalse("Orbs collection should not be empty", arenaScreen.getOrbs().isEmpty());
    }

    /**
     * Tests the loadNewBricks method in ArenaScreen.
     * Verifies that new bricks are loaded correctly at the start of the game.
     */
    @Test
    public void testLoadNewBricks() {
        Brick[][] bricks = arenaScreen.loadNewBricks();
        assertNotNull("Bricks array should not be null", bricks);
        assertTrue("Bricks should be initialized", bricks.length > 0 && bricks[0].length > 0);
    }

    /**
     * Tests the calculateBrickRarityWeights method.
     * Ensures that the rarity weights for bricks are calculated correctly.
     */
    @Test
    public void testCalculateBrickRarityWeights() {
        BrickType[] types = BrickType.values();
        int[] weights = arenaScreen.calculateBrickRarityWeights(types);
        assertNotNull("Weights array should not be null", weights);
        assertTrue("Weights array should have the same length as types", weights.length == types.length);
    }

    /**
     * Tests the getRandomBrickWeight method in ArenaScreen.
     * Checks if a valid brick type index is selected based on the weights.
     */
    @Test
    public void testGetRandomBrickWeight() {
        int[] weights = new int[]{1, 2, 3}; // Example weights
        int index = arenaScreen.getRandomBrickWeight(weights);
        assertTrue("Should return a valid index within weights array", index >= 0 && index < weights.length);
    }

}
