package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import utility.Countours;
import utility.Vector;

public class Handler {

  /**
   * Lets consider the box of 640 * 640
   */

  public static int WIDTH = 100;
  public static int HEIGHT = 100;

  Particle[][] particles = new Particle[WIDTH][HEIGHT];

  Vector[][] randomPosition = new Vector[WIDTH][HEIGHT];

  Vector target = new Vector(100,100);

  int[][] countours = new int[160][160];

  boolean formationComplete = false;

  boolean dispersion = false;

  public int offsetX = 200;
  public int offsetY = 200;

  int counter = 0;

  public Handler() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        particles[j][i] = new Particle(mapRandomValue(Math.random()), mapRandomValue(Math.random()));
        randomPosition[j][i] = new Vector(mapRandomValue(Math.random()), mapRandomValue(Math.random()));
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
          if(dispersion) {
            target.setXandY(randomPosition[j][i].getX(), randomPosition[j][i].getY());
          }else {
            target.setXandY(j + offsetX, i + offsetY);
          }
          Vector desired = calculateEffectiveVector(particles[j][i].getPosition(), target).normalize().multiplyByScalar(3);

          Vector velocity = calculateEffectiveVector(particles[j][i].getVelocity(), desired).limitVector(2);

          particles[j][i].update(passedTime, velocity);

          if(particles[j][i].getPosition().equals(target, 2)) {
            particles[j][i].setPosition(new Vector(target.getX(), target.getY()));
            particles[j][i].setFixed(true);
          }
        }
      }
    }

  private static Vector calculateEffectiveVector(Vector currentPosition, Vector targetPosition) {
     return targetPosition.subtractionVector(currentPosition);
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
  public double mapRandomValue(double randomValue) {
    return 0 + ((640)) * (randomValue - 0);
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
