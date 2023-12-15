package breakout.screens;

import breakout.container.DependenciesInjectable;
import breakout.core.audio.tags.AudioVolume;
import breakout.core.database.DB;
import breakout.core.database.QueryBuilder;
import breakout.core.effects.Effect;
import breakout.core.effects.Explosion;
import breakout.core.effects.Parallax;
import breakout.entities.enemies.bricks.BrickCollection;
import breakout.entities.enemies.bricks.collectibles.CollectibleBrick;
import breakout.entities.enemies.bricks.collectibles.drops.CollectibleCollection;
import breakout.entities.enemies.bricks.collectibles.drops.LargePaddleCollectible;
import breakout.entities.enemies.bricks.collectibles.drops.LifeCollectible;
import breakout.entities.enemies.bricks.collectibles.drops.MultiOrbCollectible;
import breakout.entities.enemies.bricks.tags.Brick;
import breakout.entities.players.cannons.Cannon;
import breakout.entities.players.paddles.Paddle;
import breakout.entities.players.tags.Score;
import breakout.entities.projectiles.orbs.Orb;
import breakout.entities.projectiles.orbs.OrbCollection;
import breakout.entities.projectiles.orbs.standards.StandardOrb;
import breakout.helpers.AutomatedLog;
import breakout.screens.tags.ShakeMagnitude;
import breakout.screens.views.ArenaView;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Refactored mainly from the Main and GameEngine files
 * @see <a href="https://github.com/kooitt/CourseworkGame/blob/master/src/main/java/brickGame/Main.java">Original Main Class</a>
 * @see <a href="https://github.com/kooitt/CourseworkGame/blob/master/src/main/java/brickGame/GameEngine.java">Original GameEngine Class</a>
 *
 *
 * Scoring system is also made functional in this class due to its ties with the "screen" which handles the entire game field.
 * @see <a href="https://github.com/kooitt/CourseworkGame/tree/master/src/main/java/brickGame">Original Score Class</a>
 *
 */
public class ArenaScreen extends breakout.core.screens.ArenaScreen implements DependenciesInjectable {
    private Parallax background;
    private Shaker shaker;
    private Blurrer blurrer;
    private ShapeDrawer shapeDrawer;

    private OrbCollection orbs;
    private Orb mainOrb;
    private BrickCollection bricks;
    private CollectibleCollection collectibles;
    private Paddle paddle;
    private Cannon cannon;

    private QueryBuilder query;

    private List<Effect> entityEffects = new ArrayList<>();
    private boolean destroyedABrick = false, gainedALife = false, gainedMultiOrb = false;

    private int level = 1;
    private boolean levelUp = false;
    private boolean win = false, lose = false;

    private boolean newGame = false, loadGame = false;

