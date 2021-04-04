package components;

import components.enums.ComponentLifeCycle;
import components.enums.ComponentType;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Optional;

public class DropDownComponent extends Component{

  private List<String> items;

  public enum DropDownState
  {
    DROP_DOWN_OPEN(Optional.empty()),
    DROP_DOWN_CLOSED(Optional.empty());

    private String selectedValue;

    public String getSelectedValue() {
      return selectedValue;
    }

    DropDownState setSelectedValue(String selectedValue) {
      this.selectedValue = selectedValue;
      return this;
    }

    DropDownState(Optional<String> selectedValue) {
      this.selectedValue = selectedValue.orElse("NONE");
    }
  }

  private DropDownState dropDownState = DropDownState.DROP_DOWN_CLOSED;

  public DropDownComponent(double x, double y, List<String> items, String label) {
    super(x,y, Optional.of(label), ComponentType.DROP_DOWN_MENU, ComponentLifeCycle.ACTIVE);
    this.items = items;
  }

  public List<String> getItems() { return items; }

  public void setItems(List<String> items) { this.items = items; }

  @Override
  void render(Graphics2D graphics2D) {

  }

  @Override
  void update(long timePassed) {

  }

  public DropDownState getDropDownState() {
    return dropDownState;
  }

  public void setDropDownState(DropDownState dropDownState, String selectedValue) {
    this.dropDownState = dropDownState.setSelectedValue(selectedValue);
  }
}
