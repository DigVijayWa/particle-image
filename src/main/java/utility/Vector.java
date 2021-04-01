package utility;

public class Vector {

  private double x;
  private double y;
  private double angle;
  private double magnitude;

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
    this.angle = Math.atan2(y, x);
    this.magnitude = getMagnitude();
  }

  public void setXandY(double x, double y) {
    this.x = x;
    this.y = y;
    this.angle = Math.atan2(y, x);
    this.magnitude = getMagnitude();
  }

  public double getMagnitude() {
    double addition = (x * x) + (y * y);
    return Math.sqrt(addition);
  }

  public void Vector1(double angle, double magnitude) {
    this.angle = angle;
    this.magnitude = magnitude;
    setXY();
  }

  public Vector additionVector(Vector vector1, Vector vector2, long timeLapsed) {
    return new Vector(vector1.getX() + vector2.getX() * 1,
        vector1.getY() + vector2.getY() * 1);
  }

  public Vector subtractionVector(Vector vector1, Vector vector2) {
    return new Vector(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY());
  }

  public void setXY() {
    this.x = this.magnitude * Math.cos(angle);
    this.y = this.magnitude * Math.sin(angle);
  }

  public Vector normalize() {
    if(this.x == 0 && this.y == 0){
      return new Vector(0,0);
    }
    double length = Math.sqrt((this.x * this.x) + (this.y * this.y));
    return new Vector(this.x/length, this.y/length);
  }

  public Vector multiplyByScalar(double scalar) {
    return new Vector(this.x * scalar, this.y * scalar);
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getAngle() {
    return angle;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public void setMagnitude(double magnitude) {
    this.magnitude = magnitude;
  }
}


