package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.control.Slider;
import utility.Countours;
import utility.GameUtility;
import utility.SliderValues;
import utility.Vector;

public class HandlerImageParticle {

  /**
   * Lets consider the box of 640 * 640
   */

  public static int WIDTH = 100;
  public static int HEIGHT = 100;

  Particle[][] particles = new Particle[WIDTH][HEIGHT];

  Vector[][] randomPosition = new Vector[WIDTH][HEIGHT];

  Vector target = new Vector(100, 100);

  int[][] countours = new int[160][160];

  boolean formationComplete = false;

  boolean dispersion = false;

  public int offsetX = 200;
  public int offsetY = 200;

  int counter = 0;

  public HandlerImageParticle() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        particles[j][i] = new Particle(GameUtility.mapRandomValue(Math.random()),
            GameUtility.mapRandomValue(Math.random()));
        randomPosition[j][i] = new Vector(GameUtility.mapRandomValue(Math.random()),
            GameUtility.mapRandomValue(Math.random()));
      }
    }

    BufferedImage image = Countours.readImage().orElse(null);

    countours = Countours.getCountoursImage(image);
  }

  public void render(Graphics2D graphics2D) {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        particles[j][i].render(graphics2D, new Color(countours[j][i]));
      }
    }
  }

  public void update(long passedTime) {
    counter = 0;
    Vector mag = null;
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        if (dispersion) {
          target.setXandY(randomPosition[j][i].getX(), randomPosition[j][i].getY());
        } else {
          target.setXandY(j + offsetX, i + offsetY);
        }

        double scalar = GameUtility
            .mapRange(particles[j][i].getPosition().distance(target),
                0, 640, SliderValues.getMinForce(), SliderValues.getMaxSpeed());

        Vector desired = GameUtility.calculateEffectiveVector(particles[j][i].getPosition(), target)
            .normalize().multiplyByScalar(scalar);

        Vector velocity = GameUtility
            .calculateEffectiveVector(particles[j][i].getVelocity(), desired)
            .limitVector(SliderValues.getMaxForce());

        particles[j][i].update(passedTime, velocity);

        if (particles[j][i].getPosition().equals(target, 2)) {
          particles[j][i].setPosition(new Vector(target.getX(), target.getY()));
          particles[j][i].setFixed(true);
        }
      }
    }
  }

  public boolean isDispersion() {
    return dispersion;
  }

  public void setDispersion(boolean dispersion) {
    this.dispersion = dispersion;
  }

  public boolean getDispersion() {
    return this.dispersion;
  }

  public int getOffsetX() {
    return offsetX;
  }

  public void setOffsetX(int offsetX) {
    this.offsetX = offsetX;
  }

  public int getOffsetY() {
    return offsetY;
  }

  public void setOffsetY(int offsetY) {
    this.offsetY = offsetY;
  }

  public void updateAllParticlesFixedState() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        particles[j][i].setFixed(false);
      }
    }
  }
}
