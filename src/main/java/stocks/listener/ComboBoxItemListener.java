package stocks.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

/**
 * This class implements a combo box listener when item event in a combo box is performed.
 */
public class ComboBoxItemListener implements ItemListener {

  Map<String, Runnable> comboBoxActions;


  /**
   * Set the map for combo box item events.
   */

  public void setComboBoxActionMap(Map<String, Runnable> map) {
    comboBoxActions = map;
  }

  @Override
  public void itemStateChanged(ItemEvent itemEvent) {
    if (comboBoxActions.containsKey(itemEvent.getItem())) {
      comboBoxActions.get(itemEvent.getItem()).run();
    }
  }
}
