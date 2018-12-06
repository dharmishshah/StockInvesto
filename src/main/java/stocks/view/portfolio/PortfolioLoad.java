package stocks.view.portfolio;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import stocks.controller.GUIController;
import stocks.view.GUIView;

/**
 * The following method represents portfolio load panel.
 */
public class PortfolioLoad extends javax.swing.JPanel implements GUIView {

  GUIController controller;
  private javax.swing.JLabel PLErrorLbl;
  private javax.swing.JButton PLLoadAllBtn;
  private javax.swing.JButton PLLoadBtn;
  private javax.swing.JComboBox<String> PLPortfolioId;
  private javax.swing.JLabel jLabel1;
  /**
   * The following constructor initializes the portfolio load operation.
   *
   * @param controller the GUIController object.
   */
  public PortfolioLoad(GUIController controller) {
    initComponents();
    this.controller = controller;
    controller.setPortfolioLoadView(this);

    PLLoadBtn.setActionCommand("PLLoad");
    PLLoadAllBtn.setActionCommand("PLLoadAll");

    PLPortfolioId.removeAllItems();
    PLPortfolioId.addItem("Select");
    List<String> portfolios = controller.getSavedPortfolios();
    for (String port : portfolios) {
      PLPortfolioId.addItem(port);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    PLPortfolioId = new javax.swing.JComboBox<>();
    PLLoadBtn = new javax.swing.JButton();
    PLLoadAllBtn = new javax.swing.JButton();
    PLErrorLbl = new javax.swing.JLabel();

    jLabel1.setText("SELECT PORTFOLIO");

    PLPortfolioId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]
            {"Item 1", "Item 2", "Item 3", "Item 4"}));

    PLLoadBtn.setText("Load");

    PLLoadAllBtn.setText("Load All");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(PLLoadBtn)
                                            .addPreferredGap(javax.swing.LayoutStyle.
                                                    ComponentPlacement.UNRELATED)
                                            .addComponent(PLLoadAllBtn))
                                    .addGroup(layout.createParallelGroup(javax.swing.
                                            GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(PLPortfolioId, javax.swing.GroupLayout
                                                            .Alignment.LEADING, 0,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)
                                            .addComponent(jLabel1,
                                                    javax.swing.GroupLayout.Alignment.LEADING,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE))
                                    .addComponent(PLErrorLbl))
                            .addContainerGap(256, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(PLPortfolioId, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing
                                    .GroupLayout.Alignment.BASELINE)
                                    .addComponent(PLLoadBtn)
                                    .addComponent(PLLoadAllBtn))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(PLErrorLbl)
                            .addContainerGap(209, Short.MAX_VALUE))
    );
  }

  /**
   * The following method represents the ActionListener method.
   *
   * @param listener the action listener object.
   */
  @Override
  public void addActionListener(ActionListener listener) {
    PLLoadBtn.addActionListener(listener);
    PLLoadAllBtn.addActionListener(listener);
  }

  /**
   * The following method represents the summary display method.
   *
   * @param data the data of the stock.
   */
  @Override
  public void setSummaryData(Map<String, Map<String, Double>> data) {

  }
}
