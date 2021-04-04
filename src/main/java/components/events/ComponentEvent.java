package components.events;


import components.Component;

public class ComponentEvent {

  private Component sourceComponent;

  public ComponentEvent(Component sourceComponent) {
    this.sourceComponent = sourceComponent;
  }
}
