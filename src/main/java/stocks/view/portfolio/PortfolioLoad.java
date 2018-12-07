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
  
  private javax.swing.JButton pLLoadAllBtn;
  private javax.swing.JButton pLLoadBtn;
  private javax.swing.JComboBox<String> pLPortfolioId;
  private javax.swing.JLabel pLErrorLbl;
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

    pLLoadBtn.setActionCommand("PLLoad");
    pLLoadAllBtn.setActionCommand("PLLoadAll");

    pLPortfolioId.removeAllItems();
    pLPortfolioId.addItem("Select");
    List<String> portfolios = controller.getSavedPortfolios();
    for (String port : portfolios) {
      pLPortfolioId.addItem(port);
    }
    this.pLErrorLbl = pLErrorLbl;
    this.jLabel1 = jLabel1;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {


    jLabel1 = new javax.swing.JLabel();
    pLPortfolioId = new javax.swing.JComboBox<>();
    pLLoadBtn = new javax.swing.JButton();
    pLLoadAllBtn = new javax.swing.JButton();
    pLErrorLbl = new javax.swing.JLabel();

    jLabel1.setText("SELECT PORTFOLIO");

    pLPortfolioId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]
            {"Item 1", "Item 2", "Item 3", "Item 4"}));

    pLLoadBtn.setText("Load");

    pLLoadAllBtn.setText("Load All");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(pLLoadBtn)
                                            .addPreferredGap(javax.swing.LayoutStyle.
                                                    ComponentPlacement.UNRELATED)
                                            .addComponent(pLLoadAllBtn))
                                    .addGroup(layout.createParallelGroup(javax.swing.
                                            GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(pLPortfolioId, javax.swing.GroupLayout
                                                            .Alignment.LEADING, 0,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)
                                            .addComponent(jLabel1,
                                                    javax.swing.GroupLayout.Alignment.LEADING,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE))
                                    .addComponent(pLErrorLbl))
                            .addContainerGap(256, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pLPortfolioId, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing
                                    .GroupLayout.Alignment.BASELINE)
                                    .addComponent(pLLoadBtn)
                                    .addComponent(pLLoadAllBtn))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pLErrorLbl)
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
    pLLoadBtn.addActionListener(listener);
    pLLoadAllBtn.addActionListener(listener);
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
