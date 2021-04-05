package components;

import components.enums.ComponentLifeCycle;
import components.enums.ComponentType;
import java.awt.Graphics2D;
import java.util.Optional;

public class SliderComponent extends Component{

  public SliderComponent(double x, double y) {
    super(x, y);
  }

  public SliderComponent(double x, double y, String labelText,
      ComponentType componentType,
      ComponentLifeCycle componentLifeCycle) {
    super(x, y, Optional.of(labelText), componentType, componentLifeCycle);
  }

  public SliderComponent(double x, double y, String labelText) {
    super(x, y, Optional.of(labelText));
  }

  @Override
  void render(Graphics2D graphics2D) {

  }

  @Override
  void update(long timePassed) {

  }
}
