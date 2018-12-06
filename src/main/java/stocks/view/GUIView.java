/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocks.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import stocks.model.stock.Stock;

/**
 *
 * @author Dharmish
 */
public interface GUIView {
    
    
    void addActionListener(ActionListener listener);

    default void addComboBoxListener(ItemListener listener){

    }
    
    void setSummaryData( Map<String, Map<String, Double>> data);

    default String getTextFieldData(String fieldName){
        try{
            Object o = this; // The object you want to inspect
            Class<?> c = o.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            JTextField portfolioName = (JTextField) f.get(o);
            return portfolioName.getText();
        }catch(NoSuchFieldException | IllegalAccessException i){
            throw new IllegalArgumentException("No such field found.\n");
        }
    }
    
    default void setLabelFieldData(String fieldName,String message){
        try{
            Object o = this; // The object you want to inspect
            Class<?> c = o.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            JLabel portfolioName = (JLabel) f.get(o);
            portfolioName.setText(message);
        }catch(NoSuchFieldException | IllegalAccessException i){
            throw new IllegalArgumentException("No such field found.\n");
        }
    }
    
    default String getComboFieldData(String fieldName){
        try{
            Object o = this; // The object you want to inspect
            Class<?> c = o.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            JComboBox<String> portfolioName = (JComboBox<String>) f.get(o);
            return (String)portfolioName.getSelectedItem();
        }catch(NoSuchFieldException | IllegalAccessException i){
            throw new IllegalArgumentException("No such field found.\n");
        }
    }
    
    default void clearTextFieldData(String fieldName){
        try{
            Object o = this; // The object you want to inspect
            Class<?> c = o.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            JTextField portfolioName = (JTextField) f.get(o);
            portfolioName.setText("");
        }catch(NoSuchFieldException | IllegalAccessException i){
            throw new IllegalArgumentException("No such field found.\n");
        }
    }
    
    default void setErrorMessage(String fieldName, String message){
        try{
            Object o = this; // The object you want to inspect
            Class<?> c = o.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            JLabel portfolioName = (JLabel) f.get(o);
            portfolioName.setText(message);
        }catch(NoSuchFieldException | IllegalAccessException i){
            throw new IllegalArgumentException("No such field found.\n");
        }
    }


    default void updateStockComboBox (List<Stock> stocksInPortfolio){

    }
   
    
    
    
}
