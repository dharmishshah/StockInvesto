package stocks.view.portfolio;

import java.awt.event.ActionListener;

import stocks.controller.GUIController;
import stocks.view.GUIView;

/**
 * The following class represents the create portfolio panel.
 */
public class PortfolioCreate extends javax.swing.JPanel implements GUIView {
  GUIController controller;

  private javax.swing.JButton savePortfolioBtn;

  private javax.swing.JLabel pCErrorLbl;

  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField portfolioNameTxt;


  /**
   * Creates new form PortfolioCreate.
   */
  public PortfolioCreate(GUIController controller) {
    initComponents();
    this.controller = controller;
    this.controller.setPortfolioCreateView(this);
    savePortfolioBtn.setActionCommand("createPortfolio");
    this.pCErrorLbl = pCErrorLbl;
    this.jLabel1 = jLabel1;
    this.portfolioNameTxt = portfolioNameTxt;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {




    jLabel1 = new javax.swing.JLabel();
    portfolioNameTxt = new javax.swing.JTextField();
    savePortfolioBtn = new javax.swing.JButton();
    pCErrorLbl = new javax.swing.JLabel();

    jLabel1.setText("Enter portfolio name");

    portfolioNameTxt.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        //portfolioNameTxtActionPerformed(evt);
      }
    });

    savePortfolioBtn.setText("Save");
    savePortfolioBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        //savePortfolioBtnActionPerformed(evt);
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
                                    .addComponent(pCErrorLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
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
                            .addComponent(pCErrorLbl)
                            .addContainerGap(204, Short.MAX_VALUE))
    );
  }

  /**
   * The following method represents the action listener operations.
   *
   * @param listener the listener object of the ActionListener.
   */
  public void addActionListener(ActionListener listener) {
    savePortfolioBtn.addActionListener(listener);
  }
}
