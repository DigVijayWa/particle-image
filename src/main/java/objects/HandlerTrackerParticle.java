package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import utility.GameUtility;
import utility.Vector;

public class HandlerTrackerParticle {

  public static int WIDTH = 100;
  public static int HEIGHT = 100;

  Particle[][] particles = new Particle[WIDTH][HEIGHT];

  List<Vector> targets = new LinkedList<>();

  public HandlerTrackerParticle() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        particles[j][i] = new Particle(GameUtility.mapRandomValue(Math.random()),
            GameUtility.mapRandomValue(Math.random()));
      }
    }
  }

  public void update(long passedTime) {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {

        double minDist = 999999;
        int index = 0;
        for (int k = targets.size() - 1; k >= 0; k--) {
          double distance = particles[j][i].getPosition().distance(targets.get(k));
          if (distance < minDist) {
            minDist = distance;
            index = k;
          }
        }

        if (!targets.isEmpty()) {
          double scalar = GameUtility
              .calculateScalarOnDistance(particles[j][i].getPosition().distance(targets.get(index)),
                  Particle.maxSpeed);

          Vector desired = GameUtility
              .calculateEffectiveVector(particles[j][i].getPosition(), targets.get(index))
              .normalize()
              .multiplyByScalar(scalar);

          Vector velocity = GameUtility
              .calculateEffectiveVector(particles[j][i].getVelocity(), desired)
              .limitVector(Particle.maxForce);


          particles[j][i].update(passedTime, velocity);
        }
      }
    }
  }

  public void render(Graphics2D graphics2D) {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        particles[j][i].render(graphics2D, GameUtility.getColorOnSpeed(particles[j][i].getVelocity().getMagnitude()));
      }
    }
  }

  public void addTarget(double x, double y) {

    if (targets.size() >= 5) {
      targets.remove(0);
    }
    targets.add(new Vector(x, y));
  }
}
