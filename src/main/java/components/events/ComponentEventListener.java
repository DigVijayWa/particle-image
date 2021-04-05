package components.events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ComponentEventListener extends MouseAdapter {

  public void mousePressed(MouseEvent event) { }

  void onClickComponent(ComponentEvent componentEvent) {}

  void loadingComplete(ComponentEvent componentEvent) {}

  void componentLifeCycleChanged(ComponentEvent componentEvent) {}

  void componentValueChanged(ComponentEvent componentEvent) {}
}
