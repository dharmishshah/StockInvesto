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
 * The following method creates the DCA panel.
 */
public class DCA extends javax.swing.JPanel implements GUIView {
  //The following variable represents the GUIController object.
  GUIController controller;
  private javax.swing.ButtonGroup buttonGroup1;
  //The following variable represents the amount invested text field.
  private javax.swing.JTextField dcaAmountInvested;
  //The following variable represents dca apply button.
  private javax.swing.JButton dcaApplyBtn;
  //The following variable represents the commission rate text field.
  private javax.swing.JTextField dcaCommissionRate;
  //The following variable represents custom stock label.
  private javax.swing.JLabel dcaCustomStocksLbl;
  //The following variable represents the custom weight text field.
  private javax.swing.JTextField dcaCustomWeight;
  //The following variable represents the day frequency text field.
  private javax.swing.JTextField dcaDayFrequency;
  //The following variable represents the end date text field.
  private javax.swing.JTextField dcaEndDate;
  //The following variable represents error label.
  private javax.swing.JLabel dcaError;
  //The following variable represents the combo box.
  private javax.swing.JComboBox<String> dcaExistingStrategies;
  //The following variable represents the combo box.
  private javax.swing.JComboBox dcaInvestmentOption;
  //The following variable represents the combo box.
  private javax.swing.JComboBox dcaPortfolioId;
  //The following method represents the save button.
  private javax.swing.JButton dcaSave;
  //The following variable represents the start date text field.
  private javax.swing.JTextField dcaStartDate;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  /**
   * The following constructor initializes the DCA panel.
   *
   * @param controller the GUIController object.
   */
  public DCA(GUIController controller) {
    initComponents();
    this.controller = controller;
    this.controller.setDCAInvestmentView(this);
    dcaInvestmentOption.removeAllItems();
    dcaInvestmentOption.addItem("EQUAL");
    dcaInvestmentOption.addItem("CUSTOM");
    dcaSave.setActionCommand("createDCA");
    dcaApplyBtn.setActionCommand("dcaApply");
    dcaPortfolioId.removeAllItems();
    dcaPortfolioId.addItem("Select");
    String portfolios = controller.getExistingPortfolios();
    if (!portfolios.isEmpty()) {
      String[] portfolioList = portfolios.substring(1).split("\n");
      for (String port : portfolioList) {
        dcaPortfolioId.addItem(port);
      }
    }
    dcaExistingStrategies.removeAllItems();
    dcaExistingStrategies.addItem("Select");
    List<String> strategies = controller.getExistingStrategies();
    for (String strategy : strategies) {
      dcaExistingStrategies.addItem(strategy);
    }
  }

