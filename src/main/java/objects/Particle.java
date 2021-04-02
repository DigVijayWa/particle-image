package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import utility.GameUtility;
import utility.Vector;

public class Particle {

  private Vector position;
  private Vector accelaration;
  private Vector wind;
  private Vector velocity;

  private Vector gravity;

  private int size;

  private boolean fixed = false;

  public Particle(double xPosition, double yPosition) {
    position = new Vector(xPosition, yPosition);
    accelaration = new Vector(0, 0);
    velocity = new Vector(0,0);
    gravity = new Vector(0, 5);
    size = 2;
  }

  public void render(Graphics2D graphics2D, Color color) {
    Color prevColor = graphics2D.getColor();
    graphics2D.setColor(color);
    graphics2D.drawLine((int) position.getX(), (int) position.getY(), (int) position.getX(),
        (int) position.getY());
    graphics2D.setColor(prevColor);
  }

  public void update(long timePassed, Vector velocity) {
    if(!fixed) {
      long timePassedSeconds = GameUtility.getTimeInMiliSeconds(timePassed);
      this.velocity = velocity;
      accelaration = accelaration.additionVector(this.velocity, timePassedSeconds);
      position = position.additionVector(accelaration, timePassedSeconds);
      accelaration.setXandY(0, 0);
    }
  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public Vector getAccelaration() {
    return accelaration;
  }

  public void setAccelaration(Vector accelaration) {
    this.accelaration = accelaration;
  }

  public Vector getWind() {
    return wind;
  }

  public void setWind(Vector wind) {
    this.wind = wind;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Vector getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public boolean isFixed() {
    return fixed;
  }

  public void setFixed(boolean fixed) {
    this.fixed = fixed;
  }
}
