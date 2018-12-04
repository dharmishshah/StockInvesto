package stocks.controller;
import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import stocks.listener.ButtonListener;
import stocks.model.portfolio.Portfolio;
import stocks.model.portfolio.PortfolioModel;
import stocks.model.portfolio.PortfolioOperations;
import stocks.view.GUIView;

public class GUIController
{
	 //Variable represents the portfolio operations.
        private final PortfolioOperations<Portfolio> portfolioOperations;
	private GUIView createportfolioView;
        private GUIView displayPortfolioView;
	
	public GUIController()
	{   
            Map<String, Map<String, Map<String, Double>>> stockData = new HashMap<String, Map<String,
            Map<String, Double>>>();
            this.portfolioOperations = new PortfolioModel(stockData);
	}

	public void setCreateView(GUIView create)
	{
		createportfolioView = create;
		//create and set the keyboard listener
		configurePortfolioCreateButtonListener();
	}
        
        public void setDisplayView(GUIView create)
	{
		displayPortfolioView = create;
		//create and set the keyboard listener
		configurePortfolioDisplayButtonListener();
	}

    

	private void configurePortfolioCreateButtonListener() {
		Map<String,Runnable> buttonClickedMap = 
                        new HashMap<String,Runnable>();
		ButtonListener buttonListener = new ButtonListener();

		buttonClickedMap.put("Save",()->{
			String text = createportfolioView.getPortfolioName();
                        portfolioOperations.addPortfolio(text);
		});
                buttonListener.setButtonClickedActionMap(buttonClickedMap);
                this.createportfolioView.addActionListener(buttonListener);
                
       
	}
        
        
        private void configurePortfolioDisplayButtonListener() {
		Map<String,Runnable> buttonClickedMap = 
                        new HashMap<String,Runnable>();
		ButtonListener buttonListener = new ButtonListener();
               
                 buttonClickedMap.put("View",()->{
			 Map<String, Map<String, Double>> resultMap = 
                                 portfolioOperations.displayPortfolios(
                                         LocalDate.now());
                         this.displayPortfolioView.setSummaryData(resultMap);
		});
                 buttonListener.setButtonClickedActionMap(buttonClickedMap);
                 this.displayPortfolioView.addActionListener(buttonListener);
	}


}