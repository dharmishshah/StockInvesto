package stocks.controller;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

 

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.time.DateTimeException;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import stocks.listener.ButtonListener;

import stocks.model.StockConstants;

import stocks.model.portfolio.Portfolio;

import stocks.model.portfolio.PortfolioModel;

import stocks.model.portfolio.PortfolioOperations;

import stocks.model.stock.Stock;

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

            portfolios = portfolios.replaceAll("\n\n", "\n");

            return portfolios;

        }

 

   

 

                private void configurePortfolioCreateButtonListener() {

           

                                Map<String,Runnable> buttonClickedMap =

                        new HashMap<String,Runnable>();

                                ButtonListener buttonListener = new ButtonListener();

 

                                buttonClickedMap.put("createPortfolio",()->{

                    createportfolioView.setErrorMessage("PCErrorLbl",

                                    "");

                                                String text =

                                createportfolioView.getTextFieldData("portfolioNameTxt");

                        try{

                             portfolioOperations.addPortfolio(text);

                        }catch(IllegalArgumentException iae){

                            createportfolioView.setErrorMessage("PCErrorLbl",

                                    iae.getMessage());

                        }

                      

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

                     LocalDate d;

                     displayPortfolioView.setErrorMessage("PVErrorLbl",

                                    "");

                     try{

                         LocalDate date = validateDate(displayPortfolioView, "PVDateTxt");

        

                                                 Map<String, Map<String, Double>> resultMap =

                                 portfolioOperations.displayPortfolios(date);

                         this.displayPortfolioView.setSummaryData(resultMap);

                     }catch(IllegalArgumentException iae){

                         displayPortfolioView.setErrorMessage("PVErrorLbl",

                                    iae.getMessage());

                     }

                    

                                });

                 buttonListener.setButtonClickedActionMap(buttonClickedMap);

                 this.displayPortfolioView.addActionListener(buttonListener);

                }

       

        

        private void configureBuyStockByAmountViewButtonListener() {

                                Map<String,Runnable> buttonClickedMap =

                        new HashMap<String,Runnable>();

                                ButtonListener buttonListener = new ButtonListener();

              

                buttonClickedMap.put("BSASave",()->{

                    

                 try{

                     buyStockByAmountView.setErrorMessage("BSAErrorLbl",

                                    "");

            int portId = validatePortfolio(buyStockByAmountView, "BSAPortfolioId");

            String tickerSymbol = validateTickerSymbol(buyStockByAmountView,"BSATickerSymbolTxt");

             double commRate = validateValue(buyStockByAmountView, "BSACommissionRateTxt", "commissionRate");

            double amountInvested = validateValue(buyStockByAmountView, "BSAAmountTxt", "amount");

           LocalDate d = validateDate(buyStockByAmountView, "BSADateTxt");

          

            portfolioOperations.addStockByAmount(portId, tickerSymbol, amountInvested, d, commRate);

               

                buyStockByAmountView.clearTextFieldData("BSATickerSymbolTxt");

                buyStockByAmountView.clearTextFieldData("BSAAmountTxt");

                buyStockByAmountView.clearTextFieldData("BSADateTxt");

                buyStockByAmountView.clearTextFieldData("BSACommissionRateTxt");

                 }catch(IllegalArgumentException iae){

                      this.buyStockByAmountView.setErrorMessage("BSAErrorLbl", iae.getMessage());

                 }

               

                                });

                 buttonListener.setButtonClickedActionMap(buttonClickedMap);

                 this.buyStockByAmountView.addActionListener(buttonListener);

                }

 

        private void configureBuyStockByVolumeButtonListener() {

                                Map<String,Runnable> buttonClickedMap =

                        new HashMap<String,Runnable>();

                                ButtonListener buttonListener = new ButtonListener();

              

                buttonClickedMap.put("BSVSave",()->{

                    try{

                     buyStockByVolumeView.setErrorMessage("BSVErrorLbl",

                                    "");

                   

           String tickerSymbol = validateTickerSymbol(buyStockByAmountView,"BSVTickerSymbolTxt");

            int portId = validatePortfolio(buyStockByVolumeView, "BSVPortfolioId");

            double volumeInvested = validateValue(buyStockByVolumeView, "BSVVolumeTxt", "volume");

           LocalDate d = validateDate(buyStockByVolumeView, "BSVDateTxt");

            double commRate = validateValue(buyStockByVolumeView, "BSVCommissionRateTxt", "commissionRate");

           

            portfolioOperations.addStock(portId, tickerSymbol, volumeInvested, d, commRate);

               

                buyStockByVolumeView.clearTextFieldData("BSVTickerSymbolTxt");

                buyStockByVolumeView.clearTextFieldData("BSVVolumeTxt");

                buyStockByVolumeView.clearTextFieldData("BSVDateTxt");

                buyStockByVolumeView.clearTextFieldData("BSVCommissionRateTxt");

                 }catch(IllegalArgumentException iae){

                      this.buyStockByVolumeView.setErrorMessage("BSVErrorLbl", iae.getMessage());

                 }

 

                                });

                 buttonListener.setButtonClickedActionMap(buttonClickedMap);

                 this.buyStockByVolumeView.addActionListener(buttonListener);

                }

       

        private void configureStockDisplayButtonListener() {

                                Map<String,Runnable> buttonClickedMap =

                        new HashMap<String,Runnable>();

                                ButtonListener buttonListener = new ButtonListener();

              

                 buttonClickedMap.put("VPSViewStock",()->{

                     LocalDate d;

                     displayStockView.setErrorMessage("VPSErrorLbl",

                                    "");

                     try{

                         int portId = validatePortfolio(displayStockView, "VPSPortfolioId");

                        LocalDate date = validateDate(displayStockView, "VPSDateTxt");

                     String portfolioId = displayStockView.

                             getComboFieldData("VPSPortfolioId");

                                                Map<String, Map<String, Double>> resultMap =

                                 portfolioOperations.displayStocks(portId, date);

                         this.displayStockView.setSummaryData(resultMap);

                     }catch(IllegalArgumentException iae){

                         displayStockView.setErrorMessage("VPSErrorLbl",

                                    iae.getMessage());

                     }

                    

                                });

                 buttonListener.setButtonClickedActionMap(buttonClickedMap);

                 this.displayStockView.addActionListener(buttonListener);

                }

       

        private void configureOneTimeInvestmentButtonListener() {

            Map<String,Runnable> buttonClickedMap =

                    new HashMap<String,Runnable>();

            ButtonListener buttonListener = new ButtonListener();

            buttonClickedMap.put("createOneTime",()->{

                String portfolioId

                        = oneTimeInvestmentView.getComboFieldData("oneTimePortfolioId");

               

                String date

                        = oneTimeInvestmentView.getTextFieldData("oneInvestmentDate");

               

                String amount

                        = oneTimeInvestmentView.getTextFieldData("oneAmountInvested");

               

                String commissionRate

                        = oneTimeInvestmentView.getTextFieldData("oneCommissionRate");

               

                String investmentOption

                        = oneTimeInvestmentView.getComboFieldData("oneInvestmentOption");

                try {

                    int portId = Integer.parseInt(portfolioId.split("\\.")[0]);

                    double amountInvested = Double.parseDouble(amount);

                    double commission = Double.parseDouble(commissionRate);

                    LocalDate d = LocalDate.parse(date,formatter);

                    if (investmentOption.matches("EQUAL")) {

                        List<Stock> stocks =

                             portfolioOperations.viewPortfolioStocks(portId, d);

                        equalWeightedInvestment(stocks, amountInvested,

                                portId, d, commission);}  

                }catch (IllegalArgumentException iae) {

                        this.oneTimeInvestmentView

                                .setErrorMessage("ERROR IN INPUT",

                                        iae.getMessage());

                     }

                oneTimeInvestmentView.clearTextFieldData("oneAmountInvested");

                oneTimeInvestmentView.clearTextFieldData("oneInvestmentDate");

                oneTimeInvestmentView.clearTextFieldData("oneCommissionRate");

               

                                });

                buttonListener.setButtonClickedActionMap(buttonClickedMap);

                this.oneTimeInvestmentView.addActionListener(buttonListener);

                }

       

        private void configureDCAInvestmentButtonListener() {

            Map<String,Runnable> buttonClickedMap =new HashMap<String,Runnable>();

            ButtonListener buttonListener = new ButtonListener();

            buttonClickedMap.put("createDCA",()->{

                String portfolioId = dcaInvestmentView.getComboFieldData("dcaPortfolioId");

                String start = dcaInvestmentView.getTextFieldData("dcaStartDate");

                String end = dcaInvestmentView.getTextFieldData("dcaEndDate");

                String amount = dcaInvestmentView.getTextFieldData("dcaAmountInvested");

                String commission = dcaInvestmentView.getTextFieldData("dcaCommissionRate");

                String investOption = dcaInvestmentView.getComboFieldData("dcaInvestmentOption");

                try {

                    int portId = Integer.parseInt(portfolioId.split("\\.")[0]);

                    double amountInvested = Double.parseDouble(amount);

                    double commissionRate = Double.parseDouble(commission);

                    LocalDate d1 = LocalDate.parse(start,formatter);

                    LocalDate d2 = LocalDate.parse(end,formatter);

                    if (investOption.matches("EQUAL")) {

                        List<Stock> stocks =

                             portfolioOperations.viewPortfolioStocks(portId, d1);

                        equalWeightedInvestment(stocks, amountInvested,

                                portId, d1, commissionRate);}  

                }catch (IllegalArgumentException iae) {

                        this.oneTimeInvestmentView

                                .setErrorMessage("ERROR IN INPUT",

                                        iae.getMessage());

                }

                dcaInvestmentView.clearTextFieldData("oneInvestmentDate");

                dcaInvestmentView.clearTextFieldData("dcaStartDate");

                dcaInvestmentView.clearTextFieldData("dcaEndDate");

                dcaInvestmentView.clearTextFieldData("dcaCommissionRate");

            });

            buttonListener.setButtonClickedActionMap(buttonClickedMap);

            this.dcaInvestmentView.addActionListener(buttonListener);

        }

      

        /**

         * The following method invests in the portfolio by equally distributing

         * amount in each stock

         * @param stocks the list of stocks in which amount is to be invested.

         * @param amountInvested the amount invested in portfolio.

         * @param portfolioId the id of the portfolio.

         * @param d the date at which the investment took place.

         * @param commissionRate the commission charged for the transaction

         */

        private void equalWeightedInvestment (List<Stock> stocks,

                double amountInvested, int portfolioId, LocalDate d,

                double commissionRate) {

          

            double amountInvestedInEachStock = amountInvested/stocks.size();

            for (int i=0; i<stocks.size(); i++) {

                String tickerSymbol = stocks.get(i).getTickerSymbol();

                portfolioOperations.addStockByAmount(portfolioId,

                        tickerSymbol, amountInvestedInEachStock

                        , d, commissionRate);

            }

        }

 

  private int validatePortfolio(GUIView view, String field){

      

            String portfolioString = view.getComboFieldData(field);

            try{

                if(portfolioString.equals("Select")){

                throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);

            }

                return Integer.parseInt(portfolioString.split("\\.")[0]);

            }catch(NumberFormatException nbe){

                 throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);

            }

        }

 

  private String validateTickerSymbol(GUIView view, String field){

      

            String tickerSymbolString = view.getTextFieldData(field);

            if(tickerSymbolString == null || tickerSymbolString.isEmpty()){

                throw new IllegalArgumentException(StockConstants.ERROR_INVALID_TICKER_SYMBOL);

            }

           

            return tickerSymbolString;

      

        }

       

        

        private LocalDate validateDate(GUIView view, String field){

            try{

                String date = view.getTextFieldData(field);

             return LocalDate.parse(date,formatter);

            }catch(DateTimeException dte){

                throw new IllegalArgumentException(StockConstants.ERROR_INVALID_DATE);

            }

           

        }

       

        private double validateValue(GUIView view, String field,String type){

            try{

                String value = view.getTextFieldData(field);

             return Double.parseDouble(value);

            }catch(NumberFormatException dte){

                if(type.equalsIgnoreCase("amount")){

                    throw new IllegalArgumentException(StockConstants.ERROR_INVALID_AMOUNT);

                }else if(type.equalsIgnoreCase("commissionRate")){

                    throw new IllegalArgumentException(StockConstants.ERROR_INVALID_COMMISSION);

                }else if(type.equalsIgnoreCase("volume")){

                    throw new IllegalArgumentException(StockConstants.ERROR_INVALID_VOLUME);

                }else{

                     throw new IllegalArgumentException(StockConstants.ERROR_INVALID_GENERIC_VALUE);

                }

              

            }

        }

       

        

       

        

}