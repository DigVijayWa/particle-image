package utility;

import app.App;
import app.App.DisplayType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import objects.HandlerImageParticle;
import objects.HandlerTrackerParticle;

public class MouseListener extends MouseAdapter implements MouseMotionListener {

  private HandlerImageParticle handlerImageParticle;

  private HandlerTrackerParticle handlerTrackerParticle;

  private App app;

  public MouseListener(HandlerImageParticle handlerImageParticle, HandlerTrackerParticle handlerTrackerParticle, App app) {
    this.handlerImageParticle = handlerImageParticle;
    this.handlerTrackerParticle = handlerTrackerParticle;
    this.app = app;
  }

  @Override
  public void mouseMoved(MouseEvent event) {
    handlerTrackerParticle.setMouseTarget(new Vector(event.getX(), event.getY()));
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    DisplayType displayType = app.getDisplayType() == DisplayType.IMAGE_DISPLAY ? DisplayType.TRACKER_DISPLAY : DisplayType.IMAGE_DISPLAY;
    app.setDisplayType(displayType);
  }

  public void mousePressed(MouseEvent event) {

      if(app.getDisplayType() == DisplayType.IMAGE_DISPLAY) {
        handleImageParticleClick(event);
      }
      else {
        handleTrackerParticleClick(event);
      }

  }

  public void handleImageParticleClick(MouseEvent event) {
    if (event.getButton() == MouseEvent.BUTTON1) {
      if ((event.getX() >= handlerImageParticle.offsetX
          && event.getX() <= handlerImageParticle.offsetX + HandlerImageParticle.WIDTH) && (
          event.getY() >= handlerImageParticle.offsetY
              && event.getY() <= handlerImageParticle.offsetY + HandlerImageParticle.HEIGHT)) {
        handlerImageParticle.setDispersion(!handlerImageParticle.getDispersion());
        handlerImageParticle.updateAllParticlesFixedState();
      }
    } else if (event.getButton() == MouseEvent.BUTTON2) {
      handlerImageParticle.setOffsetY(event.getY() - 50);
      handlerImageParticle.setOffsetX(event.getX() - 50);
      handlerImageParticle.updateAllParticlesFixedState();
    }
  }

  public void handleTrackerParticleClick(MouseEvent event) {
    handlerTrackerParticle.addTarget(event.getX(), event.getY());
  }
}