    public ArenaScreen(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    protected void loadTemplate() {
        AutomatedLog.wrapEvent("Loading UI template for arena", () -> {
            view = new ArenaView();
            view.build(stage);

            graphics = view.getGraphics();

            shaker = new Shaker(view);
            blurrer = new Blurrer(view.getRoot());
            shapeDrawer = new ShapeDrawer(graphics);

            orbs = new OrbCollection();
        });
    }

    @Override
    protected void loadAssets() {
        readDB();
        super.loadAssets();
    }

    protected void loadSoundEffects() {
        AutomatedLog.wrapEvent("Loading sound effects for arena", () -> {
            for (String sound: env.getResourceSFXs().keySet()) {
                audios.prepare(sound);
            }
            audios.warmup();
        });
    }

    @Override
    protected void loadProps() {
        AutomatedLog.wrapEvent("Loading props for arena", () -> {
            loadBackground();
        });
    }

    protected void loadBackground() {
        AutomatedLog.wrapEvent("Loading background for arena", () -> {
            Image backgroundImage = imagery.imageBlurBrighten("background-level-1-5", settings.backgroundBlur, -0.2);

            background = new Parallax(backgroundImage, settings.appWidth, settings.appHeight, 100, settings.backgroundSpeed);
        });
    }

    @Override
    protected void updateProps() {
        AutomatedLog.wrapEvent("Updating props for arena", () -> {
            background.update((int) paddle.getX());
            background.draw(graphics);
        });
    }

    @Override
    protected void loadProjectiles() {
        AutomatedLog.wrapEvent("Loading projectile entities for arena", () -> {
            loadOrbs(1);
        });
    }

    protected void loadOrbs(int count) {
        AutomatedLog.wrapEvent("Loading projectile orb for arena", () -> {
            int i = 0;
            while (i < count) {
                Orb orb = orbFactory.createOrbUnloaded(settings.orbY, settings.orbRadius, settings.orbColor);
                orbs.add(orb);

                if (mainOrb == null) {
                    mainOrb = orb;
                }

                i++;
            }
        });
    }

    @Override
    protected void loadEnemies() {
        AutomatedLog.wrapEvent("Loading enemy entities for arena", () -> {
            loadBricks();
            loadCollectibles();
        });
    }

    /**
     * Loads the bricks and their respective collectibles if it has.
     */
    private void loadBricks() {
        AutomatedLog.wrapEvent("Loading enemy bricks for arena", () -> {
            breakout.entities.enemies.bricks.Brick[][] bricks = new breakout.entities.enemies.bricks.Brick[settings.bricksPerCol][settings.bricksPerRow];

            if (newGame) {
                bricks = loadNewBricks();
            } else if (loadGame) {

            }

            this.bricks = new BrickCollection(bricks);
        });
    }

    private void readDB() {
        AutomatedLog.wrapEvent("Reading database for arena", () -> {
            query = (new DB()).access().connect()
                    .createDatabaseIfNotExist("database")
                    .createTableIfNotExist("progresses")
                    .statement();

            newGame = true;
        });
    }

    protected breakout.entities.enemies.bricks.Brick[][] loadNewBricks() {
        breakout.entities.enemies.bricks.Brick[][] bricks = new breakout.entities.enemies.bricks.Brick[settings.bricksPerCol][settings.bricksPerRow];
        Brick[] types = Brick.values();

        for (int row = 0; row < settings.bricksPerCol; row++) {
            for (int col = 0; col < settings.bricksPerRow; col++) {
                int[] weights = calculateBrickRarityWeights(types);

                int rand = getRandomBrickWeight(weights);
                int x = (settings.brickWidth + settings.brickWidthGap) * col + settings.brickWidthGap;
                int y = (settings.brickHeight + settings.brickHeightGap) * row + settings.brickHeightGap;

                breakout.entities.enemies.bricks.Brick brick = switch (types[rand]) {
                    case LARGE_PADDLE -> rectangleCollectibleBrickFactory.createLargePaddle(x, y);
                    case LIFE -> rectangleCollectibleBrickFactory.createLife(x, y);
                    case MULTI_ORB -> rectangleCollectibleBrickFactory.createMultiOrb(x, y);
                    default -> rectangleStandardBrickFactory.createStandard(x, y);
                };

                bricks[row][col] = brick;
            }
        }

        return bricks;
    }

    @Override
    protected void reload() {
        AutomatedLog.wrapEvent("Reloading enemy bricks for the next level", () -> {
            loadEnemies();
        });
    }

    /**
     * Calculates the weights for spawning each brick type based on their rarity and the player's level.
     *
     * This method adjusts the spawning weights such that bricks with higher rarity values are less likely
     * to be spawned. As the player's level increases, the likelihood of spawning rarer bricks increases slightly.
     *
     * @param brickTypes An array of {@code BrickType} enums representing different brick types.
     * @return An array of integers representing the calculated weights for each brick type.
     */
    public int[] calculateBrickRarityWeights(Brick[] brickTypes) {
        int[] weights = new int[brickTypes.length];

        for (int i = 0; i < brickTypes.length; i++) {
            int rarity = brickTypes[i].rarity();

            // Inverting the logic: Higher rarity results in lower weight
            weights[i] = Math.max(1, 10 - rarity + level);
        }

        return weights;
    }

    /**
     * Selects a random brick type index based on the provided weights.
     *
     * This method uses a weighted random selection algorithm to choose an index. Indices with higher weights
     * are more likely to be chosen. The selection is based on the cumulative sum of the weights.
     *
     * @param weights  An array of weights corresponding to each brick type.
     * @return         The index of the selected brick type. If the method fails to select an index, it defaults to 0.
     */
    protected int getRandomBrickWeight(int[] weights) {
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }

        int randomIndex = new Random().nextInt(totalWeight);
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (randomIndex < sum) {
                return i;
            }
        }

