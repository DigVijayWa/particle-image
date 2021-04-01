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

  boolean formationComplete = false;

  public int offsetX = 200;
  public int offsetY = 200;

  int counter = 0;

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
        particles[j][i].render(graphics2D, new Color(countours[i][j]));
      }
    }
  }

  public void update(long passedTime) {
    counter = 0;
    Vector mag = null;
    if(!formationComplete) {
      for (int i = 0; i < HEIGHT; i++) {
        for (int j = 0; j < WIDTH; j++) {
          target.setXandY(j + offsetX, i + offsetY);
          Vector velocity = calculateEffectiveVector(particles[j][i].getPosition(), target)
              .multiplyByScalar(1.2);
          counter = velocity.getMagnitude() < 1.2 ? counter + 1 : counter;
          mag = velocity;
          particles[j][i].update(passedTime, velocity);

        }
      }
    }
    //System.out.println(mag.getX()+" : "+mag.getY());
    if(counter >= (WIDTH * HEIGHT)) {
      System.out.println("\n Formation Complete :: ");
      formationComplete = true;
    }
  }

  private static Vector calculateEffectiveVector(Vector currentPosition, Vector targetPosition) {
     return targetPosition.subtractionVector(targetPosition, currentPosition).normalize();
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
}
