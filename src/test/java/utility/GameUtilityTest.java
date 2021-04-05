package utility;

import junit.framework.TestCase;
import objects.Particle;
import org.junit.Assert;

public class GameUtilityTest extends TestCase {

  public void testMapRandomValue() {
  }

  public void testCalculateScalarOnDistance0Distance() {

    double distance = 0;

    double maxVelocity = 5;

    Assert.assertEquals(5, GameUtility.calculateScalarOnDistance(distance, maxVelocity), 0.0);
  }

  public void testCalculateScalarOnDistance30Distance() {

    double distance = 30;

    double maxVelocity = 5;

    Assert.assertEquals(0.5, GameUtility.calculateScalarOnDistance(distance, maxVelocity), 0.0);
  }

  public void testCalculateScalarOnDistance45Distance() {

    double distance = 45;

    double maxVelocity = 5;

    Assert.assertEquals(0.5, GameUtility.calculateScalarOnDistance(distance, maxVelocity), 0.0);
  }

  public void testMapRangeInvertedRange() {

    Assert.assertEquals(GameUtility.mapRange(700,0,640, 0, Particle.maxSpeed), Particle.maxSpeed, 0.0);
  }

  public void testMapRangeInvertedRange0() {

    Assert.assertEquals(GameUtility.mapRange(0,0,640, 0, Particle.maxSpeed), Particle.maxSpeed, 0.0);
  }
}