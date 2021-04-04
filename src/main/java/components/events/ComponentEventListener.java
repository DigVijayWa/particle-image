package components.events;

public interface ComponentEventListener {

  void onClickComponent(ComponentEvent componentEvent);

  void loadingComplete(ComponentEvent componentEvent);

  void componentLifeCycleChanged(ComponentEvent componentEvent);

  void componentValueChanged(ComponentEvent componentEvent);
}
