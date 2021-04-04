package components.events;


import components.Component;

public class ComponentEvent {

  private Component sourceComponent;

  public static int COMPONENT_RESIZED = 1;

  public ComponentEvent(Component sourceComponent) {
    this.sourceComponent = sourceComponent;
  }
}