  /**
   * The following method initializes the panel components
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {
    buttonGroup1 = new javax.swing.ButtonGroup();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    dcaPortfolioId = new javax.swing.JComboBox();
    jLabel7 = new javax.swing.JLabel();
    dcaInvestmentOption = new javax.swing.JComboBox();
    dcaStartDate = new javax.swing.JTextField();
    dcaEndDate = new javax.swing.JTextField();
    dcaAmountInvested = new javax.swing.JTextField();
    dcaCommissionRate = new javax.swing.JTextField();
    dcaCustomStocksLbl = new javax.swing.JLabel();
    dcaCustomWeight = new javax.swing.JTextField();
    jLabel8 = new javax.swing.JLabel();
    dcaDayFrequency = new javax.swing.JTextField();
    dcaError = new javax.swing.JLabel();
    dcaSave = new javax.swing.JButton();
    jLabel9 = new javax.swing.JLabel();
    dcaExistingStrategies = new javax.swing.JComboBox<>();
    jLabel10 = new javax.swing.JLabel();
    dcaApplyBtn = new javax.swing.JButton();

    jLabel1.setText("DCA STRATEGY");
    jLabel2.setText("ENTER THE START DATE (MM/DD/YYYY)");
    jLabel3.setText("ENTER THE END DATE (MM/DD/YYYY)");
    jLabel4.setText("SELECT INVESTMENT OPTION");
    jLabel5.setText("ENTER THE AMOUNT TO BE INVESTED");
    jLabel6.setText("SELECT PORTFOLIO");
    dcaPortfolioId.setModel(new javax.swing.DefaultComboBoxModel(
            new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
    jLabel7.setText("ENTER THE COMMISSION RATE");
    dcaInvestmentOption.setModel(new javax.swing.DefaultComboBoxModel
            (new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
    dcaCustomStocksLbl.setText("Enter the custom Weight seperated by commas");
    jLabel8.setText("ENTER THE DAY FREQUENCY");
    dcaSave.setText("SAVE");
    jLabel9.setText("USE EXISTING STRATEGY");
    dcaExistingStrategies.setModel(new javax.swing.DefaultComboBoxModel<>
            (new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
    jLabel10.setText("(AMOUNT_COMMISSION_START DATE_END DATE_FREQUENCY)");
    dcaApplyBtn.setText("Apply");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup
                                    (javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(dcaStartDate,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcaEndDate,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcaAmountInvested,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcaCommissionRate,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcaCustomStocksLbl)
                                    .addGroup(layout.createParallelGroup
                                            (javax.swing.GroupLayout.Alignment.TRAILING,
                                                    false)
                                            .addComponent(dcaCustomWeight,
                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dcaInvestmentOption,
                                                    javax.swing.GroupLayout.Alignment.LEADING,
                                                    0, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)
                                            .addComponent(jLabel4,
                                                    javax.swing.GroupLayout.Alignment.LEADING,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE))
                                    .addComponent(jLabel8)
                                    .addComponent(dcaDayFrequency,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcaError)
                                    .addComponent(dcaSave)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup
                                                    (javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(dcaPortfolioId,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            138,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3))
                                            .addGap(108, 108, 108)
                                            .addGroup(layout.createParallelGroup
                                                    (javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(dcaApplyBtn)
                                                    .addGroup(layout.createParallelGroup
                                                            (javax.swing.GroupLayout
                                                                            .Alignment.LEADING,
                                                                    false)
                                                            .addComponent(jLabel9,
                                                                    javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE,
                                                                    170,
                                                                    javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE)
                                                            .addComponent(jLabel10,
                                                                    javax.swing.GroupLayout
                                                                            .DEFAULT_SIZE,
                                                                    javax.swing.GroupLayout
                                                                            .DEFAULT_SIZE,
                                                                    Short.MAX_VALUE)
                                                            .addComponent(dcaExistingStrategies,
                                                                    0, javax.swing.GroupLayout
                                                                            .DEFAULT_SIZE,
                                                                    Short.MAX_VALUE)))))
                            .addContainerGap(66, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addGap(17, 17, 17)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
                                    .Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
                                    .Alignment.BASELINE)
                                    .addComponent(dcaPortfolioId, javax.swing.GroupLayout
                                                    .PREFERRED_SIZE, 22,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
                                    .Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(dcaExistingStrategies,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                            javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing
                                    .GroupLayout.Alignment.BASELINE)
                                    .addComponent(dcaStartDate,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcaApplyBtn))
                            .addGap(13, 13, 13)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dcaEndDate, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dcaAmountInvested, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcaCommissionRate, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dcaDayFrequency, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcaInvestmentOption
                                    , javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcaCustomStocksLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dcaCustomWeight, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcaSave)
                            .addGap(56, 56, 56)
                            .addComponent(dcaError)
                            .addContainerGap(28, Short.MAX_VALUE))
    );
  }
  // End of variables declaration//GEN-END:variables

  @Override
  public void addActionListener(ActionListener listener) {
    dcaSave.addActionListener(listener);
    dcaApplyBtn.addActionListener(listener);
  }

  /**
   * The following method represents the combo box listener.
   *
   * @param listener the item listener.
   */
  @Override
  public void addComboBoxListener(ItemListener listener) {
    dcaInvestmentOption.addItemListener(listener);
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
   * The following method represents update stock conmbo box method.
   *
   * @param stocksInPortfolio the stocks in portfolio.
   */
  @Override
  public void updateStockComboBox(List<Stock> stocksInPortfolio) {

  }

  /**
   * The following method represents the text field data.
   *
   * @param fieldName the field name.
   * @param value     the value in the string.
   */
  @Override
  public void setTextFieldData(String fieldName, String value) {
    try {
      Object o = this; // The object you want to inspect
      Class<?> c = o.getClass();
      Field f = c.getDeclaredField(fieldName);
      f.setAccessible(true);
      JTextField textField = (JTextField) f.get(o);
      textField.setText(value);
    } catch (NoSuchFieldException | IllegalAccessException i) {
      throw new IllegalArgumentException("No such field found.\n");
    }
  }
}
