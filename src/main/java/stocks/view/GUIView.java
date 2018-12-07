package stocks.view;

import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;


/**
 * The following interface represents the action performed by the GUI.
 */

public interface GUIView {

  void addActionListener(ActionListener listener);

  default void addComboBoxListener(ItemListener listener) {
    throw new UnsupportedOperationException("Need to implement this method");
  }

  /**
   * The following method displays the summary of the stock.
   *
   * @param data the data in the portfolio.
   */
  default void setSummaryData(Map<String, Map<String, Double>> data) {
    throw new UnsupportedOperationException("Need to implement this method");
  }

  /**
   * The following method gets the text field data.
   *
   * @param fieldName the string input.
   * @return value of fieldName
   */
  default String getTextFieldData(String fieldName) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JTextField textField = (JTextField) f.get(o);
      return textField.getText();
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method sets the text field data.
   *
   * @param fieldName the string input.
   * @param value     the value of the stock.
   */
  default void setTextFieldData(String fieldName, String value) {
    throw new UnsupportedOperationException("Need to implement this method");
  }

  /**
   * The following method accepts data for the label.
   *
   * @param fieldName the input given by the user.
   * @param message   the message for the label.
   */
  default void setLabelFieldData(String fieldName, String message) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JLabel labelField = (JLabel) f.get(o);
      labelField.setText(message);
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method assigns the data in combo box.
   *
   * @param fieldName the field parameter.
   */
  default String getComboFieldData(String fieldName) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JComboBox<String> comboBox = (JComboBox<String>) f.get(o);
      return (String) comboBox.getSelectedItem();
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method assigns the data in combo box.
   *
   * @param fieldName the field parameter.
   */
  default void clearTextFieldData(String fieldName) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JTextField fieldtext = (JTextField) f.get(o);
      fieldtext.setText("");
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method assigns the error.
   *
   * @param fieldName the field value attribute.
   * @param message   the string of the message.
   */
  default void setErrorMessage(String fieldName, String message) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JLabel fieldLabel = (JLabel) f.get(o);
      fieldLabel.setText(message);
      fieldLabel.setForeground(Color.red);
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method assigns the success message.
   *
   * @param fieldName the field value attribute.
   * @param message   the string of the message.
   */
  default void setSuccessMessage(String fieldName, String message) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JLabel fieldLabel = (JLabel) f.get(o);
      fieldLabel.setText(message);
      fieldLabel.setForeground(Color.BLACK);
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }


  /**
   * The following plots the graph.
   *
   * @param plotName the name of the plot.
   * @param xAxis    the x axis of the plot.
   * @param yAxis    the y axis of the plot.
   * @param dataset  the dataset of the plot.
   */
  default void plotGraph(String plotName, String xAxis, String yAxis,
                         DefaultCategoryDataset dataset) {
    throw new UnsupportedOperationException("Need to implement this method");
  }


}
