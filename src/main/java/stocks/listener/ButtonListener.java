package stocks.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This class implements a button listener for button clicks performed in views.
 * @author Dharmish
 */
public class ButtonListener implements ActionListener {
  Map<String, Runnable> buttonClickedActions;

  /**
   * Set the map for key typed events.
   */

  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
