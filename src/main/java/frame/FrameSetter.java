
package frame;

import app.App;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import utility.SliderValues;

public class FrameSetter {

  private JFrame jframe;

  private JSlider maxSpeedControl;

  private JSlider maxForceControl;

  private JSlider minForceControl;

  private JPanel menuPanel;

  private JPanel mainPanel;

  public FrameSetter(int width, int height, App app) {
    this.jframe = new JFrame("GameModeOn");
    int gameHeight = 740;

    jframe.setSize(width, gameHeight);

    app.setBounds(0, 0, width, height);

    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initSlider();

    mainPanel = new JPanel();
    mainPanel.setBounds(0, 0, width, gameHeight);
    mainPanel.setLayout(null);

    menuPanel = new JPanel();
    menuPanel.setBounds(0, 640, width, gameHeight - 640);

    GridLayout layout = new GridLayout(1, 3, 10, 10);

    menuPanel.add(maxSpeedControl);
    menuPanel.add(maxForceControl);
    menuPanel.add(minForceControl);

    menuPanel.setLayout(layout);

    mainPanel.add(app);
    mainPanel.add(menuPanel);
    mainPanel.setVisible(true);
    menuPanel.setVisible(true);

    jframe.add(mainPanel);
    jframe.setVisible(true);

    app.start();
  }

  public void initSlider() {
    maxSpeedControl = new JSlider(JSlider.HORIZONTAL, 50, 500, 210);
    maxForceControl = new JSlider(JSlider.HORIZONTAL, 35, 250, 75);
    minForceControl = new JSlider(JSlider.HORIZONTAL, 20, 250, 50);

    maxSpeedControl.addChangeListener(e -> SliderValues.setMaxSpeed(maxSpeedControl.getValue()));

    maxForceControl.addChangeListener(e -> SliderValues.setMaxForce(maxForceControl.getValue()));

    minForceControl.addChangeListener(e -> SliderValues.setMinForce(minForceControl.getValue()));
  }
}
