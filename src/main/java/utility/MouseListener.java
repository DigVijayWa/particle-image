package utility;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import objects.Handler;

public class MouseListener extends MouseAdapter {

  private Handler handler;

  public MouseListener(Handler handler) {
    this.handler = handler;
  }

  public void mousePressed(MouseEvent event) {
    handler.setOffsetX(event.getX() - 50);
    handler.setOffsetY(event.getY() - 50);
  }
}
