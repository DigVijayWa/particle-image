package components;

import components.enums.ComponentLifeCycle;
import components.enums.ComponentType;
import components.events.ComponentEventListener;
import java.awt.Graphics2D;
import java.util.Optional;

public abstract class Component {

  private double x;
  private double y;

  private double id;

  private Optional<String> labelText;

  private ComponentType componentType;

  private ComponentLifeCycle componentLifeCycle;

  private ComponentEventListener componentEventListener;

  public Component(double x, double y) {
    this.x = x;
    this.y = y;
  }


  public Component(double x, double y, Optional<String> labelText,
      ComponentType componentType, ComponentLifeCycle componentLifeCycle) {
    this.x = x;
    this.y = y;
    this.labelText = labelText;
    this.componentType = componentType;
    this.componentLifeCycle = componentLifeCycle;
  }

  public Component(double x, double y, Optional<String> labelText) {
    this.x = x;
    this.y = y;
    this.labelText = labelText;
  }

  public double getX() { return x; }

  public void setX(double x) { this.x = x; }

  public double getY() { return y; }

  public void setY(double y) { this.y = y; }

  void render(Graphics2D graphics2D) { }

  void update(long timePassed) { }

  boolean isActive() { return false; }

  boolean isFocused() { return false; }

  boolean isVisible() { return true; }

  boolean isLabelEnabled() { return true; }

  public String getLabelText() { return labelText.orElse("default label"); }

  public void setLabelText(String labelText) { this.labelText = Optional.of(labelText); }

  public ComponentType getComponentType() { return componentType; }

  public void setComponentType(ComponentType componentType) { this.componentType = componentType; }

  public ComponentLifeCycle getComponentLifeCycle() { return componentLifeCycle; }

  public void setComponentLifeCycle(ComponentLifeCycle componentLifeCycle) {
    this.componentLifeCycle = componentLifeCycle;
  }

  public void addMouseListener(ComponentEventListener componentEventListener) {
    this.componentEventListener = componentEventListener;
  }
}
