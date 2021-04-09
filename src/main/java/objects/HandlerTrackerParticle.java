package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Slider;
import utility.GameUtility;
import utility.SliderValues;
import utility.Vector;

public class HandlerTrackerParticle {

  public static int WIDTH = 100;
  public static int HEIGHT = 100;

  Particle[][] particles = new Particle[WIDTH][HEIGHT];

  List<Vector> targets = new LinkedList<>();

  Vector mouseTarget;

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
        Vector target = null;
        for (int k = targets.size() - 1; k >= 0; k--) {
          double distance = particles[j][i].getPosition().distance(targets.get(k));
          if (distance < minDist) {
            minDist = distance;
            target = targets.get(k);
          }
        }

        if ((mouseTarget != null && target != null && mouseTarget.distance(target) < minDist) || (
            mouseTarget != null && target == null)) {
          target = mouseTarget;
        }

        if (target != null) {
          double scalar = GameUtility
              .mapRange(particles[j][i].getPosition().distance(target),
                  0, 200, SliderValues.getMaxSpeed(), SliderValues.getMinForce());

          Vector velocity = seek(particles[j][i].getPosition(), target,
              particles[j][i].getVelocity(), scalar);

          particles[j][i].update(passedTime, velocity);
        }
      }
    }
  }

  public static Vector seek(Vector position, Vector target, Vector currentVelocity, double scalar) {
    Vector desired = GameUtility
        .calculateEffectiveVector(position, target)
        .normalize()
        .multiplyByScalar(scalar);

    return GameUtility
        .calculateEffectiveVector(currentVelocity, desired).limitVector(SliderValues.getMaxForce());
  }

  public void render(Graphics2D graphics2D) {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        particles[j][i].render(graphics2D,
            GameUtility.getColorOnSpeed(particles[j][i].getVelocity().getMagnitude()));
      }
    }
  }

  public void addTarget(double x, double y) {

    if (targets.size() >= 5) {
      targets.remove(0);
    }
    targets.add(new Vector(x, y));
  }

  public Vector getMouseTarget() {
    return mouseTarget;
  }

  public void setMouseTarget(Vector mouseTarget) {
    this.mouseTarget = mouseTarget;
  }
}
