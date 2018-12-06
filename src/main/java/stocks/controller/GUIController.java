package stocks.controller;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;

import stocks.listener.ButtonListener;
import stocks.listener.ComboBoxItemListener;
import stocks.model.StockConstants;
import stocks.model.portfolio.Portfolio;
import stocks.model.portfolio.PortfolioModel;
import stocks.model.portfolio.PortfolioOperations;
import stocks.model.stock.Stock;
import stocks.model.strategy.DCA;
import stocks.model.strategy.Strategy;
import stocks.model.strategy.StrategyOperations;
import stocks.view.GUIView;

/**
 * The following class represents the controller of the GUI.
 * A GUI controller accepts the data from the GUI view interface and accepts the data
 * processed by the models.
 */
public class GUIController {
  //The following the variable represents the portfolio operations.
  private final PortfolioOperations<Portfolio> portfolioOperations;
  //The following the variable represents the strategy operations.
  private final StrategyOperations strategyOperations;
  //The following variable represents the date formatter.
  private DateTimeFormatter formatter;
  //The following variable represents the create portfolio view.
  private GUIView createportfolioView;
  //The following variable represents the display portfolio view.
  private GUIView displayPortfolioView;
  //The following variable represents the buy stock view.
  private GUIView buyStockByAmountView;
  //The following variable represents the buy amount view.
  private GUIView buyStockByVolumeView;
  //The following variable represents the display stock view.
  private GUIView displayStockView;
  //The following variable represents the one time investment view.
  private GUIView oneTimeInvestmentView;
  //The following variable represents the dca investment view.
  private GUIView dcaInvestmentView;
  //The following variable represents the save portfolio view.
  private GUIView portfolioSaveView;
  //The following variable represents the load portfolio view.
  private GUIView portfolioLoadView;
  //The following variable represents the graph portfolio view.
  private GUIView portfolioGraphView;

  /**
   * The following constructor initializes the gui controller.
   */
  public GUIController() {
    Map<String, Map<String, Map<String, Double>>> stockData =
            new HashMap<String, Map<String, Map<String, Double>>>();
    this.portfolioOperations = new PortfolioModel(stockData);
    this.formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    this.strategyOperations = new DCA(portfolioOperations);

  }

  /**
   * The following method initializes the portfolio create view.
   * @param create the GUIView object.
   */
  public void setPortfolioCreateView(GUIView create) {

    this.createportfolioView = create;
    //create and set the keyboard listener
    configurePortfolioCreateButtonListener();

  }

  /**
   * The following method initializes the display portfolio view.
   * @param displayPortfolioView the GUIView object.
   */
  public void setPortfolioDisplayView(GUIView displayPortfolioView) {

    this.displayPortfolioView = displayPortfolioView;
    //create and set the keyboard listener
    configurePortfolioDisplayButtonListener();

  }

  /**
   * The following method initializes the buy stock amount view.
   * @param buyStockByAmountView the GUIView object.
   */
  public void setBuyStockByAmountView(GUIView buyStockByAmountView) {

    this.buyStockByAmountView = buyStockByAmountView;
    //create and set the keyboard listener
    configureBuyStockByAmountViewButtonListener();
  }

  /**
   * The following method represents buy stock volume view.
   * @param buyStockByVolumeView the GUIView object.
   */
  public void setBuyStockByVolumeView(GUIView buyStockByVolumeView) {

    this.buyStockByVolumeView = buyStockByVolumeView;
    //create and set the keyboard listener
    configureBuyStockByVolumeButtonListener();

  }

  /**
   * The following method represents the display stock view.
   * @param displayStockView the GUIView object.
   */

  public void setStockDisplayView(GUIView displayStockView) {

    this.displayStockView = displayStockView;
    //create and set the keyboard listener
    configureStockDisplayButtonListener();


  }

  /**
   * The following method represents the one time investment view.
   * @param oneTimeInvestmentView the GUIView object.
   */
  public void setOneTimeInvestmentView(GUIView oneTimeInvestmentView) {
    this.oneTimeInvestmentView = oneTimeInvestmentView;
    configureOneTimeInvestmentButtonListener();
  }

  /**
   * The following method represents the dca investment view.
   * @param dcaInvestmentView the GUIView object.
   */
  public void setDCAInvestmentView(GUIView dcaInvestmentView) {
    this.dcaInvestmentView = dcaInvestmentView;
    //create and set the keyboard listener
    configureDCAInvestmentButtonListener();
  }

