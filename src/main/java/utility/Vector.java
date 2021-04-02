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

  public Vector additionVector(Vector vector, long timeLapsed) {
    return new Vector(this.getX() + vector.getX() * 1,
        this.getY() + vector.getY() * 1);
  }

  public Vector additionVector(Vector vector) {
    return new Vector(this.getX() + vector.getX() * 1,
        this.getY() + vector.getY() * 1);
  }

  public Vector subtractionVector(Vector vector) {
    return new Vector(this.getX() - vector.getX(), this.getY() - vector.getY());
  }

  public void setXY() {
    this.x = this.magnitude * Math.cos(angle);
    this.y = this.magnitude * Math.sin(angle);
  }

  public Vector normalize() {
    double length = Math.sqrt((this.x * this.x) + (this.y * this.y));
    return new Vector(this.x / length, this.y / length);
  }

  public Vector multiplyByScalar(double scalar) {
    return new Vector(this.x * scalar, this.y * scalar);
  }

  public Vector limitVector(double limit) {
    this.x = Math.abs(this.x) > limit ? (this.x/Math.abs(this.x))*limit : this.x;
    this.y = Math.abs(this.y) > limit ? (this.y/Math.abs(this.y))*limit : this.y;

    return new Vector(this.x, this.y);
  }

  public boolean equals(Vector otherVector, double precision) {
    return Math.abs(this.x - otherVector.getX()) <= precision
        && Math.abs(this.y - otherVector.getY()) <= precision;
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


