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

  Vector target = new Vector(100,100);

  int[][] countours = new int[160][160];

  public Handler() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
          particles[j][i] = new Particle(mapRandomValue(Math.random()), mapRandomValue(Math.random()));
      }
    }

    BufferedImage image = Countours.readImage().orElse(null);

    countours = Countours.getCountoursImage(image);
  }

  public void render(Graphics2D graphics2D) {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        particles[i][j].render(graphics2D, new Color(countours[j][i]));
      }
    }
  }

  public void update(long passedTime) {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        target.setXandY(j+200,i+200);
        Vector velocity = calculateEffectiveVector(particles[j][i].getPosition(), target).multiplyByScalar(3.0);
        particles[j][i].update(passedTime, velocity);
      }
    }
  }

  private static Vector calculateEffectiveVector(Vector currentPosition, Vector targetPosition) {
     return targetPosition.subtractionVector(targetPosition, currentPosition).normalize();
  }

  public double mapRandomValue(double randomValue) {
    return 0 + ((640)) * (randomValue - 0);
  }
}
