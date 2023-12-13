package breakout.configs;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class Settings {
    private static Settings instance;
    /**
     * Retrieve the singleton instance of the Settings class.
     *
     * @return instance of Settings
     */
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }

        return instance;
    }

    private Settings() {}


    // Application
    public final StageStyle appWindowStyle = StageStyle.UNDECORATED;
    public final String appTitle = null;
    public final int appWidth = 1080, appHeight = 720;
    public final boolean appResizable = false, appVisible = true;
    public final boolean appFocusTraversable = true;
    public final Cursor appCursor = Cursor.DEFAULT;

    // Game and Engine
    public final int engineFps = 120;
    public final double engineGravity = 400.0;

    // layouts
    public final int canvasExtraSize = 40;
    public final int canvasWidth = appWidth, canvasHeight = appHeight;

    public int maxLevel = 20;
    public Color levelColor = Color.GHOSTWHITE;
    public final double levelX = canvasWidth - 30.0, levelY = canvasHeight - 30.0;
    public final int levelShowSize = 40, levelHideSize = 24;

    // Doodads
    public final boolean enableScreenShake = true;
    public final int targetScreenShakeCount = 10;
    public final int foregroundDistance = 0;
    public final int frontMiddlegroundDistance = 25;
    public final int middlegroundDistance = 50;
    public final int backMiddlegroundDistance = 75;
    public final int backgroundDistance = 100;

    public final int backgroundBlur = 30;
    public final double parallaxSpeed = 80.0;
    public final double foregroundSpeed = parallaxSpeed + 1;
    public final double frontMiddlegroundSpeed = parallaxSpeed / frontMiddlegroundDistance + 1;
    public final double middlegroundSpeed = parallaxSpeed / middlegroundDistance + 1;
    public final double backMiddlegroundSpeed = parallaxSpeed / backMiddlegroundDistance + 1;
    public final double backgroundSpeed = parallaxSpeed / backgroundDistance + 1;

    // Player
    public final int playerWidth = 120, playerHeight = 20, playerArc = 8;
    public final double playerX = appWidth / 2.0 - playerWidth / 2.0, playerY = (appHeight - 60.0) - playerHeight / 2.0;
    public final Color playerColor = Color.LIGHTGRAY;
    public final int playerInitialLife = 20;
    public final Color playerLifeColor = Color.LIGHTCORAL;

    public final double playerResizeVelocity = 4.0;
    public final int playerResizedDuration = 400;
    public final double playerMoveVelocity = 600;
    public final double playerKnockbackIntensity = 20;

    public final double playerScoreShowBlur = 10.0, playerScoreHideBlur = 40.0;
    public final double playerLifeX = canvasWidth - 30.0, playerLifeY = 30.0;
    public final int playerLifeCountShowSize = 40, playerLifeCountHideSize = 24;
    public final int playerLifeLostScore = -50;

    public final double cannonY = canvasHeight - 60.0;
    public final int cannonWidth = 30, cannonHeight = 20, cannonArc = 8;
    public final Color cannonColor = Color.DARKGRAY;

    // Orb
    public final int orbRadius = 14;
    public final double orbY = ((cannonY - cannonHeight / 2.0) - orbRadius / 2.0) - 6.0;
    public final double orbVelocity = 700.0; // per frame
    public final Color orbColor = Color.DARKGRAY;
    public final int orbTrailPiecesNum = 4;
    public final Color[] orbTrailColors = new Color[] {Color.BLACK, Color.DARKGRAY, Color.LIGHTGRAY};

    // Brick
    public final int brickWidth = 34, brickHeight = 24;
    public final int brickArc = 8;
    public final int brickMaxPossibleHealth = 3;
    public final int brickCollectibleIndicatorSize = 4; // the border indicator for its respective collectible type
    public final int brickHitScore = 10, brickDestroyedScore = 100;
    public final int brickExplosionPiecesNum = 10;
    public final int brickExplosionPiecesArc = brickArc - 4;

    public final int bricksPerRow = 14, bricksPerCol = 6;
    public final int brickWidthGap = (appWidth - (brickWidth * bricksPerRow)) / (bricksPerRow + 1);
    public final int bricksGapAboveGround = 200; // gap between bricks and ground
    public final int brickHeightGap = (appHeight - bricksGapAboveGround - (brickHeight * bricksPerCol)) / (bricksPerCol + 1);

    public final double brickComboDuration = 5.0; // in seconds
    public final double brickComboX = 62.0, brickComboY = 62.0;
    public final int brickComboHideSize = 24, brickComboShowSize = 40;

    public final int collectibleWidth = 20, collectibleHeight = 20;
    public final double collectibleMaxVY = 100.0;
    public int collectibleSpinDurationPerCycle = 800; // in milliseconds
}
