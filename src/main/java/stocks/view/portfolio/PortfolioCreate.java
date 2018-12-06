
package stocks.view.portfolio;

import java.awt.event.ActionListener;
import java.util.Map;

import stocks.controller.GUIController;
import stocks.view.GUIView;

/**
 * The following class represents the create portfolio panel.
 */
public class PortfolioCreate extends javax.swing.JPanel implements GUIView {
  GUIController controller;

  /**
   * Creates new form PortfolioCreate
   */
  public PortfolioCreate(GUIController controller) {
    initComponents();
    controller.setPortfolioCreateView(this);
    savePortfolioBtn.setActionCommand("createPortfolio");
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    portfolioNameTxt = new javax.swing.JTextField();
    savePortfolioBtn = new javax.swing.JButton();
    PCErrorLbl = new javax.swing.JLabel();

    jLabel1.setText("Enter portfolio name");

    portfolioNameTxt.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        portfolioNameTxtActionPerformed(evt);
      }
    });

    savePortfolioBtn.setText("Save");
    savePortfolioBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        savePortfolioBtnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                                    .LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(portfolioNameTxt,
                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                            125, Short.MAX_VALUE)
                                    .addComponent(savePortfolioBtn)
                                    .addComponent(PCErrorLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(265, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(portfolioNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(savePortfolioBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(PCErrorLbl)
                            .addContainerGap(204, Short.MAX_VALUE))
    );
  }
  /**
   * The following method represents the action listener operations.
   * @param listener the listener object of the ActionListener.
   */
  public void addActionListener(ActionListener listener) {
    savePortfolioBtn.addActionListener(listener);
  }

  /**
   * The following method represents the summary data method.
   * @param data the data of the stock.
   */
  @Override
  public void setSummaryData(Map<String, Map<String, Double>> data) {
  }

  /**
   * The following method represents the save portfolio method.
   * @param evt the action event object.
   */

  private void savePortfolioBtnActionPerformed(java.awt.event.ActionEvent evt) {
  }

  /**
   * The following method represents the portfolio name method.
   * @param evt the action event object.
   */
  private void portfolioNameTxtActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private javax.swing.JLabel PCErrorLbl;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField portfolioNameTxt;
  private javax.swing.JButton savePortfolioBtn;
}
