/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocks.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jfree.data.category.DefaultCategoryDataset;

import stocks.model.stock.Stock;
import stocks.view.portfolio.Graph;

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
            JTextField textField = (JTextField) f.get(o);
            return textField.getText();
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
            JLabel labelField = (JLabel) f.get(o);
            labelField.setText(message);
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
            JComboBox<String> comboBox = (JComboBox<String>) f.get(o);
            return (String)comboBox.getSelectedItem();
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
            JTextField fieldtext = (JTextField) f.get(o);
            fieldtext.setText("");
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
            JLabel fieldLabel = (JLabel) f.get(o);
            fieldLabel.setText(message);
            fieldLabel.setForeground(Color.red);
        }catch(NoSuchFieldException | IllegalAccessException i){
            throw new IllegalArgumentException("No such field found.\n");
        }
    }
    
    
    default void setSuccessMessage(String fieldName, String message){
        try{
            Object o = this; // The object you want to inspect
            Class<?> c = o.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            JLabel fieldLabel = (JLabel) f.get(o);
            fieldLabel.setText(message);
            fieldLabel.setForeground(Color.green);
        }catch(NoSuchFieldException | IllegalAccessException i){
            throw new IllegalArgumentException("No such field found.\n");
        }
    }


    default void updateStockComboBox (List<Stock> stocksInPortfolio){

    }
    
    
    default void plotGraph(String plotName, String XAxis, String YAxis,
            DefaultCategoryDataset dataset){
        
    }
   
    
    
    
}
