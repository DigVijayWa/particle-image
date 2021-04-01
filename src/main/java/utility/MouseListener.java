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

    if(event.getButton() == MouseEvent.BUTTON1) {
      if ((event.getX() >= handler.offsetX && event.getX() <= handler.offsetX + Handler.WIDTH) && (
          event.getY() >= handler.offsetY && event.getY() <= handler.offsetY + Handler.HEIGHT)) {
        handler.setDispersion(!handler.getDispersion());
      }
    }
    else if(event.getButton() == MouseEvent.BUTTON2) {
      handler.setOffsetY(event.getY());
      handler.setOffsetX(event.getX());
    }
  }
}