        // Fallback in case something goes wrong, return a valid index
        return 0;
    }

    private void loadCollectibles() {
        AutomatedLog.wrapEvent("Loading collectibles in the arena", () -> {
            collectibles = new CollectibleCollection(new ArrayList<>());
        });
    }

    @Override
    protected void loadPlayers() {
        AutomatedLog.wrapEvent("Loading player entities for arena", () -> {
            loadPaddle();
            loadCannon();
        });
    }

    private void loadPaddle() {
        AutomatedLog.wrapEvent("Loading player paddle for arena", () -> {
            paddle = playerFactory.createPaddle(settings.playerX, settings.playerY, settings.playerWidth, settings.playerHeight, settings.playerColor);
        });
    }

    private void loadCannon() {
        AutomatedLog.wrapEvent("Loading player cannon for arena", () -> {
            cannon = playerFactory.createCannon(settings.cannonY, settings.cannonWidth, settings.cannonHeight, settings.cannonColor);
            cannon.randomizePosition(settings.appWidth);

            orbs.each(orb -> {
                orb.setX((cannon.getX() + cannon.getWidth() / 2.0) - settings.orbRadius / 2.0);
            });

            cannon.reload(orbs);
        });
    }

    @Override
    public void updateAssets() {
        AutomatedLog.wrapEvent("Updating assets for arena", () -> {
            if (levelUp) {
                reload();
                levelUp = false;
            }

            clearCanvas();

            updateProps();

            updateGraphics();

            updateEntities();
            updateWinLose();
            updateEffects();
        });
    }

    @Override
    protected void loadListeners() {
        AutomatedLog.wrapEvent("Loading event listeners for arena", () -> {
            view.addEventHandler(KeyEvent.class, (EventHandler<KeyEvent>) e -> {
                KeyCode code = e.getCode();

                switch (code) {
                    case LEFT:
                        if (!cannon.hasAmmo()) {
                            paddle.moveLeft(delta, settings.appWidth);
                        }
                        break;
                    case RIGHT:
                        if (!cannon.hasAmmo()) {
                            paddle.moveRight(delta, settings.appWidth);
                        }
                        break;
                    case SPACE:
                        if (cannon.hasAmmo()) {
                            cannon.fire(mainOrb);
                            cannon.retract();
                            paddle.deploy();
                        }
                        break;
                    case S:
                        // save game

                    case ESCAPE:
                        System.exit(0);
                        break;
                }
            });

            view.addEventHandler(MouseEvent.class, (EventHandler<MouseEvent>) e -> {
                switch (e.getEventType().getName()) {
                    case "MOUSE_MOVED":
                        if (cannon.hasAmmo()) {
                            if (!paddle.walled()) {
                                paddle.wall(settings.appHeight);
                            }
                            cannon.deploy();
                            cannon.aim(e.getX(), e.getY());
                        } else {
                            if (!paddle.deployed()) {
                                paddle.deploy();
                            }
                            paddle.moveInDirection((int) e.getX(), settings.appWidth);
                        }
                        break;
                    case "MOUSE_CLICKED":
                        if (cannon.hasAmmo()) {
                            orbs.each(orb -> {
                                cannon.fire(orb);
                            });
                            cannon.retract();
                            paddle.deploy();
                        }
                        break;
                }
            });

            view.listen();
        });
    }

    private void updateWinLose() {
        AutomatedLog.wrapEvent("Updating win/lose status for arena", () -> {
            if (win) {

            } else if (lose) {

            }
        });
    }

    @Override
    protected void updateGraphics() {
        AutomatedLog.wrapEvent("Updating graphics for arena", () -> {
            graphics.setFill(Color.BLACK);

            updateLifeStatus();
            updateScoreStatus();
            updateOrbStatus();
            if (bricks.getComboCount() > 0) {
                updateComboStatus();
            }
        });
    }

    private void updateLifeStatus() {
        AutomatedLog.wrapEvent("Updating player life status in arena", () -> {
            Text text = new Text("" + paddle.getHealth());
            text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, gainedALife ? settings.playerLifeCountShowSize : settings.playerLifeCountHideSize));
            text.setStyle("-fx-letter-spacing: 5px;");
            text.setFill(settings.playerLifeColor);

            DropShadow glow = new DropShadow();
            glow.setColor(settings.playerLifeColor);
            glow.setSpread(0.4);
            glow.setRadius(20);
            text.setEffect(glow);

            StackPane container = new StackPane(text);
            container.setAlignment(Pos.TOP_RIGHT);
            container.setPrefSize(view.getCanvas().getWidth(), view.getCanvas().getHeight());

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage image = container.snapshot(params, null);

            double x = settings.playerLifeX - (image.getWidth() / 2);
            double y = settings.playerLifeY - (image.getHeight() / 2);

            graphics.drawImage(image, x, y);

            if (gainedALife) {
                PauseTransition pause = new PauseTransition(Duration.millis(140));
                pause.setOnFinished(event -> gainedALife = false);
                pause.play();
            }
        });
    }

    protected void updateScoreStatus() {
        AutomatedLog.wrapEvent("Updating score status for arena", () -> {
            Text text = new Text(String.valueOf(paddle.getScore()));
            text.setFont(Font.font("Arial", FontWeight.NORMAL, 180));
            text.setStyle("-fx-letter-spacing: 5px;");
            text.setEffect(blurrer.gaussian);

            StackPane container = new StackPane(text);
            container.setAlignment(Pos.CENTER); // Center the text in the container
            container.setPrefSize(view.getCanvas().getWidth(), view.getCanvas().getHeight());

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage image = container.snapshot(params, null);

            double x = (view.getCanvas().getWidth() - image.getWidth()) / 2;
            double y = (view.getCanvas().getHeight() - image.getHeight()) / 2;

            graphics.drawImage(image, x, y);

            blurrer.animate();

            if (blurrer.on) {
                PauseTransition pause = new PauseTransition(Duration.millis(140));
                pause.setOnFinished(event -> blurrer.on = false);
                pause.play();
            }
        });
    }

    private void updateOrbStatus() {
        AutomatedLog.wrapEvent("Updating all orbs status in arena", () -> {
            Text text = new Text("" + orbs.size());
            text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, gainedMultiOrb ? settings.orbStatusShowSize : settings.orbStatusHideSize));
            text.setStyle("-fx-letter-spacing: 5px;");
            text.setFill(settings.orbColor);

            DropShadow glow = new DropShadow();
            glow.setColor(settings.orbColor);
            glow.setSpread(0.4);
            glow.setRadius(20);
            text.setEffect(glow);

            StackPane container = new StackPane(text);
            container.setAlignment(Pos.BOTTOM_RIGHT);
            container.setPrefSize(view.getCanvas().getWidth(), view.getCanvas().getHeight());

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage image = container.snapshot(params, null);

            double centerX = settings.orbStatusX;
            double centerY = settings.orbStatusY;

            double x = centerX - (image.getWidth() / 2);
            double y = centerY - (image.getHeight() / 2);

            graphics.drawImage(image, x, y);

            if (gainedMultiOrb) {
                PauseTransition pause = new PauseTransition(Duration.millis(140));
                pause.setOnFinished(event -> gainedMultiOrb = false);
                pause.play();
            }
        });
    }

    private void updateComboStatus() {
        AutomatedLog.wrapEvent("Updating combo for arena", () -> {
            Text text = new Text("x " + bricks.getComboCount());
            text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, destroyedABrick ? settings.brickComboShowSize : settings.brickComboHideSize));
            text.setStyle("-fx-letter-spacing: 5px;");
            text.setFill(Color.YELLOW);

            DropShadow glow = new DropShadow();
            glow.setColor(Color.LIGHTYELLOW);
            glow.setSpread(0.4);
            glow.setRadius(20);
            text.setEffect(glow);

            StackPane container = new StackPane(text);
            container.setAlignment(Pos.TOP_LEFT);
            container.setPrefSize(view.getCanvas().getWidth(), view.getCanvas().getHeight());

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage image = container.snapshot(params, null);

            double x = (settings.brickComboX - image.getWidth()) / 2;
            double y = (settings.brickComboY - image.getHeight()) / 2;

            graphics.drawImage(image, x, y);

            if (destroyedABrick) {
                PauseTransition pause = new PauseTransition(Duration.millis(140));
                pause.setOnFinished(event -> destroyedABrick = false);
                pause.play();
            }
        });
    }

    @Override
    protected void updateEnemies() {
        AutomatedLog.wrapEvent("Updating the state of the enemies for the arena", () -> {
            updateBricks();
            updateCollectibles();
        });
    }

    private void updateBricks() {
        AutomatedLog.wrapEvent("Updating the state of the enemy bricks in the arena", () -> {
            bricks.each(brick -> {
                if (brick.dead()) {
                    // Bricks are not removed when dead for future tracking purposes if necessary.
                    return;
                }

                orbs.each(orb -> {
                    if (orb.collidesWith(brick)) {
                        brick.damage();
                        if (brick.dead()) {
                            blurrer.on = true;

                            int maxPossibleHealth = settings.brickMaxPossibleHealth;

                            paddle.addScore(Score.DESTROYED);
                            Color[] effectColors = new Color[maxPossibleHealth + 1]; // add one for indicator color
                            for (int i = 0; i < maxPossibleHealth; i++) {
                                effectColors[i] = Color.rgb(0, 255 - i * 100, 255 - i * 100);
                            }

                            if (brick instanceof CollectibleBrick collectible) {
                                effectColors[effectColors.length - 1] = collectible.getType().color();
                            } else {
                                effectColors[effectColors.length - 1] = effectColors[(int) (Math.random() * maxPossibleHealth)];
                            }

                            entityEffects.add(new Explosion(brick.getX(), brick.getY(), settings.brickExplosionPiecesArc, effectColors, settings.brickExplosionPiecesNum, settings.appHeight, settings.engineGravity));

                            bricks.trackDestroyed();
                            bricks.triggerCombo();
                            destroyedABrick = true;
                            if (settings.enableScreenShake) {
                                // Screen shakes intensifies based on the combo count.
                                shaker.trigger(ShakeMagnitude.LIGHT);
                                if (bricks.getComboCount() > 5) {
                                    shaker.trigger(ShakeMagnitude.EXTREME);
                                } else if (bricks.getComboCount() > 3) {
                                    shaker.trigger(ShakeMagnitude.HEAVY);
                                } else if (bricks.getComboCount() > 2) {
                                    shaker.trigger(ShakeMagnitude.MEDIUM);
                                }
                            }

                            if (brick instanceof CollectibleBrick collectible) {
                                collectible.drop();
                                collectibles.add(collectible.getCollectible());
                            }

                            audios.playWithVolume("enemyDestroyed", AudioVolume.HIGH);
                        } else {
                            paddle.addScore(Score.HIT);

                            audios.play("enemyHit");
                        }

                        orb.randomizeAngle(Math.PI / 4);
                    }
                });
            });
            bricks.update(delta);
            bricks.draw(graphics);

            if (bricks.isEmpty()) {
                if (level < settings.maxLevel) {
                    level++;
                    levelUp = true;
                } else {
                    win = true;
                }
            }
        });
    }

    private void updateCollectibles() {
        AutomatedLog.wrapEvent("Updating the state of the collectibles in the arena", () -> {
            collectibles.each(collectible -> {
                if (collectible.collidesWith(paddle)) {
                    collectible.collect();

                    audios.play("playerCollect");

                    if (collectible instanceof LargePaddleCollectible largePaddle) {
                        paddle.enlarge();

                        audios.play("playerEnlarge");
                    } else if (collectible instanceof LifeCollectible life) {
                        paddle.heal();
                        gainedALife = true;

                        audios.play("playerHeal");
                    } else if (collectible instanceof MultiOrbCollectible multiOrb) {
                        loadOrbs(3);
                        loadCannon();

                        audios.play("playerReload");
                    }
                }
            });
            collectibles.update(delta, settings.appHeight);
            collectibles.draw(graphics);
        });
    }

    @Override
    protected void updatePlayers() {
        AutomatedLog.wrapEvent("Updating the state of the players for the arena", () -> {
            updatePaddle();
            if (cannon.deployed()) {
                updateCannon();
            }
        });
    }

    private void updatePaddle() {
        AutomatedLog.wrapEvent("Updating the state of the paddle for the arena", () -> {
            paddle.update(delta, settings.appWidth, settings.appHeight);

            orbs.each(orb -> {
                if (orb.collidesWith(paddle)) {
                    paddle.triggerKnockback();

                    audios.play("playerHit");
                }

                if (orb.hasAttacked()) {
                    paddle.damage();

                    blurrer.animateRedOverlay();
                    if (paddle.getHealth() == 0) {
                        blurrer.animateFullScreen(true);
                        lose = true;
                        paddle.minusScore(-settings.playerLifeLostScore);
                    }

                    audios.play("playerDamage");
                }
            });

            paddle.draw(graphics);
        });
    }

    private void updateCannon() {
        AutomatedLog.wrapEvent("Updating the state of the cannon for the arena", () -> {
            cannon.update(delta);
            cannon.draw(graphics);
        });
    }

    @Override
    protected void updateProjectiles() {
        AutomatedLog.wrapEvent("Updating the state of the projectiles for the arena", () -> {
            updateOrb();
        });
    }

    private void updateOrb() {
        AutomatedLog.wrapEvent("Updating the state of the orb for the arena", () -> {
            orbs.each(orb -> {
                orb.update(delta, settings.appWidth, (int) (settings.appHeight + mainOrb.getHeight()));
                orb.draw(graphics);
            });
        });
    }

    private void updateEffects() {
        AutomatedLog.wrapEvent("Updating the state of all effects in the arena", () -> {
            shaker.update();
            updateEntityEffects();
        });
    }

    private void updateEntityEffects() {
        AutomatedLog.wrapEvent("Updating the state of all entity effects in the arena", () -> {
            for (Effect effect : entityEffects) {
                effect.update(delta);
                effect.draw(graphics);
            }

            entityEffects.removeIf(Effect::dead);
        });
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public OrbCollection getOrbs() {
        return orbs;
    }

    public BrickCollection getBricks() {
        return bricks;
    }

    public Parallax getBackground() {
        return background;
    }

    /**
     * Handles all the blurring effects on the screen.
     */
    private class Blurrer {
        private boolean on = false;
        private static final double FROM = settings.playerScoreShowBlur;
        private static final double TO = settings.playerScoreHideBlur;

        private Pane root;
        private GaussianBlur gaussian = new GaussianBlur(TO);
        private Rectangle redOverlay;
//        private Timeline blur = new Timeline();

        public Blurrer(Pane root) {
            this.root = root;
        }

        public void animate() {
//            if (blur != null && blur.getStatus() == Animation.Status.RUNNING) {
//                blur.stop(); // Stop the ongoing animation if it's running
//            }

            double targetRadius = on ? FROM : TO;
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(gaussian.radiusProperty(), gaussian.getRadius())),
                    new KeyFrame(Duration.millis(100), new KeyValue(gaussian.radiusProperty(), targetRadius))
            );
            timeline.play();
        }

        public void animateRedOverlay() {
            this.redOverlay = new Rectangle();
            redOverlay.setFill(Color.rgb(255, 0, 0, 0.4)); // Red color with some transparency
            redOverlay.widthProperty().bind(root.widthProperty());
            redOverlay.heightProperty().bind(root.heightProperty());
            redOverlay.setVisible(false);

            root.getChildren().add(redOverlay);

            on = !on;
            // Define blur effect animation
            double targetBlurRadius = on ? FROM : TO;
            Timeline blurTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(gaussian.radiusProperty(), gaussian.getRadius())),
                    new KeyFrame(Duration.millis(200), new KeyValue(gaussian.radiusProperty(), targetBlurRadius)) // Increased duration
            );

            // Define red overlay animation
            redOverlay.setVisible(true);
            Timeline overlayTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(redOverlay.opacityProperty(), 0)),
                    new KeyFrame(Duration.millis(100), new KeyValue(redOverlay.opacityProperty(), on ? 0.5 : 0)),
                    new KeyFrame(Duration.millis(200), new KeyValue(redOverlay.opacityProperty(), 0))
            );
            overlayTimeline.setOnFinished(e -> redOverlay.setVisible(false));

            // Play animations
            blurTimeline.play();
            overlayTimeline.play();
        }

        /**
         * Returns the blur effect for the entire full screen.
         * Since this does not have any cooldown or timer, the developer can decide on when to switch it on or off.
         *
         * @param on Whether to turn on or off the blur effect.
         */
        public void animateFullScreen(boolean on) {
            double targetRadius = on ? 0 : 0;
            Timeline blurTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(gaussian.radiusProperty(), gaussian.getRadius())),
                    new KeyFrame(Duration.seconds(1), new KeyValue(gaussian.radiusProperty(), targetRadius))
            );

            blurTimeline.setOnFinished(e -> {
                if (!on) {
                    root.setEffect(null);
                }
            });

            if (on) {
                root.setEffect(gaussian);
            }

            blurTimeline.play();
        }

        public GaussianBlur getEffect() {
            return gaussian;
        }
    }

    private class ShapeDrawer {
        private GraphicsContext graphics;

        public ShapeDrawer(GraphicsContext graphics) {
            this.graphics = graphics;
        }

        /**
         * Draws a heart shape based on the given values.
         *
         * @param x
         * @param y
         * @param width
         * @param height
         *
         * @link <a href="https://andbin.dev/java/drawing-heart-shape-java2d">Inspired from</a>
         */
        public void heart(double x, double y, double width, double height) {
            double beX = x + width / 2;  // bottom endpoint X
            double beY = y + height;     // bottom endpoint Y

            double c1DX = width  * 0.968f;  // delta X of control point 1
            double c1DY = height * 0.672f;  // delta Y of control point 1
            double c2DX = width  * 0.281f;  // delta X of control point 2
            double c2DY = height * 1.295f;  // delta Y of control point 2
            double teDY = height * 0.850f;  // delta Y of top endpoint

            graphics.beginPath();
            graphics.moveTo(beX, beY); // bottom endpoint

            // left side of heart
            graphics.bezierCurveTo(
                    beX - c1DX, beY - c1DY,   // control point 1
                    beX - c2DX, beY - c2DY,   // control point 2
                    beX, beY - teDY);         // top endpoint

            // right side of heart
            graphics.bezierCurveTo(
                    beX + c2DX, beY - c2DY,   // control point 2
                    beX + c1DX, beY - c1DY,   // control point 1
                    beX, beY);                // bottom endpoint

            graphics.closePath();

            graphics.setFill(settings.playerLifeColor);
            graphics.fill();
        }
    }
}
