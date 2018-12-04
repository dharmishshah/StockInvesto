/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocks.view;

import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JTextField;

/**
 *
 * @author Dharmish
 */
public interface GUIView {
    
    
    String getPortfolioName();
    
    void addActionListener(ActionListener listener);
    
    void clearTextData(JTextField fieldName);
    
    void setSummaryData( Map<String, Map<String, Double>> data);
    
    
    
}
