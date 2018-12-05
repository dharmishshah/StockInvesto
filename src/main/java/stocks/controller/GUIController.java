package stocks.controller;
import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        private DateTimeFormatter formatter;
	      private GUIView createportfolioView;
        private GUIView displayPortfolioView;
        private GUIView buyStockByAmountView;
        private GUIView buyStockByVolumeView;
        private GUIView displayStockView;
        private GUIView oneTimeInvestmentView;
        private GUIView dcaInvestmentView;
	
	public GUIController()
	{   
            Map<String, Map<String, Map<String, Double>>> stockData = new HashMap<String, Map<String,
            Map<String, Double>>>();
            this.portfolioOperations = new PortfolioModel(stockData);
            this.formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	}

	public void setPortfolioCreateView(GUIView create)
	{
		this.createportfolioView = create;
		//create and set the keyboard listener
		configurePortfolioCreateButtonListener();
	}
        
        public void setPortfolioDisplayView(GUIView displayPortfolioView)
	{
		this.displayPortfolioView = displayPortfolioView;
		//create and set the keyboard listener
		configurePortfolioDisplayButtonListener();
	}
        
        
        public void setBuyStockByAmountView(GUIView buyStockByAmountView)
	{
		this.buyStockByAmountView = buyStockByAmountView;
		//create and set the keyboard listener
		configureBuyStockByAmountViewButtonListener();
	}
        
        
        public void setBuyStockByVolumeView(GUIView buyStockByVolumeView)
	{
		this.buyStockByVolumeView = buyStockByVolumeView;
		//create and set the keyboard listener
		configureBuyStockByVolumeButtonListener();
	}
        
        
        public void setStockDisplayView(GUIView displayStockView)
	{
		this.displayStockView = displayStockView;
		//create and set the keyboard listener
		configureStockDisplayButtonListener();
                
	}

  public void setOneTimeInvestmentView(GUIView oneTimeInvestmentView)
  {
    this.oneTimeInvestmentView = oneTimeInvestmentView;
    configureOneTimeInvestmentButtonListener();
    //create and set the keyboard listener
  }

  public void setDCAInvestmentView(GUIView dcaInvestmentView)
  {
    this.dcaInvestmentView = dcaInvestmentView;
    //create and set the keyboard listener
    configureBuyStockByAmountViewButtonListener();
  }
        
        
        public String getExistingPortfolios(){
           String portfolios = portfolioOperations.toString();
            if (portfolioOperations.toString().isEmpty()) {
                return "";
            } 
            return portfolios;
        }

    

	private void configurePortfolioCreateButtonListener() {
		Map<String,Runnable> buttonClickedMap = 
                        new HashMap<String,Runnable>();
		ButtonListener buttonListener = new ButtonListener();

		buttonClickedMap.put("createPortfolio",()->{
			String text = 
                                createportfolioView.getTextFieldData("portfolioNameTxt");
                        portfolioOperations.addPortfolio(text);
                        createportfolioView.clearTextFieldData("portfolioNameTxt");
		});
                buttonListener.setButtonClickedActionMap(buttonClickedMap);
                this.createportfolioView.addActionListener(buttonListener);
                
       
	}
        
        
        private void configurePortfolioDisplayButtonListener() {
		Map<String,Runnable> buttonClickedMap = 
                        new HashMap<String,Runnable>();
		ButtonListener buttonListener = new ButtonListener();
               
                 buttonClickedMap.put("viewPortfolio",()->{
			 Map<String, Map<String, Double>> resultMap = 
                                 portfolioOperations.displayPortfolios(
                                         LocalDate.now());
                         this.displayPortfolioView.setSummaryData(resultMap);
		});
                 buttonListener.setButtonClickedActionMap(buttonClickedMap);
                 this.displayPortfolioView.addActionListener(buttonListener);
	}
        
        
        private void configureBuyStockByAmountViewButtonListener() {
		Map<String,Runnable> buttonClickedMap = 
                        new HashMap<String,Runnable>();
		ButtonListener buttonListener = new ButtonListener();
               
                buttonClickedMap.put("BSASave",()->{
                String portfolioId = buyStockByAmountView.getComboFieldData("BSAPortfolioId");
                String tickerSymbol = buyStockByAmountView.getTextFieldData("BSATickerSymbolTxt");
                String amount = buyStockByAmountView.getTextFieldData("BSAAmountTxt");
                String date = buyStockByAmountView.getTextFieldData("BSADateTxt");
                String commissionRate = buyStockByAmountView.getTextFieldData("BSACommissionRateTxt");
               try {
            int portId = Integer.parseInt(portfolioId.split("\\.")[0]);
            double amountInvested = Double.parseDouble(amount);
            LocalDate d = LocalDate.parse(date,formatter);
            double commRate = Double.parseDouble(commissionRate);
            portfolioOperations.addStockByAmount(portId, tickerSymbol, amountInvested, d, commRate);
                     } catch (IllegalArgumentException iae) {
                        this.buyStockByAmountView.setErrorMessage("BSAErrorLbl", iae.getMessage());
                     }
                buyStockByAmountView.clearTextFieldData("BSATickerSymbolTxt");
                buyStockByAmountView.clearTextFieldData("BSAAmountTxt");
                buyStockByAmountView.clearTextFieldData("BSADateTxt");
                buyStockByAmountView.clearTextFieldData("BSACommissionRateTxt");
		});
                 buttonListener.setButtonClickedActionMap(buttonClickedMap);
                 this.buyStockByAmountView.addActionListener(buttonListener);
	}
        
        private void configureBuyStockByVolumeButtonListener() {
		Map<String,Runnable> buttonClickedMap = 
                        new HashMap<String,Runnable>();
		ButtonListener buttonListener = new ButtonListener();
               
                buttonClickedMap.put("BSVSave",()->{
                String portfolioId = buyStockByVolumeView.getComboFieldData("BSVPortfolioId");
                String tickerSymbol = buyStockByVolumeView.getTextFieldData("BSVTickerSymbolTxt");
                String volume = buyStockByVolumeView.getTextFieldData("BSVVolumeTxt");
                String date = buyStockByVolumeView.getTextFieldData("BSVDateTxt");
                String commissionRate = buyStockByVolumeView.getTextFieldData("BSVCommissionRateTxt");
               try {
            int portId = Integer.parseInt(portfolioId.split("\\.")[0]);
            double volumeInvested = Double.parseDouble(volume);
            LocalDate d = LocalDate.parse(date,formatter);
            double commRate = Double.parseDouble(commissionRate);
            portfolioOperations.addStock(portId, tickerSymbol, volumeInvested, d, commRate);
                     } catch (IllegalArgumentException iae) {
                        this.buyStockByVolumeView.setErrorMessage("BSVErrorLbl", iae.getMessage());
                     }
                buyStockByVolumeView.clearTextFieldData("BSVTickerSymbolTxt");
                buyStockByVolumeView.clearTextFieldData("BSVVolumeTxt");
                buyStockByVolumeView.clearTextFieldData("BSVDateTxt");
                buyStockByVolumeView.clearTextFieldData("BSVCommissionRateTxt");
		});
                 buttonListener.setButtonClickedActionMap(buttonClickedMap);
                 this.buyStockByVolumeView.addActionListener(buttonListener);
	}
        
        private void configureStockDisplayButtonListener() {
		Map<String,Runnable> buttonClickedMap = 
                        new HashMap<String,Runnable>();
		ButtonListener buttonListener = new ButtonListener();
               
                 buttonClickedMap.put("VPSViewStock",()->{
                     String portfolioId = displayStockView.getComboFieldData("VPSPortfolioId");
                      int portId = Integer.parseInt(portfolioId.split("\\.")[0]);
			 Map<String, Map<String, Double>> resultMap = 
                                 portfolioOperations.displayStocks(portId, LocalDate.now());
                         this.displayStockView.setSummaryData(resultMap);
		});
                 buttonListener.setButtonClickedActionMap(buttonClickedMap);
                 this.displayStockView.addActionListener(buttonListener);
	}


  private void configureOneTimeInvestmentButtonListener() {
    Map<String,Runnable> buttonClickedMap =
            new HashMap<String,Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("createOneTime",()->{
      String text = oneTimeInvestmentView.getTextFieldData("portfolioNameTxt");
      portfolioOperations.addPortfolio(text);
      oneTimeInvestmentView.clearTextFieldData("portfolioNameTxt");
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.oneTimeInvestmentView.addActionListener(buttonListener);
  }



  private void configureDCAInvestmentButtonListener() {
    Map<String,Runnable> buttonClickedMap =
            new HashMap<String,Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("createDCA",()->{
      String text =
              dcaInvestmentView.getTextFieldData("portfolioNameTxt");
      portfolioOperations.addPortfolio(text);
      dcaInvestmentView.clearTextFieldData("portfolioNameTxt");
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.dcaInvestmentView.addActionListener(buttonListener);


  }
        

}