  /**
   * The following method represents the portfolio graph view.
   * @param portfolioGraphView the GUIView object.
   */
  public void setPortfolioGraphView(GUIView portfolioGraphView) {

    this.portfolioGraphView = portfolioGraphView;
    //create and set the keyboard listener
    configurePortfolioGraphButtonListener();
  }

  /**
   * The following method represents the portfolio load view.
   * @param portfolioLoadView the GUIView object.
   */
  public void setPortfolioLoadView(GUIView portfolioLoadView) {
    this.portfolioLoadView = portfolioLoadView;
    //create and set the keyboard listener
    configurePortfolioLoadButtonListener();
  }

  /**
   * The following method represents the portfolio save view.
   * @param portfolioSaveView the GUIView object.
   */
  public void setPortfolioSaveView(GUIView portfolioSaveView) {
    this.portfolioSaveView = portfolioSaveView;
    //create and set the keyboard listener
    configurePortfolioSaveButtonListener();
  }

  /**
   * The following method configures the create portfolio button.
   * It creates the new portfolio.
   */
  private void configurePortfolioCreateButtonListener() {

    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    //Initializing the button listener.
    ButtonListener buttonListener = new ButtonListener();
    //Map the operations for the button.
    buttonClickedMap.put("createPortfolio", () -> {
      createportfolioView.setErrorMessage("PCErrorLbl",
              "");
      //Accept the portfolio name.
      String text =
              createportfolioView.getTextFieldData("portfolioNameTxt");

      try {
        portfolioOperations.addPortfolio(text);
        createportfolioView.setSuccessMessage("PCErrorLbl",
                "Portfolio Created Successfully.");
      } catch (IllegalArgumentException iae) {
        createportfolioView.setErrorMessage("PCErrorLbl",
                iae.getMessage());

      }
      //Clear the text field box.
      createportfolioView.clearTextFieldData("portfolioNameTxt");
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.createportfolioView.addActionListener(buttonListener);

  }

  /**
   * The following method configures the display button listener.
   * It displays the portfolio.
   */
  private void configurePortfolioDisplayButtonListener() {

    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    //initialize the button listener.
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("viewPortfolio", () -> {
      LocalDate d;
      displayPortfolioView.setErrorMessage("PVErrorLbl",
              "");

      try {
        LocalDate date = validateDate(displayPortfolioView, "PVDateTxt");
        Map<String, Map<String, Double>> resultMap =
                portfolioOperations.displayPortfolios(date);
        this.displayPortfolioView.setSummaryData(resultMap);

      } catch (IllegalArgumentException iae) {
        displayPortfolioView.setErrorMessage("PVErrorLbl",
                iae.getMessage());

      }

    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.displayPortfolioView.addActionListener(buttonListener);

  }

  /**
   * The following method configures the buy stock button listener.
   * It buys stock by amount.
   */
  private void configureBuyStockByAmountViewButtonListener() {

    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("BSASave", () -> {


      try {

        buyStockByAmountView.setErrorMessage("BSAErrorLbl",
                "");
        //Accept the portfolio.
        int portId = validatePortfolio(buyStockByAmountView, "BSAPortfolioId");
        //Accept the ticker symbol.
        String tickerSymbol = validateTickerSymbol(buyStockByAmountView, "BSATickerSymbolTxt");
        //Accept the commission rate.
        double commRate = validateValue(buyStockByAmountView,
                "BSACommissionRateTxt", "commissionRate");
        //Accept the amount invested.
        double amountInvested = validateValue(buyStockByAmountView,
                "BSAAmountTxt", "amount");
        //Accept the date.
        LocalDate d = validateDate(buyStockByAmountView, "BSADateTxt");
        //Use the addStockAmount of the portfolio operations.
        portfolioOperations.addStockByAmount(portId, tickerSymbol, amountInvested, d, commRate);
        //Clear the text field of ticker symbol.
        buyStockByAmountView.clearTextFieldData("BSATickerSymbolTxt");
        //Clear the text field of amount.
        buyStockByAmountView.clearTextFieldData("BSAAmountTxt");
        //Clear the text field of date.
        buyStockByAmountView.clearTextFieldData("BSADateTxt");
        //Clear the text field of commission rate.
        buyStockByAmountView.clearTextFieldData("BSACommissionRateTxt");
        buyStockByAmountView.setSuccessMessage("BSAErrorLbl",
                "Stock bought successfully.");

      } catch (IllegalArgumentException iae) {
        this.buyStockByAmountView.setErrorMessage("BSAErrorLbl", iae.getMessage());

      }


    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.buyStockByAmountView.addActionListener(buttonListener);

  }

  /**
   * The following method configures the buy stock by volume.
   * It buys stock by volume.
   */
  private void configureBuyStockByVolumeButtonListener() {
    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();

    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("BSVSave", () -> {

      try {
        buyStockByVolumeView.setErrorMessage("BSVErrorLbl",
                "");
        //Accept the ticker symbol.
        String tickerSymbol = validateTickerSymbol(buyStockByVolumeView, "BSVTickerSymbolTxt");
        //Accept the portfolio.
        int portId = validatePortfolio(buyStockByVolumeView, "BSVPortfolioId");
        //Accept the volume to be bought.
        double volumeInvested = validateValue(buyStockByVolumeView,
                "BSVVolumeTxt", "volume");
        //Accept the date at which stock was bought.
        LocalDate d = validateDate(buyStockByVolumeView, "BSVDateTxt");
        //Accept the commission rate.
        double commRate = validateValue(buyStockByVolumeView,
                "BSVCommissionRateTxt", "commissionRate");
        //Use the add stock method.
        portfolioOperations.addStock(portId, tickerSymbol, volumeInvested, d, commRate);
        //Clear the ticker symbol text field.
        buyStockByVolumeView.clearTextFieldData("BSVTickerSymbolTxt");
        //Clear the volume text field.
        buyStockByVolumeView.clearTextFieldData("BSVVolumeTxt");
        //Clear the date field.
        buyStockByVolumeView.clearTextFieldData("BSVDateTxt");
        //Clear the commission rate text field.
        buyStockByVolumeView.clearTextFieldData("BSVCommissionRateTxt");
        buyStockByVolumeView.setSuccessMessage("BSVErrorLbl",
                "Stock bought successfully.");

      } catch (IllegalArgumentException iae) {
        this.buyStockByVolumeView.setErrorMessage("BSVErrorLbl", iae.getMessage());
      }
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.buyStockByVolumeView.addActionListener(buttonListener);
  }


  /**
   * The following method configure the display stock method.
   * It displays the stock.
   */
  private void configureStockDisplayButtonListener() {

    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("VPSViewStock", () -> {
      LocalDate d;
      displayStockView.setErrorMessage("VPSErrorLbl",
              "");
      try {
        //Accept the portfolio.
        int portId = validatePortfolio(displayStockView, "VPSPortfolioId");
        //Accept the date.
        LocalDate date = validateDate(displayStockView, "VPSDateTxt");
        String portfolioId = displayStockView.
                getComboFieldData("VPSPortfolioId");
        Map<String, Map<String, Double>> resultMap =
                portfolioOperations.displayStocks(portId, date);
        this.displayStockView.setSummaryData(resultMap);
      } catch (IllegalArgumentException iae) {
        displayStockView.setErrorMessage("VPSErrorLbl", iae.getMessage());
      }
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.displayStockView.addActionListener(buttonListener);
  }

  /**
   * The following method configures the one time investment method.
   */
  private void configureOneTimeInvestmentButtonListener() {
    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    Map<String, Runnable> comboBoxMap =
            new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    ComboBoxItemListener comboBoxItemListener = new ComboBoxItemListener();
    buttonClickedMap.put("createOneTime", () -> {

      this.dcaInvestmentView
              .setErrorMessage("oneTimeErrorLbl",
                      "");
      this.dcaInvestmentView
              .setSuccessMessage("oneTimeErrorLbl",
                      "");
      try {
        //Accept the portfolio.
        int portId = validatePortfolio(this.oneTimeInvestmentView, "oneTimePortfolioId");
        //Accept the amount invested.
        double amountInvested = validateValue(this.oneTimeInvestmentView, "oneTimePortfolioId",
                "amount");
        //Accept the commission rate.
        double commission = validateValue(this.oneTimeInvestmentView, "oneTimePortfolioId",
                "commission");
        //Accept the date of investment.
        LocalDate d = validateDate(this.oneTimeInvestmentView, "oneInvestmentDate");
        //Accept the investment options.
        String investmentOption
                = oneTimeInvestmentView.getComboFieldData("oneInvestmentOption");
        //Retrieve the stocks of the portfolio.
        List<Stock> stocks =
                portfolioOperations.viewPortfolioStocks(portId, d);
        //If the investment option is 'EQUAL'
        if (investmentOption.matches("EQUAL")) {
          equalWeightedInvestment(stocks, amountInvested,
                  portId, d, commission);
        } else if (investmentOption.matches("CUSTOM")) {
          //If the investment option is 'CUSTOM'
          List<Double> stockWeightage = new ArrayList<>();
          //Accept the weights for stocks.
          String stocksWeights
                  = oneTimeInvestmentView.getTextFieldData("oneTimeCustomWeightsTxt");
          stockWeightage = validateWeights(stocksWeights, stocks);
          customWeighted(stocks, stockWeightage,
                  amountInvested, portId, d, commission);
        }
        this.oneTimeInvestmentView
                .setSuccessMessage("oneTimeErrorLbl",
                        "One Time Investment Applied Successfully.");

      } catch (IllegalArgumentException iae) {
        this.oneTimeInvestmentView
                .setErrorMessage("oneTimeErrorLbl",
                        iae.getMessage());
      }
      //Clear the amount invested text field.
      oneTimeInvestmentView.clearTextFieldData("oneAmountInvested");
      //Clear the investment date text field.
      oneTimeInvestmentView.clearTextFieldData("oneInvestmentDate");
      //Clear the commission rate text field.
      oneTimeInvestmentView.clearTextFieldData("oneCommissionRate");
      //Clear the weight text field.
      oneTimeInvestmentView.clearTextFieldData("oneTimeCustomWeightsTxt");
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.oneTimeInvestmentView.addActionListener(buttonListener);

    //When the custom combo box field is selected.
    comboBoxMap.put("CUSTOM", () -> {
      try {
        int portId =
                validatePortfolio(oneTimeInvestmentView, "oneTimePortfolioId");
        LocalDate date
                = validateDate(oneTimeInvestmentView, "oneInvestmentDate");
        List<Stock> stocks =
                portfolioOperations.viewPortfolioStocks(portId, date);
        String stockString = "";
        for (Stock stock : stocks) {
          stockString = stockString + stock.getTickerSymbol() + " ";
        }
        //When stock size is 0.
        if (stocks.size() == 0) {
          oneTimeInvestmentView.setErrorMessage("oneTimeErrorLbl",
                  "NO STOCKS IN THE PORTFOLIO!!!! PLEASE BUY STOCKS FIRST");
        }
        oneTimeInvestmentView.setLabelFieldData("OneTimeCustomStocksLbl",
                "There are " + stocks.size() + " stocks."
                        + " Enter custom weights seperated by comma - "
                        + stockString);
      } catch (IllegalArgumentException iae) {
        oneTimeInvestmentView.setErrorMessage("oneTimeErrorLbl", iae.getMessage());
      }

    });

    comboBoxMap.put("EQUAL", () -> {

      oneTimeInvestmentView.setErrorMessage("OneTimeCustomStocksLbl",
              "THE FOLLOWING TEXT BOX IS ONLY TO ENTER THE CUSTOM WEIGHTS!!!");
    });

    comboBoxItemListener.setComboBoxActionMap(comboBoxMap);
    this.oneTimeInvestmentView.addComboBoxListener(comboBoxItemListener);

  }

  /**
   * The following method configures the DCA investment method.
   */
  private void configureDCAInvestmentButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    Map<String, Runnable> comboBoxMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    ComboBoxItemListener comboBoxItemListener = new ComboBoxItemListener();
    buttonClickedMap.put("createDCA", () -> {
      this.dcaInvestmentView
              .setErrorMessage("dcaError",
                      "");
      this.dcaInvestmentView
              .setSuccessMessage("dcaError",
                      "");
      //Accept the portfolio.
      String portfolioId = dcaInvestmentView.getComboFieldData("dcaPortfolioId");
      //Accept the start date.
      String start = dcaInvestmentView.getTextFieldData("dcaStartDate");
      //Accept the end date.
      String end = dcaInvestmentView.getTextFieldData("dcaEndDate");
      //Accept the amount.
      String amount = dcaInvestmentView.getTextFieldData("dcaAmountInvested");
      //Accept the commission rate.
      String commission = dcaInvestmentView.getTextFieldData("dcaCommissionRate");
      //Accept the investment option.
      String investOption = dcaInvestmentView.getComboFieldData("dcaInvestmentOption");

      try {
        int portId = validatePortfolio(dcaInvestmentView, "dcaPortfolioId");
        //Validate the amount invested.
        double amountInvested = validateValue(dcaInvestmentView, "dcaAmountInvested",
                "amount");
        //Validate the commission rate.
        double commissionRate = validateValue(dcaInvestmentView, "dcaCommissionRate",
                "commission");
        //Validate the start date.
        LocalDate d1 = validateDate(dcaInvestmentView, "dcaStartDate");
        //Validate the end date.
        LocalDate d2 = validateDate(dcaInvestmentView, "dcaEndDate");
        //Retrieve stocks of the portfolio.
        List<Stock> stocks =
                portfolioOperations.viewPortfolioStocks(portId, d1);
        List<Double> stockWeightage = new ArrayList<>();
        //Initialize the new strategy.
        StrategyOperations dcaStrategy = new DCA(portfolioOperations);
        //Accepts the date frequency for the investment.
        String dayFrequency = dcaInvestmentView.getTextFieldData("dcaDayFrequency");
        int days = 0;
        //Validate day.
        days = validateDay(dayFrequency);
        //Compare dates.
        boolean datesValidity = compareStrategyDates(d1, d2);
        //If the investment option is EQUAL.
        if (investOption.matches("EQUAL")) {
          double weightForEachStock = amountInvested / stocks.size();
          for (int i = 0; i < stocks.size(); i++) {
            stockWeightage.add(weightForEachStock);
          }
          if (datesValidity) {
            Strategy newStrategy = new Strategy(d1, d2, portId, amountInvested,
                    commissionRate, stocks, stockWeightage, days);
            dcaStrategy.executeStrategy(newStrategy);
          }
        } else if (investOption.matches("CUSTOM")) {
          //If the investment option is CUSTOM.
          String stocksWeights
                  = dcaInvestmentView.getTextFieldData("dcaCustomWeight");
          stockWeightage = validateWeights(stocksWeights, stocks);

          if (datesValidity) {
            Strategy newStrategy = new Strategy(d1, d2, portId, amountInvested,
                    commissionRate, stocks, stockWeightage, days);
            dcaStrategy.executeStrategy(newStrategy);
          }

        }
        String data = amount + "_" + commission + "_" + start + "_" + end + "_"
                + dayFrequency;
        strategyOperations.saveStrategy(data);
        this.dcaInvestmentView
                .setSuccessMessage("dcaError",
                        "Strategy Applied Successfully.");
      } catch (IllegalArgumentException iae) {
        this.dcaInvestmentView
                .setErrorMessage("dcaError",
                        iae.getMessage());
      }
      //Clear the start date text field.
      dcaInvestmentView.clearTextFieldData("dcaStartDate");
      //Clear the end date text field.
      dcaInvestmentView.clearTextFieldData("dcaEndDate");
      //Clear the amount invested text field.
      dcaInvestmentView.clearTextFieldData("dcaAmountInvested");
      //Clear the commission rate text field.
      dcaInvestmentView.clearTextFieldData("dcaCommissionRate");
      //Clear the day frequency text field.
      dcaInvestmentView.clearTextFieldData("dcaDayFrequency");
      //Clear the custom weight text field.
      dcaInvestmentView.clearTextFieldData("dcaCustomWeight");

    });
    buttonClickedMap.put("dcaApply", () -> {
      String strategy =
              dcaInvestmentView.getComboFieldData("dcaExistingStrategies");
      String[] values = strategy.split("_");
      if (values.length == 5) {
        dcaInvestmentView.setTextFieldData("dcaStartDate", values[2]);
        dcaInvestmentView.setTextFieldData("dcaEndDate", values[3]);
        dcaInvestmentView.setTextFieldData("dcaAmountInvested", values[0]);
        dcaInvestmentView.setTextFieldData("dcaCommissionRate", values[1]);
        dcaInvestmentView.setTextFieldData("dcaDayFrequency", values[4]);
      }
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.dcaInvestmentView.addActionListener(buttonListener);
    comboBoxMap.put("CUSTOM", () -> {
      try {
        int portId =
                validatePortfolio(dcaInvestmentView, "dcaPortfolioId");
        LocalDate start
                = validateDate(dcaInvestmentView, "dcaStartDate");
        LocalDate end
                = validateDate(dcaInvestmentView, "dcaEndDate");
        List<Stock> stocks =
                portfolioOperations.viewPortfolioStocks(portId, start);
        String stockString = "";
        for (Stock stock : stocks) {
          stockString = stockString + stock.getTickerSymbol() + " ";
        }
        if (stocks.size() == 0) {
          dcaInvestmentView.setErrorMessage("dcaError",
                  "NO STOCKS IN THE PORTFOLIO!!!! PLEASE BUY STOCKS FIRST");
        }
        dcaInvestmentView.setLabelFieldData("dcaCustomStocksLbl",
                "There are " + stocks.size() + " stocks."
                        + " Enter custom weights seperated by comma - "
                        + stockString);
      } catch (IllegalArgumentException iae) {
        dcaInvestmentView.setErrorMessage("dcaError", iae.getMessage());
      }
    });
    comboBoxMap.put("EQUAL", () -> {
      dcaInvestmentView.setErrorMessage("dcaCustomStocksLbl",
              "THE FOLLOWING TEXT BOX IS ONLY TO ENTER THE CUSTOM WEIGHTS!!!");
    });
    comboBoxItemListener.setComboBoxActionMap(comboBoxMap);
    this.dcaInvestmentView.addComboBoxListener(comboBoxItemListener);
  }

  /**
   * The following method configures the graph method.
   */
  private void configurePortfolioGraphButtonListener() {
    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("PGViewGraph", () -> {
      try {
        //Accept the portfolio.
        int portId = validatePortfolio(portfolioGraphView, "PGPortfolioId");
        //Accept the start date.
        LocalDate sdate = validateDate(portfolioGraphView, "PGStartDateTxt");
        //Accept the end date.
        LocalDate edate = validateDate(portfolioGraphView, "PGEndDateTxt");
        //Accept the frequency.
        int frequency = validateIntValue(portfolioGraphView, "PGFrequencyTxt");
        DefaultCategoryDataset dataset = portfolioOperations.
                getGraphDataset(sdate, edate, portId, frequency);
        portfolioGraphView.plotGraph("Performance", "Date",
                "Total Stock Cost " + "& Total Stock Value", dataset);
      } catch (IllegalArgumentException iae) {
        portfolioGraphView.setErrorMessage("PGErrorLbl", iae.getMessage());
      }
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.portfolioGraphView.addActionListener(buttonListener);
  }

  /**
   * The following method configures the load portfolio method.
   */
  private void configurePortfolioLoadButtonListener() {
    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("PLLoad", () -> {
      try {
        String portfolioName = validatePortfolioNameForFiles(portfolioLoadView,
                "PLPortfolioId", "load");
        portfolioOperations.loadPortfolios(false, portfolioName);
        portfolioLoadView.setSuccessMessage("PLErrorLbl",
                "Portfolio loaded successfully.");
      } catch (IllegalArgumentException iae) {
        portfolioLoadView.setErrorMessage("PLErrorLbl", iae.getMessage());
      }
    });

    buttonClickedMap.put("PLLoadAll", () -> {
      try {
        portfolioOperations.loadPortfolios(true, null);
        portfolioLoadView.setSuccessMessage("PLErrorLbl",
                "All Portfolios loaded successfully.");
      } catch (IllegalArgumentException iae) {
        portfolioLoadView.setErrorMessage("PLErrorLbl", iae.getMessage());
      }
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.portfolioLoadView.addActionListener(buttonListener);
  }


  /**
   * The following method configures the save portfolio method.
   */
  private void configurePortfolioSaveButtonListener() {

    Map<String, Runnable> buttonClickedMap =
            new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("PSSave", () -> {
      try {
        //Accept the portfolio name.
        String portfolioName = validatePortfolioNameForFiles(portfolioSaveView,
                "PSPortfolioId", "save");
        //Save portfolio.
        portfolioOperations.savePortfolios(false, portfolioName, -1);
        portfolioSaveView.setSuccessMessage("PSErrorLbl",
                "Portfolio saved successfully.");
      } catch (IllegalArgumentException iae) {
        portfolioSaveView.setErrorMessage("PSErrorLbl", iae.getMessage());
      }
    });

    buttonClickedMap.put("PSSaveAll", () -> {
      try {
        portfolioOperations.savePortfolios(true, null, -1);
        portfolioSaveView.setSuccessMessage("PSErrorLbl",
                "All Portfolios saved successfully.");
      } catch (IllegalArgumentException iae) {
        portfolioSaveView.setErrorMessage("PSErrorLbl", iae.getMessage());
      }
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.portfolioSaveView.addActionListener(buttonListener);
  }

  /**
   * The following method retrieves the existing portfolio.
   */
  public String getExistingPortfolios() {
    //Accept the portfolio strings.
    String portfolios = portfolioOperations.toString();
    if (portfolioOperations.toString().isEmpty()) {
      return "";
    }
    portfolios = portfolios.replaceAll("\n\n", "\n");
    return portfolios;
  }

  /**
   * The following method writes the saved portfolio in a text file.
   */
  public List<String> getSavedPortfolios() {
    List<String> savedFiles = new ArrayList<String>();

    try {
      String current = new java.io.File(".").getCanonicalPath();
      File dir = new File(current + "/stockData/savedPortfolios/");

      if (!dir.exists()) {
        dir.mkdir();
      }
      File[] files = dir.listFiles();
      for (File file : files) {
        savedFiles.add(file.getName().replace(".txt", ""));
      }
    } catch (IOException ioe) {
    }
    return savedFiles;
  }

  /**
   * The following method retrieves the existing portfolios.
   */
  public List<String> getExistingStrategies() {
    List<String> savedFiles = strategyOperations.loadStrategy();
    return savedFiles;
  }


  /**
   * The following method invests in the portfolio by equally distributing amount in each stock
   *
   * @param stocks         the list of stocks in which amount is to be invested.
   * @param amountInvested the amount invested in portfolio.
   * @param portfolioId    the id of the portfolio.
   * @param d              the date at which the investment took place.
   * @param commissionRate the commission charged for the transaction
   */
  private void equalWeightedInvestment(List<Stock> stocks,
                                       double amountInvested, int portfolioId, LocalDate d,
                                       double commissionRate) {

    double amountInvestedInEachStock = amountInvested / stocks.size();
    for (int i = 0; i < stocks.size(); i++) {
      String tickerSymbol = stocks.get(i).getTickerSymbol();
      portfolioOperations.addStockByAmount(portfolioId,
              tickerSymbol, amountInvestedInEachStock
              , d, commissionRate);
    }
  }

  /**
   * The following method invests in custom weighted option.
   * @param stocks the stocks in portfolio.
   * @param weights the list weights.
   * @param amountInvested the amount invested in the portfolio.
   * @param portfolioId the id of the portfolio.
   * @param d the date of the investment.
   * @param commissionRate the commission rate charged for the investment.
   */
  private void customWeighted(List<Stock> stocks, List<Double> weights,
                              double amountInvested, int portfolioId, LocalDate d,
                              double commissionRate) {
    for (int i = 0; i < weights.size(); i++) {
      double stockWeight = weights.get(i);
      double stockInvestment = amountInvested * (stockWeight / 100);
      String stockTickerSymbol = stocks.get(i).getTickerSymbol();
      portfolioOperations.addStockByAmount(portfolioId,
              stockTickerSymbol, stockInvestment, d, commissionRate);
    }
  }

  /**
   * The following method validated the portfolio.
   * @param view the GUI view object.
   * @param field the string of portfolio.
   */
  private int validatePortfolio(GUIView view, String field) {

    String portfolioString = view.getComboFieldData(field);
    try {
      if (portfolioString.equals("Select")) {
        throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);
      }
      return Integer.parseInt(portfolioString.split("\\.")[0]);
    } catch (NumberFormatException nbe) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);
    }
  }

  /**
   * The following method portfolios in the file.
   * @param view the GUI view object.
   * @param field the portfolio string.
   * @param type the type of the investment.
   */
  private String validatePortfolioNameForFiles(GUIView view, String field, String type) {

    String portfolioString = view.getComboFieldData(field);
    try {
      if (portfolioString.equals("Select")) {
        throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);
      }
      if (type.equals("save")) {
        return portfolioString.split("\\.")[1];
      } else {
        return portfolioString;
      }

    } catch (NumberFormatException nbe) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);
    }
  }

  /**
   * The following method validates the weights for the custom weight investment option.
   * @param stocks the list of stocks.
   * @param stockString the string of stocks' ticker symbol.
   */
  private List<Double> validateWeights(String stockString, List<Stock> stocks) {
    String[] weightsList = stockString.split(",");
    List<Double> weightsOfStock = new ArrayList<>();
    if (weightsList.length != stocks.size()) {
      throw new IllegalArgumentException(StockConstants.ERROR_WEIGHTAGE_SIZE);
    } else {
      for (String weight : weightsList) {
        Double weightage = Double.parseDouble(weight);
        if (weightage < 0) {
          throw new IllegalArgumentException(StockConstants.ERROR_NEGATIVE_WEIGHTAGE);
        } else if (weightage > 100) {
          throw new IllegalArgumentException(StockConstants.ERROR_GREATER_WEIGHTAGE);
        } else {
          weightsOfStock.add(weightage);
        }
      }
      double sum = 0;
      for (Double weights : weightsOfStock) {
        sum = sum + weights;
      }
      if (sum > 100) {
        throw new IllegalArgumentException(StockConstants.ERROR_SUM_WEIGHTS);
      } else if (sum < 100) {
        throw new IllegalArgumentException(StockConstants.ERROR_SUM_WEIGHTS_LESS);
      }
    }
    return weightsOfStock;
  }

  /**
   * The following method validates the day.
   * @param dayFrequency the day frequency.
   */
  private int validateDay(String dayFrequency) {
    int days = 0;
    days = Integer.parseInt(dayFrequency);
    if (days <= 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_DAYS);
    }
    return days;
  }

  /**
   * The following method validates the ticker symbol of the stock.
   * @param view the GUIView object.
   * @param field the ticker symbol.
   */
  private String validateTickerSymbol(GUIView view, String field) {
    String tickerSymbolString = view.getTextFieldData(field);

    if (tickerSymbolString == null || tickerSymbolString.isEmpty()) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_TICKER_SYMBOL);
    }
    return tickerSymbolString;
  }

  /**
   * The following method compares the start and end date.
   * @param start the start date of the strategy.
   * @param end the end date of the strategy.
   */
  private boolean compareStrategyDates(LocalDate start, LocalDate end) {
    if (start.compareTo(end) == 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_SAME_DATE);
    } else if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_START_DATE);
    }
    return true;
  }

  /**
   * The following method validates the given date.
   * @param view the GUIView object.
   * @param field the date in string form.
   */
  private LocalDate validateDate(GUIView view, String field) {
    try {
      String date = view.getTextFieldData(field);
      return LocalDate.parse(date, formatter);
    } catch (DateTimeException dte) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_DATE);
    }
  }

  /**
   * The following method validates the value.
   * @param view the GUIView object.
   * @param field the value in the string field.
   * @param type the type of investment.
   */
  private double validateValue(GUIView view, String field, String type) {
    try {
      String value = view.getTextFieldData(field);
      return Double.parseDouble(value);
    } catch (NumberFormatException dte) {
      if (type.equalsIgnoreCase("amount")) {
        throw new IllegalArgumentException(StockConstants.ERROR_INVALID_AMOUNT);
      } else if (type.equalsIgnoreCase("commissionRate")) {
        throw new IllegalArgumentException(StockConstants.ERROR_INVALID_COMMISSION);
      } else if (type.equalsIgnoreCase("volume")) {
        throw new IllegalArgumentException(StockConstants.ERROR_INVALID_VOLUME);
      } else {
        throw new IllegalArgumentException(StockConstants.ERROR_INVALID_GENERIC_VALUE);
      }
    }
  }

  /**
   * The following method validates the day frequency.
   * @param view the GUIView object.
   * @param field the day in the string format.
   */
  private int validateIntValue(GUIView view, String field) {
    try {
      String value = view.getTextFieldData(field);
      int frequency = Integer.parseInt(value);
      if (frequency < 0 || frequency > 365) {
        throw new NumberFormatException("Invalid value");
      }
      return frequency;
    } catch (NumberFormatException dte) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_FREQUENCY);
    }
  }

}