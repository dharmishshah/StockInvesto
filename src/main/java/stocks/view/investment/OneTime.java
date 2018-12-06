package stocks.view.investment;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import stocks.controller.GUIController;
import stocks.model.stock.Stock;
import stocks.view.GUIView;

/**
 * The following method represents the One time investment panel.
 */
public class OneTime extends javax.swing.JPanel implements GUIView {
  //The following variable represents the GUIController object.
  GUIController controller;
  //The following variable represents the  custom label.
  private javax.swing.JLabel OneTimeCustomStocksLbl;
  //The following variable represents the  custom label.
  private javax.swing.JLabel jLabel1;
  //The following variable represents the  custom label.
  private javax.swing.JLabel jLabel2;
  //The following variable represents the  custom label.
  private javax.swing.JLabel jLabel3;
  //The following variable represents the  custom label.
  private javax.swing.JLabel jLabel4;
  //The following variable represents the  custom label.
  private javax.swing.JLabel jLabel5;
  //The following variable represents the  custom label.
  private javax.swing.JLabel jLabel6;
  //The following variable text field.
  private javax.swing.JTextField oneAmountInvested;
  //The following variable text field.
  private javax.swing.JTextField oneCommissionRate;
  //The following variable text field.
  private javax.swing.JTextField oneInvestmentDate;
  private javax.swing.JComboBox oneInvestmentOption;
  //The following variable text field.
  private javax.swing.JTextField oneTimeCustomWeightsTxt;
  private javax.swing.JLabel oneTimeErrorLbl;
  private javax.swing.JComboBox oneTimePortfolioId;
  private javax.swing.JButton saveOneTime;
  /**
   * The following constructor initializes the one time investment panel.
   *
   * @param controller the GUIController object.
   */
  public OneTime(GUIController controller) {
    initComponents();
    this.controller = controller;
    this.controller.setOneTimeInvestmentView(this);
    saveOneTime.setActionCommand("createOneTime");
    oneInvestmentOption.setActionCommand("oneTime");
    oneInvestmentOption.removeAllItems();
    oneInvestmentOption.addItem("EQUAL");
    oneInvestmentOption.addItem("CUSTOM");
    oneTimePortfolioId.removeAllItems();
    oneTimePortfolioId.addItem("Select");
    String portfolios = controller.getExistingPortfolios();
    if (!portfolios.isEmpty()) {
      String[] portfolioList = portfolios.substring(1).split("\n");
      for (String port : portfolioList) {
        oneTimePortfolioId.addItem(port);
      }
    }
  }

  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    oneTimePortfolioId = new javax.swing.JComboBox();
    oneInvestmentOption = new javax.swing.JComboBox();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    saveOneTime = new javax.swing.JButton();
    oneInvestmentDate = new javax.swing.JTextField();
    oneAmountInvested = new javax.swing.JTextField();
    oneCommissionRate = new javax.swing.JTextField();
    OneTimeCustomStocksLbl = new javax.swing.JLabel();
    oneTimeCustomWeightsTxt = new javax.swing.JTextField();
    oneTimeErrorLbl = new javax.swing.JLabel();

    jLabel1.setText("ONE TIME STRATEGY");

    jLabel2.setText("ENTER THE INVESTMENT DATE (MM/DD/YYYY)");

    jLabel4.setText("SELECT THE INVESTMENT OPTION");

    jLabel3.setText("SELECT THE PORTFOLIO ");

    oneTimePortfolioId.setModel(new javax.swing
            .DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

    oneInvestmentOption.setModel(new javax.swing
            .DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

    jLabel5.setText("ENTER THE AMOUNT INVESTED");

    jLabel6.setText("ENTER THE COMMISSION RATE");

    saveOneTime.setText("SAVE");

    OneTimeCustomStocksLbl.setText("Enter custom weights seperated by comma");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup
                                    (javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(oneInvestmentDate,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveOneTime,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(oneTimePortfolioId,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(oneAmountInvested,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(oneCommissionRate,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(OneTimeCustomStocksLbl)
                                    .addGroup(layout.createParallelGroup
                                            (javax.swing.GroupLayout.Alignment.TRAILING,
                                                    false)
                                            .addComponent(oneTimeCustomWeightsTxt,
                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(oneInvestmentOption,
                                                    javax.swing.GroupLayout.Alignment.LEADING,
                                                    0, 154, Short.MAX_VALUE))
                                    .addComponent(oneTimeErrorLbl))
                            .addContainerGap(546, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(oneTimePortfolioId,
                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(oneInvestmentDate, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(oneAmountInvested, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(oneCommissionRate, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(oneInvestmentOption,
                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(OneTimeCustomStocksLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(oneTimeCustomWeightsTxt,
                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveOneTime)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(oneTimeErrorLbl)
                            .addContainerGap(45, Short.MAX_VALUE))
    );
  }

  /**
   * The following method represents the combo box listener.
   *
   * @param listener the item listener.
   */
  @Override
  public void addComboBoxListener(ItemListener listener) {
    oneInvestmentOption.addItemListener(listener);
  }

  /**
   * The following method represents the button listener button.
   *
   * @param listener the variable represents the listener action.
   */
  @Override
  public void addActionListener(ActionListener listener) {
    saveOneTime.addActionListener(listener);
  }

  /**
   * The following method represents the summary data.
   *
   * @param data the stock data.
   */
  @Override
  public void setSummaryData(Map<String, Map<String, Double>> data) {
  }

  /**
   * The following method enables system to read from text box.
   *
   * @param fieldName the input given by user.
   */
  @Override
  public String getTextFieldData(String fieldName) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JTextField portfolioName = (JTextField) f.get(o);
      return portfolioName.getText();
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method enables system to read from the combo box.
   *
   * @param fieldName the input given by the user.
   */
  @Override
  public String getComboFieldData(String fieldName) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JComboBox<String> portfolioName = (JComboBox<String>) f.get(o);
      return (String) portfolioName.getSelectedItem();
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method clears the data from text box.
   *
   * @param fieldName the content of the text box.
   */
  @Override
  public void clearTextFieldData(String fieldName) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JTextField portfolioName = (JTextField) f.get(o);
      portfolioName.setText("");
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }

  /**
   * The following method updates the combo box.
   *
   * @param stocksInPortfolio the stocks in the portfolio.
   */
  public void updateStockComboBox(List<Stock> stocksInPortfolio) {
    //oneStocksInPortfolio.removeAllItems();
    for (Stock stock : stocksInPortfolio) {
      String stocksTickerSymbol = stock.getTickerSymbol();
      //oneStocksInPortfolio.addItem(stocksTickerSymbol);
    }
  }
}
