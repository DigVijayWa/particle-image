package utility;

public class SliderValues {
  public static double maxSpeed = 210;

  public static double maxForce = 30;

  public static double attractionDistance = 100;

  public static double minForce = 30;

  public static double getMaxSpeed() {
    return maxSpeed;
  }

  public static void setMaxSpeed(double maxSpeed) {
    SliderValues.maxSpeed = maxSpeed;
  }

  public static double getMaxForce() {
    return maxForce;
  }

  public static void setMaxForce(double maxForce) {
    SliderValues.maxForce = maxForce;
  }

  public static double getAttractionDistance() {
    return attractionDistance;
  }

  public static void setAttractionDistance(double attractionDistance) {
    SliderValues.attractionDistance = attractionDistance;
  }

  public static double getMinForce() {
    return minForce;
  }

  public static void setMinForce(double minForce) {
    SliderValues.minForce = minForce;
  }
}
