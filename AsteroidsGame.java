import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors and Aidan Chadha
 * @version s23
 */
public class AsteroidsGame implements Playable {

    public static final int LIVES = 3;

    private ArrayList<Drawable> drawElements;
    private ArrayList<Updatable> updateElements;
    private ArrayList<GameElement> shipAndBullets;
    private ArrayList<Enemy> enemies;
    private Ship ship;

    private NumericDisplay score;
    private NumericDisplay lives;

    /**
     * Constructs all game elements.
     */
    public AsteroidsGame() {
        StdDraw.setTitle("Generic Space Rock Shooter");

        drawElements = new ArrayList<>();
        updateElements = new ArrayList<>();
        shipAndBullets = new ArrayList<>();
        enemies = new ArrayList<>();

        // add background elements
        final int TOP = GameDriver.SCREEN_HEIGHT;
        score = new NumericDisplay(40, TOP - 20, "Score: ", 0);
        lives = new NumericDisplay(40, TOP - 40, "Lives: ", LIVES);
        drawElements.add(score);
        drawElements.add(lives);

        // TODO
    }

    /**
     * Creates and adds 100 stars with random locations.
     */
    private void newStars() {
        for (int i = 0; i < 100; i++) {
            double x = Math.random() * GameDriver.SCREEN_WIDTH;
            double y = Math.random() * GameDriver.SCREEN_HEIGHT;
            Star star = new Star(x, y);
            drawElements.add(star);
        }
    }

    /**
     * Creates a new ship and adds it to the according array lists.
     */
    private void newShip() {
        ship = new Ship();
        drawElements.add(ship);
        shipAndBullets.add(ship);
        updateElements.add(ship);
    }

    /**
     * Makes 10 asteroids.
     */
    private void newEnemies() {
        for (int i = 0; i < 10; i++) {
            AsteroidSize asteroidSize = AsteroidSize.randomSize();
            Asteroid asteroid = new Asteroid(asteroidSize);
            enemies.add(asteroid);
            updateElements.add(asteroid);
            drawElements.add(asteroid);
        }
    }

    /**
     * Starts a new game.
     */
    public void startGame() {
        newStars();
        newShip();
        newEnemies();
    }

    /**
     * Add an enemy.
     * 
     * @param enemy the enemy to be added.
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        updateElements.add(enemy);
        drawElements.add(enemy);
    }

    /**
     * Handle keyboard input from the game and move the ship and shoot bullets if the corresponding keys are pressed.
     */
    private void handleKeyboardInput() {
        if (GameDriver.spacePressed()) {
            Bullet bullet = new Bullet(ship.pose);
            shipAndBullets.add(bullet);
            updateElements.add(bullet);
            drawElements.add(bullet);
        }

        if (GameDriver.leftPressed()) {
            ship.turnLeft();
        }

        if (GameDriver.rightPressed()) {
            ship.turnRight();
        }

        if (GameDriver.upPressed()) {
            ship.thrust();
        }
    }

    /**
     * Handles colissions between enemies and ship and the asteroids.
     */
    private void handleCollisions() {
        for (Enemy enemy : enemies) {
            for (GameElement bullet : shipAndBullets) {
                if (bullet instanceof Bullet) {
                    if (bullet.checkCollision(enemy)) {
                        bullet.setDestroyed(true);
                        enemy.setDestroyed(true);
                    }
                }
            }
            if (ship.checkCollision(enemy)) {
                int currentLives = lives.getValue();
                lives.setValue(currentLives - 1);
                ship.setDestroyed(true);
                enemy.setDestroyed(true);
                shipAndBullets.remove(ship);
                drawElements.remove(ship);
                updateElements.remove(ship);
                newShip();
            }
        }
    }

    /**
     * Gets rid of all destroyed bullets.
     */
    private void removeDestroyedBullets() {
        for (int i = 0; i < shipAndBullets.size(); i++) {
            if (shipAndBullets.get(i) instanceof Bullet) {
                if (shipAndBullets.get(i).isDestroyed()) {
                    drawElements.remove(shipAndBullets.get(i));
                    updateElements.remove(shipAndBullets.get(i));
                    shipAndBullets.remove(shipAndBullets.get(i));

                }
            }
        }
    }

    /**
     * Gets rid of all destroyed enemies.
     */
    private void removeDestroyedEnemies() {
        checkAllEnemies();
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isDestroyed()) {
                score.setValue(score.getValue() + enemies.get(i).getPoints());
                drawElements.remove(enemies.get(i));
                updateElements.remove(enemies.get(i));
                enemies.remove(enemies.get(i));
            }
        }
    }

    /**
     * Checks if all enemies have been destroyed.
     */
    private void checkAllEnemies() {
        int count = 0;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isDestroyed()) {
                count++;
            }
        }
        if (count == enemies.size()) {
            newEnemies();
        }
    }

    @Override
    public void update() {
        Random r = new Random();
        // freeze simulation if game is over
        if (lives.getValue() <= 0) {
            return;
        }

        int choice = r.nextInt(1000);
        if (choice <= 1) {
            Saucer saucer = new Saucer();
            enemies.add(saucer);
            updateElements.add(saucer);
            drawElements.add(saucer);
        }
        // update everything (including newest bullet)
        handleKeyboardInput();
        for (Updatable item : updateElements) {
            item.update();
        }

        // TODO You will need these in Part 3
        handleCollisions();
        removeDestroyedBullets();
        removeDestroyedEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof Saucer) {
                enemy.update();
            }
        }

    }

    @Override
    public void draw() {
        // TODO
        StdDraw.setPenColor(Color.WHITE);
        for (Drawable item : drawElements) {
            item.draw();
        }
    }

    /**
     * Generates a random X position on the screen.
     * 
     * @return the x position
     */
    protected static double randomXPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH;
    }

    /**
     * Generates a random Y position on the screen.
     * 
     * @return the y position
     */
    protected static double randomYPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT;
    }

    /**
     * Generates a random heading from the interval [0, 2*PI).
     * 
     * @return the heading
     */
    protected static double randomHeading() {
        return GameDriver.GENERATOR.nextDouble() * 2 * Math.PI;
    }

}
