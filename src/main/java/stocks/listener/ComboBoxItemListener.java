package stocks.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

public class ComboBoxItemListener implements ItemListener {

  Map<String,Runnable> comboBoxActions;

  /**
   * Empty default constructor
   */
  public ComboBoxItemListener() {
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters
   */

  public void setComboBoxActionMap(Map<String,Runnable> map) {
    comboBoxActions = map;
  }

  @Override
  public void itemStateChanged(ItemEvent itemEvent) {
    if (comboBoxActions.containsKey(itemEvent.getItem())) {
      comboBoxActions.get(itemEvent.getItem()).run();
    }
  }
}
